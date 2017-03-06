//
//  ScatterDataExtract.swift
//  reactNativeCharts
//
//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation

import SwiftyJSON
import Charts

class ScatterDataExtract : DataExtract {
    override func createData() -> ChartData {
        return ScatterChartData();
    }
    
    override func createDataSet(_ entries: [ChartDataEntry]?, label: String?) -> IChartDataSet {
        return ScatterChartDataSet(values: entries, label: label)
    }
    
    override func dataSetConfig(_ dataSet: IChartDataSet, config: JSON) {
        let scatterDataSet = dataSet as! ScatterChartDataSet;
        
        ChartDataSetConfigUtils.commonConfig(scatterDataSet, config: config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(scatterDataSet, config: config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(scatterDataSet, config: config);
        
        // ScatterDataSet only config
        if config["scatterShapeSize"].number != nil {
            scatterDataSet.scatterShapeSize = CGFloat(config["scatterShapeSize"].numberValue)
        }
        if config["scatterShape"].string != nil {
            scatterDataSet.setScatterShape(BridgeUtils.parseScatterShape(config["scatterShape"].stringValue))
        }
        
        if config["scatterShapeHoleColor"].int != nil {
            scatterDataSet.scatterShapeHoleColor = RCTConvert.uiColor(config["scatterShapeHoleColor"].intValue);
        }
        
        if config["scatterShapeHoleRadius"].number != nil {
            scatterDataSet.scatterShapeHoleRadius = CGFloat(config["scatterShapeHoleRadius"].numberValue)
        }
        
    }
    
    override func createEntry(_ values: [JSON], index: Int) -> ChartDataEntry {
        var entry: ChartDataEntry;
        
        var x = Double(index);
        let value = values[index];
        
        if value.dictionary != nil {
            let dict = value;
            
            if dict["x"].double != nil {
                x = Double((dict["x"].doubleValue));
            }
            
            if dict["y"].number != nil {
                entry = ChartDataEntry(x: x, y: dict["y"].doubleValue, data: dict as AnyObject?);
            } else {
                fatalError("invalid data " + values.description);
            }
            
            
        } else if value.double != nil {
            entry = ChartDataEntry(x: x, y: value.doubleValue);
        } else {
            fatalError("invalid data " + values.description);
        }
        
        return entry;
    }
}
