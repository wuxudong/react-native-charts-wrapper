import Foundation
import DGCharts

open class LabelByXValueFormatter: NSObject, ValueFormatter, AxisValueFormatter {

    open var labels = [Double : String]()

    public override init() {

    }

    public init(_ labels: [Double : String]) {
        self.labels = labels;
    }

    fileprivate func format(_ value: Double) -> String
    {
        return self.labels[value] ?? ""
    }

    open func stringForValue(
        _ value: Double, axis: AxisBase?) -> String
    {
        return format(value)
    }

    open func stringForValue(
        _ value: Double,
        entry: ChartDataEntry,
        dataSetIndex: Int,
        viewPortHandler: ViewPortHandler?) -> String
    {
        return format(entry.x)
    }
}
