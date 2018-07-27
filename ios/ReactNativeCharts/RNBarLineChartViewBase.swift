//
// Created by xudong wu on 26/02/2017.
// Copyright (c) wuxudong. All rights reserved.
//

import Foundation
import Charts
import SwiftyJSON

class RNBarLineChartViewBase: RNYAxisChartViewBase {
    fileprivate var barLineChart: BarLineChartViewBase {
        get {
            return chart as! BarLineChartViewBase
        }
    }
    
    var savedVisibleRange : NSDictionary?

    override func setYAxis(_ config: NSDictionary) {
        let json = BridgeUtils.toJson(config)

        if json["left"].exists() {
            let leftYAxis = barLineChart.leftAxis
            setCommonAxisConfig(leftYAxis, config: json["left"]);
            setYAxisConfig(leftYAxis, config: json["left"]);
        }


        if json["right"].exists() {
            let rightAxis = barLineChart.rightAxis
            setCommonAxisConfig(rightAxis, config: json["right"]);
            setYAxisConfig(rightAxis, config: json["right"]);
        }
    }


    func setDrawGridBackground(_  enabled: Bool) {
        barLineChart.drawGridBackgroundEnabled = enabled;
    }


    func setGridBackgroundColor(_ color: Int) {
        barLineChart.gridBackgroundColor = RCTConvert.uiColor(color);
    }


    func setDrawBorders(_ enabled: Bool) {
        barLineChart.drawBordersEnabled = enabled;
    }

    func setBorderColor(_ color: Int) {
        
        barLineChart.borderColor = RCTConvert.uiColor(color);
    }

    func setBorderWidth(_ width: CGFloat) {
        barLineChart.borderLineWidth = width;
    }


    func setMaxVisibleValueCount(_ count: NSInteger) {
        barLineChart.maxVisibleCount = count;
    }
    
    func setVisibleRange(_ config: NSDictionary) {
        // delay visibleRange handling until chart data is set
        savedVisibleRange = config
    }
    
    func updateVisibleRange(_ config: NSDictionary) {
        let json = BridgeUtils.toJson(config)
        
        let x = json["x"]
        if x["min"].double != nil {
            barLineChart.setVisibleXRangeMinimum(x["min"].doubleValue)
        }
        if x["max"].double != nil {
            barLineChart.setVisibleXRangeMaximum(x["max"].doubleValue)
        }
        
        let y = json["y"]
        if y["left"]["min"].double != nil {
            barLineChart.setVisibleYRangeMinimum(y["left"]["min"].doubleValue, axis: YAxis.AxisDependency.left)
        }
        if y["left"]["max"].double != nil {
            barLineChart.setVisibleYRangeMaximum(y["left"]["max"].doubleValue, axis: YAxis.AxisDependency.left)
        }
        
        if y["right"]["min"].double != nil {
            barLineChart.setVisibleYRangeMinimum(y["right"]["min"].doubleValue, axis: YAxis.AxisDependency.right)
        }
        if y["right"]["max"].double != nil {
            barLineChart.setVisibleYRangeMaximum(y["right"]["max"].doubleValue, axis: YAxis.AxisDependency.right)
        }
    }
    
    func setAutoScaleMinMaxEnabled(_  enabled: Bool) {
        barLineChart.autoScaleMinMaxEnabled = enabled
    }

    func setKeepPositionOnRotation(_  enabled: Bool) {
        barLineChart.keepPositionOnRotation = enabled
    }

    func setScaleEnabled(_  enabled: Bool) {
        barLineChart.setScaleEnabled(enabled)
    }

    func setDragEnabled(_  enabled: Bool) {
        barLineChart.dragEnabled = enabled
    }


    func setScaleXEnabled(_  enabled: Bool) {
        barLineChart.scaleXEnabled = enabled
    }

    func setScaleYEnabled(_  enabled: Bool) {
        barLineChart.scaleYEnabled = enabled
    }

    func setPinchZoom(_  enabled: Bool) {
        barLineChart.pinchZoomEnabled = enabled
    }

    func setHighlightPerDragEnabled(_  enabled: Bool) {
        barLineChart.highlightPerDragEnabled = enabled
    }

    func setDoubleTapToZoomEnabled(_  enabled: Bool) {
        barLineChart.doubleTapToZoomEnabled = enabled
    }

    func setZoom(_ config: NSDictionary) {
        let json = BridgeUtils.toJson(config)

        if json["scaleX"].float != nil && json["scaleY"].float != nil && json["xValue"].double != nil && json["yValue"].double != nil {
            var axisDependency = YAxis.AxisDependency.left

            if json["axisDependency"].string != nil && json["axisDependency"].stringValue == "RIGHT" {
                axisDependency = YAxis.AxisDependency.right
            }
            
            barLineChart.zoom(scaleX: CGFloat(json["scaleX"].floatValue),
                    scaleY: CGFloat(json["scaleY"].floatValue),
                    xValue: json["xValue"].doubleValue,
                    yValue: json["yValue"].doubleValue,
                    axis: axisDependency)
        }
    }

    func setViewPortOffsets(_ config: NSDictionary) {
        let json = BridgeUtils.toJson(config)

        let left = json["left"].double != nil ? CGFloat(json["left"].doubleValue) : 0
        let top = json["top"].double != nil ? CGFloat(json["top"].doubleValue) : 0
        let right = json["right"].double != nil ? CGFloat(json["right"].doubleValue) : 0
        let bottom = json["bottom"].double != nil ? CGFloat(json["bottom"].doubleValue) : 0

        barLineChart.setViewPortOffsets(left: left, top: top, right: right, bottom: bottom)
    }

    func setExtraOffsets(_ config: NSDictionary) {
        let json = BridgeUtils.toJson(config)
    
        let left = json["left"].double != nil ? CGFloat(json["left"].doubleValue) : 0
        let top = json["top"].double != nil ? CGFloat(json["top"].doubleValue) : 0
        let right = json["right"].double != nil ? CGFloat(json["right"].doubleValue) : 0
        let bottom = json["bottom"].double != nil ? CGFloat(json["bottom"].doubleValue) : 0
    
        barLineChart.setExtraOffsets(left: left, top: top, right: right, bottom: bottom)
    }
    
    override func didSetProps(_ changedProps: [String]!) {
        super.didSetProps(changedProps)
        
        if let config = savedVisibleRange {
            updateVisibleRange(config)
        }        
    }
    
}
