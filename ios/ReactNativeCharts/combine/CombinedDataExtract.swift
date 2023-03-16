//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation

import SwiftyJSON
import Charts

class CombinedDataExtract : DataExtract {
    let lineDataExtract = LineDataExtract()
    let barDataExtract = BarDataExtract()
    let scatterDataExtract = ScatterDataExtract()
    let candleDataExtract = CandleDataExtract()
    let bubbleDataExtract = BubbleDataExtract()

    override func extract(_ data: JSON) -> ChartData?  {
        let chartData = CombinedChartData();


        if data["lineData"] != JSON.null {
            if let lineData = lineDataExtract.extract(data["lineData"]) as? LineChartData {
                chartData.lineData = lineData
            }
        }

        if data["barData"] != JSON.null {
            if let barData = barDataExtract.extract(data["barData"]) as? BarChartData {
                chartData.barData = barData
            }
        }


        if data["scatterData"] != JSON.null {
            if let scatterData = scatterDataExtract.extract(data["scatterData"]) as? ScatterChartData {
                chartData.scatterData = scatterData
            }
        }


        if data["candleData"] != JSON.null {
            if let candleData = candleDataExtract.extract(data["candleData"]) as? CandleChartData {
                chartData.candleData = candleData
            }
        }


        if data["bubbleData"] != JSON.null {
            if let bubbleData = bubbleDataExtract.extract(data["bubbleData"]) as? BubbleChartData {
                chartData.bubbleData = bubbleData
            }
        }
        return chartData

    }

    override func createEntries(_ values: [JSON]) -> [ChartDataEntry] {
        fatalError("not support")
    }


    override func createData() -> ChartData {
        fatalError("not support")
    }

    override func createDataSet(_ entries: [ChartDataEntry], label: String) -> ChartDataSetProtocol {
        fatalError("not support")
    }

    override func dataSetConfig(_ dataSet: ChartDataSetProtocol, config: JSON) {
        fatalError("not support")
    }

    override func createEntry(_ values: [JSON], index: Int) -> ChartDataEntry {
        fatalError("not support")
    }




}
