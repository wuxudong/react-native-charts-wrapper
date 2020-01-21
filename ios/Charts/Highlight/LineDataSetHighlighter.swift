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
        guard
            let pos = getValsForTouch(x: x, y: y),
            let dataSets = chart?.data?.dataSets else {
                return -1
        }
        
        let xVal = Double(pos.x)
        let yVal = Double(pos.y)
        let x = Double(x)
        var minDistance = Double.infinity
        var minIndex: Int = -1
        
        for i in 0 ..< dataSets.count {
            if dataSets[i].isVisible {
                if let result = dataSets[i].extremesEntriesForXValue(xVal) {
                    let x_1 = result[0].x
                    let x_2 = result[1].x
                    
                    let y_1 = result[0].y
                    let y_2 = result[1].y
                    
                    var distance: Double
                    if x_2 - x_1 == 0 {
                        distance = fabs(x_1 - x)
                    }
                    else {
                        let m = (y_2 - y_1) / (x_2 - x_1)
                        let q = (x_2 * y_1 - x_1 * y_2) / (x_2 - x_1)
                        distance = (fabs(yVal - (m * xVal + q)) / sqrt(1 + m * m))
                    }
                    
                    if distance < minDistance {
                        minDistance = distance
                        minIndex = i
                    }
                }
            }
        }
        
        return minIndex
    }
    
    /// - Parameters:
    ///   - x:
    /// - Returns: The corresponding x-pos for a given touch-position in pixels.
    open func getValsForTouch(x: CGFloat, y: CGFloat) -> CGPoint?
    {        
        // take any transformer to determine the values
        return chart?.getTransformer(forAxis: .left).valueForTouchPoint(x: x, y: y)
    }
    
    internal var data: ChartData?
    {
        return chart?.data
    }
}
