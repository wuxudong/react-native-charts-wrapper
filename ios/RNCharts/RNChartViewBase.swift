//
//  RNChartViewBase.swift
//  reactNativeCharts
//
//  Created by xudong wu on 25/02/2017.
//  Copyright wuxudong
//

import UIKit

// In react native, because object-c is unaware of swift protocol extension. use baseClass as workaround

@objcMembers
open class RNChartViewBase: UIView, ChartViewDelegate {
    open var onSelect:RCTBubblingEventBlock?
    
    open var onChange:RCTBubblingEventBlock?
    
    override open func reactSetFrame(_ frame: CGRect)
    {
        super.reactSetFrame(frame);
        
        let chartFrame = CGRect(x: 0, y: 0, width: frame.width, height: frame.height)
        chart.reactSetFrame(chartFrame);
    }
    
    var chart: ChartViewBase {
        fatalError("subclass should override this function.")
    }
    
    var dataExtract : DataExtract {
        fatalError("subclass should override this function.")
    }
    
    func setData(_ data: NSDictionary) {
        let json = BridgeUtils.toJson(data)
        
        chart.data = dataExtract.extract(json)
    }
    
    func setLegend(_ config: NSDictionary) {
        let json = BridgeUtils.toJson(config)
        
        let legend = chart.legend;
        
        if json["enabled"].bool != nil {
            legend.enabled = json["enabled"].boolValue;
        }
        
        if json["textColor"].int != nil {
            legend.textColor = RCTConvert.uiColor(json["textColor"].intValue);
        }
        
        if json["textSize"].number != nil {
            legend.font = legend.font.withSize(CGFloat(truncating: json["textSize"].numberValue))
        }
        
        // Wrapping / clipping avoidance
        if json["wordWrapEnabled"].bool != nil {
            legend.wordWrapEnabled = json["wordWrapEnabled"].boolValue
        }
        
        if json["maxSizePercent"].number != nil {
            legend.maxSizePercent = CGFloat(truncating: json["maxSizePercent"].numberValue)
        }
        
        if json["horizontalAlignment"].string != nil {
            legend.horizontalAlignment = BridgeUtils.parseLegendHorizontalAlignment(json["horizontalAlignment"].stringValue)
        }
        
        if json["verticalAlignment"].string != nil {
            legend.verticalAlignment = BridgeUtils.parseLegendVerticalAlignment(json["verticalAlignment"].stringValue)
        }
        
        if json["orientation"].string != nil {
            legend.orientation = BridgeUtils.parseLegendOrientation(json["orientation"].stringValue)
        }
        
        if json["drawInside"].bool != nil {
            legend.drawInside = json["drawInside"].boolValue
        }
        
        if json["direction"].string != nil {
            legend.direction = BridgeUtils.parseLegendDirection(json["direction"].stringValue)
        }
        
        
        if json["form"].string != nil {
            legend.form = BridgeUtils.parseLegendForm(json["form"].stringValue)
        }
        
        if json["formSize"].number != nil {
            legend.formSize = CGFloat(truncating: json["formSize"].numberValue)
        }
        
        if json["xEntrySpace"].number != nil {
            legend.xEntrySpace = CGFloat(truncating: json["xEntrySpace"].numberValue)
        }
        
        if json["yEntrySpace"].number != nil {
            legend.yEntrySpace = CGFloat(truncating: json["yEntrySpace"].numberValue)
        }
        
        if json["formToTextSpace"].number != nil {
            legend.formToTextSpace = CGFloat(truncating: json["formToTextSpace"].numberValue)
        }
        
        
        // Custom labels & colors
        if json["custom"].exists() {
            let customMap = json["custom"]
            if customMap["colors"].exists() && customMap["labels"].exists() {
                
                let colorsArray = customMap["colors"].arrayValue
                let labelsArray = customMap["labels"].arrayValue
                
                if colorsArray.count == labelsArray.count {
                    // TODO null label should start a group
                    // TODO -2 color should avoid drawing a form
                    
                    var legendEntries = [LegendEntry]();
                    
                    for i in 0..<labelsArray.count {
                        let legendEntry = LegendEntry()
                        legendEntry.formColor =  RCTConvert.uiColor(colorsArray[i].intValue);
                        legendEntry.label = labelsArray[i].stringValue;
                        
                        legendEntries.append(legendEntry)
                    }
                    
                    legend.setCustom(entries: legendEntries)
                }
            }
        }
        
        // TODO extra
        
    }
    
    func setChartBackgroundColor(_ backgroundColor: Int) {
        chart.backgroundColor = RCTConvert.uiColor(backgroundColor)
    }
    
