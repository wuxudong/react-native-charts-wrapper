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

    override func setYAxis(_ config: NSDictionary) {
        let json = BridgeUtils.toJson(config)
        let yAxis = _chart.yAxis

        setCommonAxisConfig(yAxis, config: json)
        setYAxisConfig(yAxis, config: json)
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
        chart.skipWebLineCount = count
    }

    func setMinOffset(_ minOffset: NSNumber) {
        chart.minOffset = CGFloat(truncating: minOffset)
    }

    func setRotationEnabled(_ enabled: Bool) {
        chart.rotationEnabled = enabled
    }

    func setRotationAngle(_ angle: NSNumber) {
        chart.rotationAngle = CGFloat(truncating: angle)
    }

    func setDrawWeb(_ enabled: Bool) {
        chart.drawWeb = enabled
    }
    
    func setWebLineWidth(_ width: NSNumber) {
        chart.webLineWidth = CGFloat(truncating: width)
    }

    func setWebLineWidthInner(_ width: NSNumber) {
        chart.innerWebLineWidth = CGFloat(truncating: width)
    }

    func setWebAlpha(_ alpha: NSNumber) {
        chart.webAlpha = CGFloat(truncating: alpha)
    }

    func setWebAlphaInner(_ alpha: NSNumber) {
        chart.webAlphaInner = CGFloat(truncating: alpha)
    }

    func setWebColor(_ color: NSNumber) {
        chart.webColor = RCTConvert.uiColor(color.intValue)
    }

    func setWebColorInner(_ color: NSNumber) {
        chart.innerWebColor = RCTConvert.uiColor(color.intValue)
    }
}
