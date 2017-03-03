//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import Charts
import SwiftyJSON

class RNPieChartView: RNChartViewBase {
    let _chart: PieChartView;
    let _dataExtract : PieDataExtract;

    override var chart: PieChartView {
        return _chart
    }
    
    override var dataExtract: DataExtract {
        return _dataExtract
    }
    

    override init(frame: CoreGraphics.CGRect) {

        self._chart = PieChartView(frame: frame)
        self._dataExtract = PieDataExtract()

        super.init(frame: frame)

        self.addSubview(_chart)

    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func setDrawSliceText(_ enabled: Bool) {
        chart.drawEntryLabelsEnabled = enabled
    }
    
    
    func setUsePercentValues(_ enabled: Bool) {
        chart.usePercentValuesEnabled = enabled
    }
    
    
    func setCenterText(_ text: String) {
        chart.centerText = text
    }
    
    
    func setCenterTextRadiusPercent(_ radiusPercent: NSNumber) {
        chart.centerTextRadiusPercent = CGFloat(radiusPercent) / 100.0
    }
    
    
    func setHoleRadius(_ percent: NSNumber) {
        chart.holeRadiusPercent = CGFloat(percent) / 100.0
    }
    
    
    func setHoleColor(_ color: Int) {
        chart.holeColor = RCTConvert.uiColor(color)
    }
    
    
    func setTransparentCircleRadius(_ percent: NSNumber) {
        chart.transparentCircleRadiusPercent = CGFloat(percent) / 100.0
    }
        
    func setTransparentCircleColor(_ color: Int) {
        chart.transparentCircleColor = RCTConvert.uiColor(color)
    }
    
    func setMaxAngle(_ maxAngle: NSNumber) {
        chart.maxAngle = CGFloat(maxAngle)
    }

}
