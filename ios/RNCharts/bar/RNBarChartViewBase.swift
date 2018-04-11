//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import Charts
import SwiftyJSON

class RNBarChartViewBase: RNBarLineChartViewBase {

    fileprivate var barChart: BarChartView {
        get {
            return chart as! BarChartView
        }
    }

    func setDrawValueAboveBar(_ enabled: Bool) {
        barChart.drawValueAboveBarEnabled = enabled
    }

    func setDrawBarShadow(_ enabled: Bool) {
        barChart.drawBarShadowEnabled = enabled
    }
}
