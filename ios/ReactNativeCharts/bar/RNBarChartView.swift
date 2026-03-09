//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import DGCharts
import SwiftyJSON

class RNBarChartView: RNBarChartViewBase {

    let _chart: BarChartView
    let _dataExtract : BarDataExtract

    override var chart: BarChartView {
        return _chart
    }

    override var dataExtract: DataExtract {
        return _dataExtract
    }

    override init(frame: CoreGraphics.CGRect) {

        self._chart = BarChartView(frame: frame)
        self._dataExtract = BarDataExtract()

        super.init(frame: frame)

        self._chart.delegate = self
        self.addSubview(_chart)
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        _chart.frame = self.bounds // Adjust the chart's frame to fill the entire component's bounds
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
