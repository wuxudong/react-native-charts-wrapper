//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import Foundation
import CoreGraphics

class RNBubbleChartView: RNBarLineChartViewBase {
    let _chart: BubbleChartView
    let _dataExtract : BubbleDataExtract

    override var chart: BubbleChartView {
        return _chart
    }
    
    override var dataExtract: DataExtract {
        return _dataExtract
    }
    

    override init(frame: CGRect) {

        self._chart = BubbleChartView(frame: frame)
        self._dataExtract = BubbleDataExtract()

        super.init(frame: frame)
      
        self._chart.delegate = self
        self.addSubview(_chart)

    }

    required public init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
