//
//  LargeValueFormatter.swift
//  ChartsDemo
//  Copyright © 2016 dcg. All rights reserved.
//
import Foundation
import Charts

open class LargeValueFormatter: NSObject, IValueFormatter, IAxisValueFormatter
{
    fileprivate static let MAX_LENGTH = 5

    /// Suffix to be appended after the values.
    ///
    /// **default**: suffix: ["", "k", "m", "b", "t"]
    open var suffix = ["", "k", "m", "b", "t"]

    /// An appendix text to be added at the end of the formatted value.
    open var appendix: String?

    public override init()
    {

    }

    public init(appendix: String?)
    {
        self.appendix = appendix
    }

    fileprivate func format(_ value: Double) -> String
    {
        var sig = abs(value)
        let sign = value < 0 ? "-" : ""
        var length = 0
        let maxLength = suffix.count - 1

        while sig >= 1000.0 && length < maxLength
        {
            sig /= 1000.0
            length += 1
        }

        let valueFormatter = NumberFormatter()
        valueFormatter.maximumFractionDigits = 2
        valueFormatter.minimumFractionDigits = 0

        var r = sign + (valueFormatter.string(from: NSNumber(value: sig)) ?? "") + suffix[length]

        if appendix != nil
        {
            r += appendix!
        }

        return r
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
        return format(value)
    }
}
