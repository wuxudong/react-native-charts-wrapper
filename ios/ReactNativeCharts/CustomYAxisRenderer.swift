//
//  CustomYAxisRenderer.swift
//  RNCharts
//
//  Created by Taylor Johnson on 6/11/20.
//
import Foundation
import Charts
import SwiftyJSON

/**
 * Adds support for rendering YAxis labels with outer stroke
 */
open class CustomYAxisRenderer : YAxisRenderer {
	open var labelStrokeWidth: NSNumber?
	open var labelStrokeColor = UIColor.white

	public init(viewPortHandler: ViewPortHandler, yAxis: YAxis?, transformer: Transformer?, config: JSON)
	{
	    super.init(viewPortHandler: viewPortHandler, yAxis: yAxis, transformer: transformer)
		if config["strokeColor"].number != nil {
			labelStrokeColor = RCTConvert.uiColor(config["strokeColor"].numberValue)
		}
		if config["strokeWidth"].number != nil {
			labelStrokeWidth = config["strokeWidth"].numberValue
		}
	}

	open override func renderAxisLabels(context: CGContext)
	{
			guard let yAxis = self.axis as? YAxis else { return }

			if !yAxis.isEnabled || !yAxis.isDrawLabelsEnabled
			{
					return
			}

			let xoffset = yAxis.xOffset
			let yoffset = yAxis.labelFont.lineHeight / 2.5 + yAxis.yOffset

			let dependency = yAxis.axisDependency
			let labelPosition = yAxis.labelPosition

			var xPos = CGFloat(0.0)

			var textAlign: NSTextAlignment

			if dependency == .left
			{
					if labelPosition == .outsideChart
					{
							textAlign = .right
							xPos = viewPortHandler.offsetLeft - xoffset
					}
					else
					{
							textAlign = .left
							xPos = viewPortHandler.offsetLeft + xoffset
					}

			}
			else
			{
					if labelPosition == .outsideChart
					{
							textAlign = .left
							xPos = viewPortHandler.contentRight + xoffset
					}
					else
					{
							textAlign = .right
							xPos = viewPortHandler.contentRight - xoffset
					}
			}

			drawYLabels(
					context: context,
					fixedPosition: xPos,
					positions: transformedPositions(),
					offset: yoffset - yAxis.labelFont.lineHeight,
					textAlign: textAlign)
	}

	/// draws the y-labels on the specified x-position
	open func drawYLabels(
			context: CGContext,
			fixedPosition: CGFloat,
			positions: [CGPoint],
			offset: CGFloat,
			textAlign: NSTextAlignment)
	{
			guard
					let yAxis = self.axis as? YAxis
					else { return }

			let labelFont = yAxis.labelFont
			let labelTextColor = yAxis.labelTextColor

		let topLabelIndex = yAxis.entryCount - 1

			let from = yAxis.isDrawBottomYLabelEntryEnabled ? 0 : 1
		let topLabelPositon = positions[topLabelIndex].y + offset

		let to = yAxis.isDrawTopYLabelEntryEnabled && topLabelPositon >= viewPortHandler.contentTop ? yAxis.entryCount : (yAxis.entryCount - 1)

			for i in stride(from: from, to: to, by: 1)
			{
					let text = yAxis.getFormattedLabel(i)

				if (labelStrokeWidth != nil) {
					ChartUtils.drawText(
							context: context,
							text: text,
							point: CGPoint(x: fixedPosition, y: positions[i].y + offset),
							align: textAlign,
						attributes: [.font: labelFont, .strokeColor: labelStrokeColor, .strokeWidth: labelStrokeWidth as Any]
					)
				}

					ChartUtils.drawText(
							context: context,
							text: text,
							point: CGPoint(x: fixedPosition, y: positions[i].y + offset),
							align: textAlign,
							attributes: [.font: labelFont, .foregroundColor: labelTextColor]
					)
			}
	}
	
	
}
