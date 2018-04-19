//
//  ChartDataSetConfigUtils.swift
//  reactNativeCharts
//
//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

class ChartDataSetConfigUtils: NSObject {
    static func commonConfig(_ dataSet: ChartDataSet, config: JSON) {
        // Setting main color
        if config["color"].int != nil {
            dataSet.setColor(RCTConvert.uiColor(config["color"].intValue))
        }
        
        if config["colors"].array != nil {
            dataSet.colors = BridgeUtils.parseColors(config["colors"].arrayValue)
        }
        
        if config["drawValues"].bool != nil {
            dataSet.drawValuesEnabled = config["drawValues"].boolValue;
        }
        
        if config["highlightEnabled"].bool != nil {
            dataSet.highlightEnabled = config["highlightEnabled"].boolValue;
        }

        if config["valueTextSize"].float != nil {
            dataSet.valueFont = dataSet.valueFont.withSize(CGFloat(config["valueTextSize"].floatValue))
        }
        
        if config["valueTextColor"].int != nil {
            dataSet.valueTextColor = RCTConvert.uiColor(config["valueTextColor"].intValue)
        }
        
        if config["visible"].bool != nil {
            dataSet.visible = config["visible"].boolValue
        }
      
        let valueFormatter = config["valueFormatter"];
        if valueFormatter.string != nil {
            if "largeValue" == valueFormatter.stringValue {
                dataSet.valueFormatter = LargeValueFormatter();
            } else if "percent" == valueFormatter.stringValue {
                let percentFormatter = NumberFormatter()
                percentFormatter.numberStyle = .percent
                
                dataSet.valueFormatter = DefaultValueFormatter(formatter: percentFormatter);
            } else if "date" == valueFormatter.stringValue {
                let valueFormatterPattern = config["valueFormatterPattern"].stringValue;
                dataSet.valueFormatter = ChartDateFormatter(pattern: valueFormatterPattern);
            } else {
                let customFormatter = NumberFormatter()
                customFormatter.positiveFormat = valueFormatter.stringValue
                customFormatter.negativeFormat = valueFormatter.stringValue
                
                dataSet.valueFormatter = DefaultValueFormatter(formatter: customFormatter);
            }
            
        }

        if config["axisDependency"].string != nil {
            dataSet.axisDependency = BridgeUtils.parseAxisDependency(config["axisDependency"].stringValue)
        }
        

    }
    
    static func commonBarLineScatterCandleBubbleConfig(_ dataSet: BarLineScatterCandleBubbleChartDataSet, config: JSON) {
        if config["highlightColor"].int != nil {
            dataSet.highlightColor = RCTConvert.uiColor(config["highlightColor"].intValue);
        }
    }
    
    
    static func commonLineScatterCandleRadarConfig(_ dataSet: LineScatterCandleRadarChartDataSet, config: JSON) {
        if config["drawHighlightIndicators"].bool != nil {
            dataSet.setDrawHighlightIndicators(config["drawHighlightIndicators"].boolValue);
        }
        
        if config["drawVerticalHighlightIndicator"].bool != nil {
            dataSet.drawVerticalHighlightIndicatorEnabled = config["drawVerticalHighlightIndicator"].boolValue;
        }
        
        if config["drawHorizontalHighlightIndicator"].bool != nil {
            dataSet.drawHorizontalHighlightIndicatorEnabled = config["drawHorizontalHighlightIndicator"].boolValue;
        }
        
        if config["highlightLineWidth"].float != nil {
            dataSet.highlightLineWidth = CGFloat(config["highlightLineWidth"].floatValue);
        }
    }
    
    static func commonLineRadarConfig( _ dataSet:LineRadarChartDataSet,  config:JSON) {
        if config["fillColor"].int != nil {
            dataSet.fillColor = RCTConvert.uiColor(config["fillColor"].intValue);
        }
        
        if config["fillAlpha"].number != nil {
            dataSet.fillAlpha = BridgeUtils.toIOSAlpha(config["fillAlpha"].numberValue);
        }
        
        if config["drawFilled"].bool != nil {
            dataSet.drawFilledEnabled = config["drawFilled"].boolValue;
        }
        
        if config["lineWidth"].float != nil {
            dataSet.lineWidth = CGFloat(config["lineWidth"].floatValue);
        }
    }
    
    
}
