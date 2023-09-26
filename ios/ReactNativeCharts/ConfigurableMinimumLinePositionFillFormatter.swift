import Foundation
import DGCharts

open class ConfigurableMinimumLinePositionFillFormatter: NSObject, FillFormatter {

    open var min = CGFloat(0)

    public override init() {

    }

    public init(_ min: CGFloat) {
        self.min = min;
    }

    open func getFillLinePosition(dataSet: LineChartDataSetProtocol, dataProvider: LineChartDataProvider) -> CGFloat {
        return min
    }
}
