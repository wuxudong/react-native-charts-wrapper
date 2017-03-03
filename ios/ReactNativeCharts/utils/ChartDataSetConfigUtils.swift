//
//  ChartDataSetConfigUtils.swift
//  reactNativeCharts
//
//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit
import Charts
import SwiftyJSON


class ChartDataSetConfigUtils: NSObject {
    static func commonConfig(dataSet: ChartDataSet, config: JSON) {
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
    }
    
    static func commonBarLineScatterCandleBubbleConfig(dataSet: BarLineScatterCandleBubbleChartDataSet, config: JSON) {
        if config["highlightColor"].string != nil {
            dataSet.highlightColor = RCTConvert.uiColor(config["highlightColor"].stringValue);
        }
    }
    
    
    static func commonLineScatterCandleRadarConfig(dataSet: LineScatterCandleRadarChartDataSet, config: JSON) {
        if config["drawHighlightIndicators"].bool != nil {
            dataSet.setDrawHighlightIndicators(config["drawHighlightIndicators"].boolValue);
        }
        
        if config["drawVerticalHighlightIndicator"].bool != nil {
            dataSet.drawVerticalHighlightIndicatorEnabled = config["drawVerticalHighlightIndicator"].boolValue;
        }
        
        if config["drawHorizontalHighlightIndicator"].bool != nil {
            dataSet.drawHorizontalHighlightIndicatorEnabled = config["drawHorizontalHighlightIndicator"].boolValue;
        }
        
        if config["highlightLineWidth"].number != nil {
            dataSet.highlightLineWidth = CGFloat(config["highlightLineWidth"].numberValue);
        }
    }
    
    static func commonLineRadarConfig( dataSet:LineRadarChartDataSet,  config:JSON) {
        if config["fillColor"].int != nil {
            dataSet.fillColor = RCTConvert.uiColor(config["fillColor"].intValue);
        }
        
        if config["fillAlpha"].number != nil {
            dataSet.fillAlpha = BridgeUtils.toIOSAlpha(config["fillAlpha"].numberValue);
        }
        
        if config["drawFilled"].bool != nil {
            dataSet.drawFilledEnabled = config["drawFilled"].boolValue;
        }
        
        if config["lineWidth"].number != nil {
            dataSet.lineWidth = CGFloat(config["lineWidth"].numberValue);
        }
    }
    
    
}
