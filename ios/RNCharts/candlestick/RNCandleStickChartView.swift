//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import Charts
import SwiftyJSON

class RNCandleStickChartView: RNBarLineChartViewBase {
    
    let _chart: CandleStickChartView;
    let _dataExtract : CandleDataExtract;
    
    override var chart: ChartViewBase {
        return _chart
    }
    
    override var dataExtract: DataExtract {
        return _dataExtract
    }
    
    
    override init(frame: CoreGraphics.CGRect) {
        
        self._chart = CandleStickChartView(frame: frame)
        self._dataExtract = CandleDataExtract()
        
        super.init(frame: frame);
      
        self._chart.delegate = self
        self.addSubview(_chart);
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    

}
