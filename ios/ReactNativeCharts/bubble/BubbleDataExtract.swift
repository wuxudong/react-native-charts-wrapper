//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation

import SwiftyJSON
import Charts

class BubbleDataExtract : DataExtract {
    override func createData() -> ChartData {
        return BubbleChartData();
    }
    
    override func createDataSet(_ entries: [ChartDataEntry]?, label: String?) -> IChartDataSet {
        return BubbleChartDataSet(values: entries, label: label)
    }
    
    override func dataSetConfig(_ dataSet: IChartDataSet, config: JSON) {
        let bubbleDataSet = dataSet as! BubbleChartDataSet;
        
        ChartDataSetConfigUtils.commonConfig(bubbleDataSet, config: config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(bubbleDataSet, config: config)
        
        // BubbleDataSet only config
        if config["highlightCircleWidth"].number != nil {
            bubbleDataSet.highlightCircleWidth = CGFloat(config["highlightCircleWidth"].numberValue)
        }
    }
    
    override func createEntry(_ values: [JSON], index: Int) -> ChartDataEntry {
        var entry: BubbleChartDataEntry;
        
        var x = Double(index);
        
        let item = values[index];
        
        if item.dictionary != nil {
            let dict = item;
            
            if dict["x"].double != nil {
                x = Double((dict["x"].doubleValue));
            }
            
            if dict["y"].double != nil && dict["size"].float != nil {
                entry = BubbleChartDataEntry(x: x, y: dict["y"].doubleValue,
                                             size: CGFloat(dict["size"].floatValue), data: dict as AnyObject?);
            } else {
                fatalError("invalid data " + values.description);
            }
        }   else {
            fatalError("invalid data " + values.description);
        }
        
        return entry;
    }

}