    func setChartDescription(_ config: NSDictionary) {
        let json = BridgeUtils.toJson(config)
        
        let chartDescription = Description()
        
        if json["text"].string != nil {
            chartDescription.text = json["text"].stringValue
        }
        
        if json["textColor"].int != nil {
            chartDescription.textColor = RCTConvert.uiColor(json["textColor"].intValue)
        }
        
        if json["textSize"].float != nil {
            chartDescription.font = chartDescription.font.withSize(CGFloat(json["textSize"].floatValue))
        }
        
        
        if json["positionX"].number != nil && json["positionY"].number != nil {
            chartDescription.position = CGPoint(x: CGFloat(json["positionX"].numberValue), y: CGFloat(json["positionY"].numberValue))
        }
        
        chart.chartDescription = chartDescription
    }
    
    func setNoDataText(_ noDataText: String) {
        chart.noDataText = noDataText
    }
    
    func setTouchEnabled(_ touchEnabled: Bool) {
        chart.isUserInteractionEnabled = touchEnabled
    }
    
    func setDragDecelerationEnabled(_ dragDecelerationEnabled: Bool) {
        chart.dragDecelerationEnabled = dragDecelerationEnabled
    }
    
    func setDragDecelerationFrictionCoef(_ dragDecelerationFrictionCoef: NSNumber) {
        chart.dragDecelerationFrictionCoef = CGFloat(dragDecelerationFrictionCoef)
    }
    
    func setAnimation(_ config: NSDictionary) {
        let json = BridgeUtils.toJson(config)
        
        let durationX = json["durationX"].double != nil ?
            json["durationX"].doubleValue / 1000.0 : 0
        let durationY = json["durationY"].double != nil ?
            json["durationY"].doubleValue / 1000.0 : 0
        
        
        var easingX: ChartEasingOption = .linear
        var easingY: ChartEasingOption = .linear
        
        if json["easingX"].string != nil {
            easingX = BridgeUtils.parseEasingOption(json["easingX"].stringValue)
        }
        if json["easingY"].string != nil {
            easingY = BridgeUtils.parseEasingOption(json["easingY"].stringValue)
        }
        
        if durationX != 0 && durationY != 0 {
            chart.animate(xAxisDuration: durationX, yAxisDuration: durationY, easingOptionX: easingX, easingOptionY: easingY)
        } else if (durationX != 0) {
            chart.animate(xAxisDuration: durationX, easingOption: easingX)
        } else if (durationY != 0) {
            chart.animate(yAxisDuration: durationY, easingOption: easingY)
        }
    }
    
    func setXAxis(_ config: NSDictionary) {
        let json = BridgeUtils.toJson(config)
        
        let xAxis = chart.xAxis;
        
        setCommonAxisConfig(xAxis, config: json)
        
        if json["labelRotationAngle"].number != nil {
            xAxis.labelRotationAngle = CGFloat(json["labelRotationAngle"].numberValue)
        }
        
        if json["avoidFirstLastClipping"].bool != nil {
            xAxis.avoidFirstLastClippingEnabled = json["avoidFirstLastClipping"].boolValue
        }
        
        if json["position"].string != nil {
            xAxis.labelPosition = BridgeUtils.parseXAxisLabelPosition(json["position"].stringValue)
        }
    }
    
