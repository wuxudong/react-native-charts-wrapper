//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import Charts
import SwiftyJSON

class RNPieChartView: RNChartViewBase {
    let _chart: PieChartView;
    let _dataExtract : PieDataExtract;

    override var chart: PieChartView {
        return _chart
    }

    override var dataExtract: DataExtract {
        return _dataExtract
    }

    override init(frame: CoreGraphics.CGRect) {

        self._chart = PieChartView(frame: frame)
        self._dataExtract = PieDataExtract()

        super.init(frame: frame)

        self._chart.delegate = self
        self.addSubview(_chart)

    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func setDrawSliceText(_ enabled: Bool) {
        chart.drawEntryLabelsEnabled = enabled
    }


    func setUsePercentValues(_ enabled: Bool) {
        chart.usePercentValuesEnabled = enabled
    }


    func setCenterText(_ text: String) {
        chart.centerText = text
    }

    func setStyledCenterText(_ data: NSDictionary) {
        let json = BridgeUtils.toJson(data)

        var attrString: NSMutableAttributedString?
        if json["text"].string == nil
        {
            attrString = nil
        }
        else
        {
            #if os(OSX)
                let paragraphStyle = NSParagraphStyle.default().mutableCopy() as! NSMutableParagraphStyle
            #else
                let paragraphStyle = NSParagraphStyle.default.mutableCopy() as! NSMutableParagraphStyle
            #endif
            paragraphStyle.lineBreakMode = NSLineBreakMode.byTruncatingTail
            paragraphStyle.alignment = .center


            var color : NSUIColor?
            if json["color"].int != nil {
                color = RCTConvert.uiColor(json["color"].intValue)
            } else {
                color = UIColor.black
            }

            let fontSize = json["size"].float != nil ? CGFloat(json["size"].floatValue) : CGFloat(12)

            var font: UIFont?
            if let parsedFont = FontUtils.getFont(json) {
                font = RCTFont.update(parsedFont, withSize: NSNumber(value: Float(fontSize)))
            } else {
                font = NSUIFont.systemFont(ofSize: fontSize)
            }

            attrString = NSMutableAttributedString(string: json["text"].stringValue)
            attrString?.setAttributes([
                NSAttributedString.Key.foregroundColor: color!,
                NSAttributedString.Key.font: font,
                NSAttributedString.Key.paragraphStyle: paragraphStyle
                ], range: NSMakeRange(0, attrString!.length))
        }

        chart.centerAttributedText = attrString

    }

    func setCenterTextRadiusPercent(_ radiusPercent: NSNumber) {
        chart.centerTextRadiusPercent = CGFloat(truncating: radiusPercent) / 100.0
    }


    func setHoleRadius(_ percent: NSNumber) {
        chart.holeRadiusPercent = CGFloat(truncating: percent) / 100.0
    }


    func setHoleColor(_ color: Int) {
        chart.holeColor = RCTConvert.uiColor(color)
    }


    func setTransparentCircleRadius(_ percent: NSNumber) {
        chart.transparentCircleRadiusPercent = CGFloat(truncating: percent) / 100.0
    }

    func setTransparentCircleColor(_ color: Int) {
        chart.transparentCircleColor = RCTConvert.uiColor(color)
    }

    func setEntryLabelColor(_ color: Int) {
        chart.entryLabelColor = RCTConvert.uiColor(color)
    }

    func setEntryLabelTextSize(_ size: NSNumber) {
        chart.entryLabelFont = chart.entryLabelFont?.withSize(CGFloat(truncating: size))
    }

    func setEntryLabelFontFamily(_ fontFamily: String) {
        chart.entryLabelFont = RCTFont.update(chart.entryLabelFont, withFamily: fontFamily)
    }

    func setExtraOffsets(_ data: NSDictionary) {
        let json = BridgeUtils.toJson(data)
        var leftOffset : CGFloat = 0
        var topOffset : CGFloat = 0
        var rightOffset : CGFloat = 0
        var bottomOffset : CGFloat = 0

        if json["left"].float != nil
        {
            leftOffset = CGFloat(json["left"].floatValue)
        }
        if json["top"].float != nil
        {
            topOffset = CGFloat(json["top"].floatValue)
        }
        if json["right"].float != nil
        {
            rightOffset = CGFloat(json["right"].floatValue)
        }
        if json["bottom"].float != nil
        {
            bottomOffset = CGFloat(json["bottom"].floatValue)
        }

        chart.setExtraOffsets(left: leftOffset, top: topOffset, right: rightOffset, bottom: bottomOffset)
    }

    func setDrawEntryLabels(_ enabled: Bool) {
        chart.drawEntryLabelsEnabled = enabled
    }

    func setMaxAngle(_ maxAngle: NSNumber) {
        chart.maxAngle = CGFloat(truncating: maxAngle)
    }

    func setMinOffset(_ minOffset: NSNumber) {
        chart.minOffset = CGFloat(truncating: minOffset)
    }

    func setRotationEnabled(_ enabled: Bool) {
        chart.rotationEnabled = enabled
    }

    func setRotationAngle(_ angle: NSNumber) {
        chart.rotationAngle = CGFloat(truncating: angle)
    }

    override func didSetProps(_ changedProps: [String]!) {
        super.didSetProps(changedProps)

        let pieChartDataSet = chart.data?.dataSets[0] as? PieChartDataSet

        pieChartDataSet?.entryLabelColor = chart.entryLabelColor
        pieChartDataSet?.entryLabelFont = chart.entryLabelFont
    }
}
