//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import Charts
import SwiftyJSON

class RNCombinedChartView: RNBarLineChartViewBase {

    let _chart: CombinedChartView;
    let _dataExtract : CombinedDataExtract;

    override var chart: CombinedChartView {
        return _chart
    }
    
    override var dataExtract: DataExtract {
        return _dataExtract
    }    

    override init(frame: CoreGraphics.CGRect) {

        self._chart = CombinedChartView(frame: frame)
        self._dataExtract = CombinedDataExtract()

        super.init(frame: frame)

        self._chart.delegate = self
        self.addSubview(_chart)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setDrawValueAboveBar(_ enabled: Bool) {
        _chart.drawValueAboveBarEnabled = enabled
    }

    func setDrawBarShadow(_ enabled: Bool) {
        _chart.drawBarShadowEnabled = enabled
    }

}
