//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import Charts
import SwiftyJSON

class RNLineChartView: RNBarLineChartViewBase {
    let _chart: LineChartView;
    let _dataExtract : LineDataExtract;    
    var topOffset: Double = 0
    
    override var chart: LineChartView {
        return _chart
    }
    
    override var dataExtract: DataExtract {
        return _dataExtract
    }
    
    override init(frame: CoreGraphics.CGRect) {
        
        self._chart = LineChartView(frame: frame)
        self._dataExtract = LineDataExtract()
        
        super.init(frame: frame);
        
        self._chart.delegate = self
        self.addSubview(_chart);
        
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func setYAxis(_ config: NSDictionary) {
        super.setYAxis(config)

        let propMap = config as! Dictionary<String, Any>
        if let left = propMap["left"] as? Dictionary<String, Any> {
            topOffset = left["topOffset"] as? Double ?? 0
        }
    }
    
    func addDataPoints(_ data: NSDictionary) {
        let dict = data as! Dictionary<String, Any>
        let dataSets = dict["data"] as! Array<Dictionary<String, Any>>
        
        var maxYPoint = chart.leftAxis.axisMaximum
        
        for i in 0 ..< dataSets.count {
            let point = dataSets[i]
            let x = point["x"] as! Double
            let y = point["y"] as! Double
            let lineData = chart.data!.dataSets[i]
            _ = lineData.addEntry(ChartDataEntry(x: x, y: y))
            if y > maxYPoint - topOffset {
                maxYPoint = y + topOffset
            }
        }
        
        chart.leftAxis.axisMaximum = maxYPoint
        chart.notifyDataSetChanged()
    }

    func updateConfig(_ data: NSArray) {
        let arr = data as! Array<Dictionary<String, Any>>
        
        for line in arr {
            
            let id = line["id"] as! Int;
            let config = line["config"] as! Dictionary<String, Any>;
            let lineData = chart.data!.dataSets[id] as! LineChartDataSet
            
            if let visible = config["visible"] as? Bool {
                lineData.visible = visible
            }
            if let lineWidth = config["lineWidth"] as? CGFloat {
                lineData.lineWidth = lineWidth
            }
        }

        chart.notifyDataSetChanged()
    }

}
