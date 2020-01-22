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

    @objc public func getDataSetIndexHighlight(x: CGFloat, y: CGFloat) -> DataSetHighlight
    {
        guard
            let pos = getValsForTouch(x: x, y: y),
            let dataSets = chart?.data?.dataSets else {
                return DataSetHighlight(index: -1)
        }
                
        let xVal = Double(pos.x)
        let yVal = Double(pos.y)

        var minDistance = Double.infinity
        var minIndex: Int = -1
        var minM: Double?
        var minQ: Double?
        
        for i in 0 ..< dataSets.count {
            if dataSets[i].isVisible {
                if let result = dataSets[i].extremesEntriesForXValue(xVal) {
                    let x_1 = result[0].x
                    let x_2 = result[1].x
                    
                    let y_1 = result[0].y
                    let y_2 = result[1].y
                    
                    var distance: Double
                    let m: Double
                    let q: Double
                    if x_2 - x_1 == 0 {
                        m = .infinity
                        q = x_1
                        distance = fabs(x_1 - xVal)
                    }
                    else if y_2 - y_1 == 0 {
                        m = 0
                        q = y_1
                        distance = fabs(y_1 - yVal)
                    }
                    else {
                        m = (y_2 - y_1) / (x_2 - x_1)
                        q = (x_2 * y_1 - x_1 * y_2) / (x_2 - x_1)
                        distance = (fabs(yVal - (m * xVal + q)) / sqrt(1 + m * m))
                    }
                    
                    if distance < minDistance {
                        minDistance = distance
                        minIndex = i
                        minM = m
                        minQ = q
                    }
                }
            }
        }
        
        guard let m1 = minM, let q1 = minQ else {
            return DataSetHighlight(index: -1);
        }
        
        let xIntersection: Double
        let yIntersection: Double
        if m1 == .infinity {
            xIntersection = q1
            yIntersection = yVal
        }
        else if m1 == 0 {
            xIntersection = xVal
            yIntersection = q1
        }
        else {
            let m2 = -1/m1
            let q2 = xVal/m1 + yVal
            xIntersection = (q1 - q2) / (m2 - m1)
            yIntersection = xIntersection * m2 + q2
        }
        guard let pix = getPixForVal(x: xIntersection, y: yIntersection) else {
            return DataSetHighlight(index: -1);
        }
        
        let dist = sqrt((xVal - xIntersection) * (xVal - xIntersection) + (yVal - yIntersection) * (yVal - yIntersection))
        let pixDist = sqrt((x - pix.x) * (x - pix.x) + (y - pix.y) * (y - pix.y))
        return DataSetHighlight(index: minIndex, pixelDistance: CGFloat(pixDist), pointDistance: CGFloat(dist))
    }
    
    /// - Parameters:
    ///   - x:
    /// - Returns: The corresponding x-pos for a given touch-position in pixels.
    open func getValsForTouch(x: CGFloat, y: CGFloat) -> CGPoint?
    {        
        // take any transformer to determine the values
        return chart?.getTransformer(forAxis: .left).valueForTouchPoint(x: x, y: y)
    }

    open func getPixForVal(x: Double, y: Double) -> CGPoint?
    {        
        // take any transformer to determine the values
        return chart?.getTransformer(forAxis: .left).pixelForValues(x: x, y: y)
    }
    
    internal var data: ChartData?
    {
        return chart?.data
    }
}
