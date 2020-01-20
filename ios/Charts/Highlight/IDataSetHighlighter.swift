import Foundation
import CoreGraphics

@objc(IChartDataSetHighlighter)
public protocol IDataSetHighlighter: class
{
    /// - Parameters:
    ///   - x:
    ///   - y:
    /// - Returns the index corresponding to the highlighted DataSet near the given x- and y- touch positions in pixels.
    func getDataSetIndexHighlight(x: CGFloat, y: CGFloat) -> Int
}
