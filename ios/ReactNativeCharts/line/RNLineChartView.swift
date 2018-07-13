//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import Charts
import SwiftyJSON

class RNLineChartView: RNBarLineChartViewBase {
    let _chart: LineChartView;
    let _dataExtract : LineDataExtract;
    
    override var chart: LineChartView {
        return _chart
    }
    
    override var dataExtract: DataExtract {
        return _dataExtract
    }
    
    override func setData(_ data: NSDictionary) {
    let json = BridgeUtils.toJson(data)
    chart.data = dataExtract.extract(json)
    chart.setVisibleXRangeMinimum(10*60) // 10mn
    chart.setVisibleXRangeMaximum(12*3600) // 12h
    
    }
    
    override init(frame: CoreGraphics.CGRect) {
      
      
        self._chart = LineChartView(frame: frame)
        self._dataExtract = LineDataExtract()
      
        super.init(frame: frame);
      
        let gestureRecognizer = UILongPressGestureRecognizer(target: self, action: #selector(chartViewLongPress(gesture:)))
        gestureRecognizer.minimumPressDuration = 0.7;

        self.addGestureRecognizer(gestureRecognizer);
      

        self._chart.delegate = self
        self.addSubview(_chart);
      
      
      
      
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
