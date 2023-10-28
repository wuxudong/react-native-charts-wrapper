import Foundation
import DGCharts

open class CircleMarker: MarkerImage
{
  open var color: UIColor
  open var strokeColor: UIColor
  open var strokeSize: Int

  public init(
    color: UIColor,
    strokeColor: UIColor,
    size: CGSize,
    strokeSize: Int
  )
  {
    self.color = color
    self.strokeColor = strokeColor
    self.strokeSize = strokeSize

    super.init()

    self.size = size
  }

  open override func offsetForDrawing(atPoint point: CGPoint) -> CGPoint
  {
    var offset = self.offset
    var size = self.size

    if size.width == 0.0 && image != nil
    {
      size.width = image!.size.width
    }
    if size.height == 0.0 && image != nil
    {
      size.height = image!.size.height
    }

    let width = size.width
    let height = size.height
    let padding: CGFloat = 8.0

    var origin = point
    origin.x -= width / 2
    origin.y -= height

    if origin.x + offset.x < 0.0
    {
      offset.x = -origin.x + padding
    }
    else if let chart = chartView,
      origin.x + width + offset.x > chart.bounds.size.width
    {
      offset.x = chart.bounds.size.width - origin.x - width - padding
    }

    if origin.y + offset.y < 0
    {
      offset.y = height + padding;
    }
    else if let chart = chartView,
      origin.y + height + offset.y > chart.bounds.size.height
    {
      offset.y = chart.bounds.size.height - origin.y - height - padding
    }

    return offset
  }

  open override func draw(context: CGContext, point: CGPoint)
  {
    let offset = self.offsetForDrawing(atPoint: point)
    let color = self.color;
    let size = self.size
    let strokeSize = self.strokeSize
    let strokeColor = self.strokeColor;

    var rect = CGRect(
      origin: CGPoint(
        x: point.x + offset.x,
        y: point.y + offset.y
      ),
      size: size
    )
    rect.origin.x -= size.width / 2.0
    rect.origin.y -= size.height / 2.0

    context.saveGState()

    context.setFillColor(color.cgColor)
    context.setLineWidth(CGFloat(strokeSize))
    context.setStrokeColor(strokeColor.cgColor)

    context.addEllipse(in: rect)
    context.drawPath(using: .fillStroke)

    UIGraphicsPushContext(context)

    UIGraphicsPopContext()

    context.restoreGState()
  }

  open override func refreshContent(entry: ChartDataEntry, highlight: Highlight)
  {
  }
}
