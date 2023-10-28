//
//  DateFormatter.swift
//  Aquasafe
//
//  Created by Douglas Nassif Roma Junior on 06/09/17.
//  Copyright © 2017 Facebook. All rights reserved.
//

import Foundation
import DGCharts

open class ChartDateFormatter: NSObject, ValueFormatter, AxisValueFormatter {

  open var dateFormatter = DateFormatter();

  public override init() {

  }

  public init(pattern: String?) {
    self.dateFormatter.dateFormat = pattern;
  }

  open func stringForValue(_ value: Double, axis: AxisBase?) -> String {
    return format(value)
  }

  open func stringForValue(_ value: Double, entry: ChartDataEntry, dataSetIndex: Int, viewPortHandler: ViewPortHandler?) -> String {
    return format(value)
  }

  fileprivate func format(_ value: Double) -> String
  {
    let date = Date(timeIntervalSince1970: value / 1000.0);
    return self.dateFormatter.string(from: date);
  }

}
