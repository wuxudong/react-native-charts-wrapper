//
//  PercentValueFormatter.swift
//  FondyPortal
//
//  Created by Valentine Beregovoy on 8/20/17.
//  Copyright © 2017 Facebook. All rights reserved.
//

import Foundation
import Charts

open class PercentValueFormatter : NSObject, IValueFormatter {
    open func stringForValue(_ value: Double,
                                      entry: ChartDataEntry,
                                      dataSetIndex: Int,
                                      viewPortHandler: ViewPortHandler?) -> String
    {
        return String(format: "%.2f%%", value);
    }
}