    func setCommonAxisConfig(_ axis: AxisBase, config: JSON) {
        
        // what is drawn
        if config["enabled"].bool != nil {
            axis.enabled = config["enabled"].boolValue
        }
        
        if config["drawLabels"].bool != nil {
            axis.drawLabelsEnabled = config["drawLabels"].boolValue
        }
        
        if config["drawAxisLine"].bool != nil {
            axis.drawAxisLineEnabled = config["drawAxisLine"].boolValue
        }
        
        if config["drawGridLines"].bool != nil {
            axis.drawGridLinesEnabled = config["drawGridLines"].boolValue
        }
        
        // style
        if config["fontFamily"].string != nil {
            axis.labelFont = RCTFont.update(nil, withFamily: config["fontFamily"].string)
        }
        
        if config["fontStyle"].string != nil {
            axis.labelFont = RCTFont.update(axis.labelFont, withStyle: config["fontStyle"].string)
        }
        
        if config["fontWeight"].string != nil {
            axis.labelFont = RCTFont.update(axis.labelFont, withWeight: config["fontWeight"].string)
        }
        
        if config["textColor"].int != nil {
            axis.labelTextColor = RCTConvert.uiColor(config["textColor"].intValue)
        }
        
        if config["textSize"].float != nil {
            axis.labelFont = axis.labelFont.withSize(CGFloat(config["textSize"].floatValue))
        }
        
        if config["gridColor"].int != nil {
            axis.gridColor = RCTConvert.uiColor(config["gridColor"].intValue)
        }
        
        if config["gridLineWidth"].number != nil {
            axis.gridLineWidth = CGFloat(config["gridLineWidth"].numberValue)
        }
        
        if config["axisLineColor"].int != nil {
            axis.axisLineColor = RCTConvert.uiColor(config["axisLineColor"].intValue)
        }
        
        if config["axisLineWidth"].number != nil {
            axis.axisLineWidth = CGFloat(config["axisLineWidth"].numberValue)
        }
        
        if config["gridDashedLine"].exists() {
            let gridDashedLine = config["gridDashedLine"]
            
            var lineLength = CGFloat(0)
            var spaceLength = CGFloat(0)
            
            if gridDashedLine["lineLength"].number != nil {
                lineLength = CGFloat(gridDashedLine["lineLength"].numberValue)
            }
            
            if gridDashedLine["spaceLength"].number != nil {
                spaceLength = CGFloat(gridDashedLine["spaceLength"].numberValue)
            }
            
            if gridDashedLine["phase"].number != nil {
                axis.gridLineDashPhase = CGFloat(gridDashedLine["phase"].numberValue)
            }
            
            axis.gridLineDashLengths = [lineLength, spaceLength]
        }
        
        // limit lines
        if config["limitLines"].array != nil {
            let limitLinesConfig = config["limitLines"].arrayValue
            
            for limitLineConfig in limitLinesConfig {
                
                if limitLineConfig["limit"].double != nil {
                    
                    let limitLine = ChartLimitLine(limit: limitLineConfig["limit"].doubleValue)
                  
                    if limitLineConfig["label"].string != nil {
                        limitLine.label = limitLineConfig["label"].stringValue
                    }
                    
                    if (limitLineConfig["lineColor"].int != nil) {
                        limitLine.lineColor = RCTConvert.uiColor(limitLineConfig["lineColor"].intValue)
                    }
                  
                    if (limitLineConfig["valueTextColor"].int != nil) {
                        limitLine.valueTextColor = RCTConvert.uiColor(limitLineConfig["valueTextColor"].intValue)
                    }
                  
                    if (limitLineConfig["valueFont"].int != nil) {
                        limitLine.valueFont = NSUIFont.systemFont(ofSize: CGFloat(limitLineConfig["valueFont"].intValue))
                    }
                    
                    if limitLineConfig["lineWidth"].number != nil {
                        limitLine.lineWidth = CGFloat(limitLineConfig["lineWidth"].numberValue)
                    }
                  
                    if limitLineConfig["labelPosition"].string != nil {
                        limitLine.labelPosition = BridgeUtils.parseLimitlineLabelPosition(limitLineConfig["labelPosition"].stringValue);
                    }
                  
                    if limitLineConfig["lineDashPhase"].float != nil {
                        limitLine.lineDashPhase = CGFloat(limitLineConfig["lineDashPhase"].floatValue);
                    }
                    if limitLineConfig["lineDashLengths"].arrayObject != nil {
                        limitLine.lineDashLengths = limitLineConfig["lineDashLengths"].arrayObject as? [CGFloat];
                    }

                    axis.addLimitLine(limitLine)
                }
            }
        }
        
        if config["drawLimitLinesBehindData"].bool != nil {
            axis.drawLimitLinesBehindDataEnabled = config["drawLimitLinesBehindData"].boolValue
        }
        
        if config["axisMaximum"].double != nil {
            axis.axisMaximum = config["axisMaximum"].doubleValue
        }
        
        if config["axisMinimum"].double != nil {
            axis.axisMinimum = config["axisMinimum"].doubleValue
        }
        
        if config["granularity"].double != nil {
            axis.granularity = config["granularity"].doubleValue
        }
        
        if config["granularityEnabled"].bool != nil {
            axis.granularityEnabled = config["granularityEnabled"].boolValue
        }
        
        if config["labelCount"].int != nil {
            var labelCountForce = false
            if config["labelCountForce"].bool != nil {
                labelCountForce = config["labelCountForce"].boolValue
            }
            axis.setLabelCount(config["labelCount"].intValue, force: labelCountForce)
        }
        
        // formatting
        // TODO: other formatting options
        let valueFormatter = config["valueFormatter"];
        if valueFormatter.array != nil {
            axis.valueFormatter = IndexAxisValueFormatter(values: valueFormatter.arrayValue.map({ $0.stringValue }))
        } else if valueFormatter.string != nil {
            if "largeValue" == valueFormatter.stringValue {
                axis.valueFormatter = LargeValueFormatter();
            } else if "percent" == valueFormatter.stringValue {
                let percentFormatter = NumberFormatter()
                percentFormatter.numberStyle = .percent
                
                axis.valueFormatter = DefaultAxisValueFormatter(formatter: percentFormatter);
            } else if "date" == valueFormatter.stringValue {
              let valueFormatterPattern = config["valueFormatterPattern"].stringValue;
              axis.valueFormatter = ChartDateFormatter(pattern: valueFormatterPattern);
            } else {
              let customFormatter = NumberFormatter()
              customFormatter.positiveFormat = valueFormatter.stringValue
              customFormatter.negativeFormat = valueFormatter.stringValue
              
              axis.valueFormatter = DefaultAxisValueFormatter(formatter: customFormatter);
          }
        }
        
        if config["centerAxisLabels"].bool != nil {
            axis.centerAxisLabelsEnabled = config["centerAxisLabels"].boolValue
        }
    }
    
