//
//  PercentAxisFormatter.swift
//  FondyPortal
//
//  Created by Valentine Beregovoy on 8/20/17.
//  Copyright © 2017 Facebook. All rights reserved.
//

import Foundation
import Charts

open class PercentAxisFormatter : NSObject, IAxisValueFormatter {
  open func stringForValue(_ value: Double,
                           axis: AxisBase?) -> String
  {
    return String(format: "%.0f%%", value);
  }
}
