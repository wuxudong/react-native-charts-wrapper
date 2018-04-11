//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation

import SwiftyJSON
import Charts

class PieDataExtract : DataExtract {
    override func createData() -> ChartData {
        return PieChartData();
    }
    
    override func createDataSet(_ entries: [ChartDataEntry]?, label: String?) -> IChartDataSet {
        return PieChartDataSet(values: entries, label: label)
    }
    
    override func dataSetConfig(_ dataSet: IChartDataSet, config: JSON) {
        let pieDataSet = dataSet as! PieChartDataSet;
        
        ChartDataSetConfigUtils.commonConfig(pieDataSet, config: config);
        
        // PieDataSet only config
        if config["sliceSpace"].float != nil {
            pieDataSet.sliceSpace = CGFloat(config["sliceSpace"].floatValue)
        }
        
        if config["selectionShift"].float != nil {
            pieDataSet.selectionShift = CGFloat(config["selectionShift"].floatValue)
        }
        
        if config["xValuePosition"].string != nil {
          pieDataSet.xValuePosition = BridgeUtils.parsePieChartDataSetValuePosition(config["xValuePosition"].stringValue)
        }
      
        if config["yValuePosition"].string != nil {
          pieDataSet.yValuePosition = BridgeUtils.parsePieChartDataSetValuePosition(config["yValuePosition"].stringValue)
        }
    }
    
    override func createEntry(_ values: [JSON], index: Int) -> ChartDataEntry {
        var entry: PieChartDataEntry;
        
        let item = values[index];
        
        if item.dictionary != nil {
            let dict = item;
            
            var value : Double;
            if dict["value"].double != nil {
                value = Double((dict["value"].doubleValue));
            } else {
                fatalError("invalid data " + values.description);
            }
            
            if dict["label"].string != nil {
                entry = PieChartDataEntry(value: value, label: dict["label"].stringValue)
            } else {
                entry = PieChartDataEntry(value: value)
            }
            
            entry.data = dict as AnyObject?
        } else if item.double != nil {
            entry = PieChartDataEntry(value : item.doubleValue);
        } else {
            fatalError("invalid data " + values.description);
        }
        
        return entry;
    }
}
