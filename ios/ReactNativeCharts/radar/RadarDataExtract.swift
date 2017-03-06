//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation

import SwiftyJSON
import Charts

class RadarDataExtract : DataExtract {
    override func createData() -> ChartData {
        return RadarChartData();
    }
    
    override func createDataSet(_ entries: [ChartDataEntry]?, label: String?) -> IChartDataSet {
        return RadarChartDataSet(values : entries, label: label)
    }
    
    override func dataSetConfig(_ dataSet: IChartDataSet, config: JSON) {
        let barDataSet = dataSet as! RadarChartDataSet
        
        ChartDataSetConfigUtils.commonConfig(barDataSet, config: config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(barDataSet, config: config);
        ChartDataSetConfigUtils.commonLineRadarConfig(barDataSet, config: config);
    }
    
    override func createEntry(_ values: [JSON], index: Int) -> RadarChartDataEntry {
        var entry: RadarChartDataEntry;
        
        
        let item = values[index];
        
        if item.dictionary != nil {
            let dict = item;
            
            if dict["value"].double != nil {
                entry = RadarChartDataEntry(value: dict["value"].doubleValue, data: dict as AnyObject?);
            }  else {
                fatalError("invalid data " + values.description);
            }
            
        } else if item.double != nil {
            entry = RadarChartDataEntry(value: item.doubleValue);
        } else {
            fatalError("invalid data " + values.description);
        }
        
        return entry;
    }
}
