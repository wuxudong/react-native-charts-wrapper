//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation

import SwiftyJSON
import Charts

class BarDataExtract : DataExtract {
    override open func createData() -> ChartData {
        return BarChartData();
    }
    
    override open func dataConfig(_ data: ChartData, config: JSON) {
    
        let barData = data as! BarChartData
      
        if config["barWidth"].double != nil {
            barData.barWidth = config["barWidth"].doubleValue
        }
        
        if config["group"].exists() {
            let fromX = config["group"]["fromX"].doubleValue;
            let groupSpace = config["group"]["groupSpace"].doubleValue;
            let barSpace = config["group"]["barSpace"].doubleValue;
            
            barData.groupBars(fromX: fromX, groupSpace: groupSpace, barSpace: barSpace)
        }
        
       
    }
    
    override open func createDataSet(_ entries: [ChartDataEntry]?, label: String?) -> IChartDataSet {
        return BarChartDataSet(values: entries, label: label)
    }
    
    override open func dataSetConfig(_ dataSet: IChartDataSet, config: JSON) {
        let barDataSet = dataSet as! BarChartDataSet
        
        ChartDataSetConfigUtils.commonConfig(barDataSet, config: config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(barDataSet, config: config);
        
        if config["barShadowColor"].int != nil {
            barDataSet.barShadowColor = RCTConvert.uiColor(config["barShadowColor"].intValue)
        }
        
        if config["highlightAlpha"].number != nil {
            barDataSet.highlightAlpha = BridgeUtils.toIOSAlpha(config["highlightAlpha"].numberValue)
        }
        
        if config["stackLabels"].array != nil {
            barDataSet.stackLabels = config["stackLabels"].arrayValue.map({ return $0.stringValue })
        }
    }
    
    override func createEntry(_ values: [JSON], index: Int) -> BarChartDataEntry {
        var entry: BarChartDataEntry;
        
        var x = Double(index);
        let value = values[index];
        
        if value.dictionary != nil {
            let dict = value;
            
            if dict["x"].double != nil {
                x = Double(dict["x"].doubleValue);
            }
            
            if dict["y"].array != nil {
                entry = BarChartDataEntry(x: x, yValues: (dict["y"].arrayValue.map({ y in y.doubleValue })));
            } else if dict["y"].number != nil {
                entry = BarChartDataEntry(x: x, y: dict["y"].doubleValue);
            } else {
                fatalError("invalid data " + values.description);
            }
            
            entry.data = dict as AnyObject?;
        } else if value.array != nil {
            entry = BarChartDataEntry(x: x, yValues: (value.arrayValue.map({ y in y.doubleValue })));
        } else if value.double != nil {
            entry = BarChartDataEntry(x: x, y: value.doubleValue);
        } else {
            fatalError("invalid data " + values.description);
        }
        
        return entry;
    }
}