    func setMarker(_ config: NSDictionary) {
        let json = BridgeUtils.toJson(config)
        
        if json["enabled"].exists() && !json["enabled"].boolValue {
            chart.marker = nil
            return
        }
        
        var markerFont = UIFont.systemFont(ofSize: 12.0)
        
        if json["textSize"].float != nil {
            markerFont = markerFont.withSize(CGFloat(json["textSize"].floatValue))
        }
        
        
        // TODO fontFamily, fontStyle
        
        let balloonMarker = BalloonMarker(
            color: RCTConvert.uiColor(json["markerColor"].intValue),
            font: markerFont,
            textColor: RCTConvert.uiColor(json["textColor"].intValue))
        chart.marker = balloonMarker
        
        balloonMarker.chartView = chart
        
    }
    
    func setHighlights(_ config: NSArray) {        
        var highlights : [Highlight] = []
        for object in config {
            if let dict = object as? NSDictionary {
                let json = BridgeUtils.toJson(dict)
                
                if json["x"].double != nil {
                    let dataSetIndex = json["dataSetIndex"].int != nil ? json["dataSetIndex"].intValue : 0
                    let y = json["y"].double != nil ? json["y"].doubleValue : 0
                    
                    var highlight : Highlight
                    if json["stackIndex"].int != nil {
                        highlight = Highlight(x: json["x"].doubleValue, dataSetIndex: dataSetIndex, stackIndex: json["stackIndex"].intValue)
                    } else {
                        highlight = Highlight(x: json["x"].doubleValue, y: y, dataSetIndex: dataSetIndex)
                    }
                    
                    if json["dataIndex"].int != nil {
                        highlight.dataIndex = json["dataIndex"].intValue
                    }
                    
                    highlights.append(highlight)
                }
            }
        }
        chart.highlightValues(highlights)
    }
    
    @objc public func chartValueSelected(_ chartView: ChartViewBase, entry: ChartDataEntry, highlight: Highlight) {
        
        if self.onSelect == nil {
            return
        } else {
            self.onSelect!(EntryToDictionaryUtils.entryToDictionary(entry))
            
        }
    }
    
    @objc public func chartValueNothingSelected(_ chartView: ChartViewBase) {
        if self.onSelect == nil {
            return
        } else {
            self.onSelect!(nil)
            
        }
    }
    
    @objc public func chartScaled(_ chartView: ChartViewBase, scaleX: CoreGraphics.CGFloat, scaleY: CoreGraphics.CGFloat) {
        sendEvent("chartScaled")
    }
    
    @objc public func chartTranslated(_ chartView: ChartViewBase, dX: CoreGraphics.CGFloat, dY: CoreGraphics.CGFloat) {
        sendEvent("chartTranslated")
    }
    
    func sendEvent(_ action:String) {
        var dict = [AnyHashable: Any]()
        
        dict["action"] = action
        if chart is BarLineChartViewBase {
            let viewPortHandler = chart.viewPortHandler
            let barLineChart = chart as! BarLineChartViewBase
            
            dict["scaleX"] = barLineChart.scaleX
            dict["scaleY"] = barLineChart.scaleY
            
            if let handler = viewPortHandler {
                let center = barLineChart.valueForTouchPoint(point: handler.contentCenter, axis: YAxis.AxisDependency.left)
                dict["centerX"] = center.x
                dict["centerY"] = center.y
                
                let leftBottom = barLineChart.valueForTouchPoint(point: CGPoint(x: handler.contentLeft, y: handler.contentBottom), axis: YAxis.AxisDependency.left)
                
                let rightTop = barLineChart.valueForTouchPoint(point: CGPoint(x: handler.contentRight, y: handler.contentTop), axis: YAxis.AxisDependency.left)
                
                dict["left"] = leftBottom.x
                dict["bottom"] = leftBottom.y
                dict["right"] = rightTop.x
                dict["top"] = rightTop.y
            }
        }
        
        if self.onChange == nil {
            return
        } else {
            self.onChange!(dict)
        }
    }
    
    
}
