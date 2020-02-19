import Foundation
import Charts

open class ConfigurableMinimumLinePositionFillFormatter: NSObject, IFillFormatter {

    open var min = CGFloat(0)

    public override init() {

    }

    public init(_ min: CGFloat) {
        self.min = min;
    }

    open func getFillLinePosition(dataSet: ILineChartDataSet, dataProvider: LineChartDataProvider) -> CGFloat {
        return min
    }
}
