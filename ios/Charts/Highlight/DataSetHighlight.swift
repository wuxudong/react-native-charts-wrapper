import Foundation
import CoreGraphics

@objc(ChartDataSetHighlight)
open class DataSetHighlight: NSObject
{
    @objc open var index: Int
    @objc open var pixelDistance: CGFloat
    @objc open var pointDistance: CGFloat
        
    @objc public init(index: Int, pixelDistance: CGFloat = CGFloat.infinity, pointDistance: CGFloat = CGFloat.infinity)
    {
        self.index = index;
        self.pixelDistance = pixelDistance;
        self.pointDistance = pointDistance;
        super.init()
    }
    
    // MARK: NSObject
    
    open override var description: String
    {
        return "DataSetHighlight, index: \(index), pixelDistance: \(pixelDistance), pointDataSet: \(pointDistance)"
    }
}


// MARK: Equatable
extension DataSetHighlight /*: Equatable*/ {
    open override func isEqual(_ object: Any?) -> Bool {
        guard let object = object as? DataSetHighlight else { return false }

        if self === object
        {
            return true
        }

        return index == object.index
    }
}
