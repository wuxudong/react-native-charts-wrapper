//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation
import Charts
import SwiftyJSON

open class DataExtract {
    func extract(_ data: JSON) -> ChartData?  {
        if data["dataSets"].array == nil {
            return nil;
        }
        
        let chartData = createData();
        
        
        let dataSets = data["dataSets"].arrayValue;
        
        for (_, dataSet) in dataSets.enumerated() {
            
            let values = dataSet["values"].arrayValue;
            let label = dataSet["label"].stringValue;
            
            let entries = createEntries(values);
            
            let chartDataSet = createDataSet(entries, label: label);
            
            if dataSet["config"].dictionary != nil {
                dataSetConfig(chartDataSet, config: dataSet["config"])
            }
            
            chartData.addDataSet(chartDataSet);
        }
        
        if data["config"].dictionary != nil {
            dataConfig(chartData, config: data["config"])
        }

        
        return chartData

    }
    
    func createEntries(_ values: [JSON]) -> [ChartDataEntry] {
        var entries = [ChartDataEntry]();
        
        for (index, value) in values.enumerated() {
            if value.null == nil {
                entries.append(createEntry(values, index: index))
            }
        }
        
        return entries;
        
    }

    
    func createData() -> ChartData {
        fatalError("subclass should override this function")
    }
    
    func dataConfig(_ data: ChartData, config: JSON) {}
    
    func createDataSet(_ entries: [ChartDataEntry]?, label: String?) -> IChartDataSet {
        fatalError("subclass should override this function")
    }
    
    func dataSetConfig(_ dataSet: IChartDataSet, config: JSON) {
        fatalError("subclass should override this function")
    }
    
    func createEntry(_ values: [JSON], index: Int) -> ChartDataEntry {
        fatalError("subclass should override this function")
    }

    
}
