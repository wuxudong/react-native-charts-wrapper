//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import Charts
import SwiftyJSON

class RNRadarChartView: RNYAxisChartViewBase {
    
    let _chart: RadarChartView;
    let _dataExtract : RadarDataExtract
    
    override var chart: RadarChartView {
        return _chart
    }
    
    override var dataExtract: DataExtract {
        return _dataExtract
    }
    
    override init(frame: CoreGraphics.CGRect) {
        
        self._chart = RadarChartView(frame: frame)
        self._dataExtract = RadarDataExtract()
        
        super.init(frame: frame)
      
        self._chart.delegate = self
        self.addSubview(_chart)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
 
    func setSkipWebLineCount(_ count: Int) {
        _chart.skipWebLineCount = count
    }
    
    func setRotationEnabled(_ enabled: Bool) {
        _chart.rotationEnabled = enabled
    }
}
