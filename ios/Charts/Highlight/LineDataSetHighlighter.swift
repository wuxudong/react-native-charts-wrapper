import Foundation
import CoreGraphics

open class LineDataSetHighlighter : NSObject, IDataSetHighlighter
{
    /// instance of the data-provider
    @objc open weak var chart: LineChartDataProvider?
    
    @objc public init(chart: LineChartDataProvider)
    {
        self.chart = chart
    }

    @objc public func getDataSetIndexHighlight(x: CGFloat, y: CGFloat) -> Int
    {
        // TODO
        return -1;
    }
    
    /// - Parameters:
    ///   - x:
    /// - Returns: The corresponding x-pos for a given touch-position in pixels.
    open func getValsForTouch(x: CGFloat, y: CGFloat) -> CGPoint?
    {        
        // take any transformer to determine the values
        return chart?.getTransformer(forAxis: .left).valueForTouchPoint(x: x, y: y)
    }
    
    internal func getDistance(x1: CGFloat, y1: CGFloat, x2: CGFloat, y2: CGFloat) -> CGFloat
    {
        return hypot(x1 - x2, y1 - y2)
    }
    
    internal var data: ChartData?
    {
        return chart?.data
    }
}
