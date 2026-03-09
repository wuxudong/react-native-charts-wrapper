//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import DGCharts
import SwiftyJSON

class RNScatterChartView: RNBarLineChartViewBase {
    let _chart: ScatterChartView
    let _dataExtract : ScatterDataExtract

    override var chart: ScatterChartView {
        return _chart
    }

    override var dataExtract: DataExtract {
        return _dataExtract
    }

    override init(frame: CoreGraphics.CGRect) {

        self._chart = ScatterChartView(frame: frame);
        self._dataExtract = ScatterDataExtract()

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
