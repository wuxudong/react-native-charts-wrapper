//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation
import SwiftyJSON
import Charts

class CandleDataExtract : DataExtract {
    override func createData() -> ChartData {
        return CandleChartData();
    }
    
    override func createDataSet(_ entries: [ChartDataEntry]?, label: String?) -> IChartDataSet {
        return CandleChartDataSet(values: entries, label: label)
    }
    
    override func dataSetConfig(_ dataSet: IChartDataSet, config: JSON) {
        let candleDataSet = dataSet as! CandleChartDataSet
        
        ChartDataSetConfigUtils.commonConfig(candleDataSet, config: config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(candleDataSet, config: config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(candleDataSet, config: config);
        
        
        // CandleDataSet only config
        if config["barSpace"].number != nil {
            candleDataSet.barSpace = CGFloat(config["barShadowColor"].numberValue)
        }
        
        
        if config["shadowWidth"].number != nil {
            candleDataSet.shadowWidth = CGFloat(config["shadowWidth"].numberValue)
        }
        
        if config["shadowColor"].int != nil {
            candleDataSet.shadowColor = RCTConvert.uiColor(config["shadowColor"].intValue)
        }
        
        if config["shadowColorSameAsCandle"].bool != nil {
            candleDataSet.shadowColorSameAsCandle = config["shadowColorSameAsCandle"].boolValue;
        }
        
        if config["neutralColor"].int != nil {
            candleDataSet.neutralColor = RCTConvert.uiColor(config["neutralColor"].intValue)
        }
        
        
        if config["decreasingColor"].int != nil {
            candleDataSet.decreasingColor = RCTConvert.uiColor(config["decreasingColor"].intValue)
        }
        
        if config["increasingColor"].int != nil {
            candleDataSet.increasingColor = RCTConvert.uiColor(config["increasingColor"].intValue)
        }
        
        
        if config["decreasingPaintStyle"].string != nil {
            if config["decreasingPaintStyle"].stringValue.lowercased() == "stroke" {
                candleDataSet.decreasingFilled = false;
            } else {
                candleDataSet.decreasingFilled = true;
            }
        }
        
        
        if config["increasingPaintStyle"].string != nil {
            if config["increasingPaintStyle"].stringValue.lowercased() == "fill" {
                candleDataSet.increasingFilled = true;
            } else {
                candleDataSet.increasingFilled = false;
            }
        }
    }
    
    override func createEntry(_ values: [JSON], index: Int) -> CandleChartDataEntry {
        var entry: CandleChartDataEntry;
        
        var x = Double(index);
        let value = values[index];
        
        if value["x"].double != nil {
            x = Double(value["x"].doubleValue);
        }
        
        if value["shadowH"].double == nil || value["shadowL"].double == nil || value["open"].number == nil || value["close"].number == nil  {
            fatalError("invalid data " + values.description);
        }
        
        
        entry = CandleChartDataEntry(x: x,
                                     shadowH: value["shadowH"].doubleValue,
                                     shadowL: value["shadowL"].doubleValue,
                                     open: value["open"].doubleValue,
                                     close: value["close"].doubleValue,
                                     data: value as AnyObject?)
        
        return entry;
    }

}
