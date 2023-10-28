//
//  IndexValueFormatter.swift
//  Example
//
//  Created by wuxudong on 2018/7/26.
//  Copyright © 2018 wuxudong. All rights reserved.
//

import Foundation

import DGCharts

open class IndexValueFormatter: NSObject, ValueFormatter {

  private var _values: [String] = [String]()
  private var _valueCount: Int = 0

  public var values: [String]
    {
    get
    {
      return _values
    }
    set
    {
      _values = newValue
      _valueCount = _values.count
    }
  }

  public override init()
  {
    super.init()
  }

  @objc public init(values: [String])
  {
    super.init()

    self.values = values
  }

  open func stringForValue(_ value: Double, entry: ChartDataEntry, dataSetIndex: Int, viewPortHandler: ViewPortHandler?) -> String {
    let index = Int(entry.x.rounded())
    guard values.indices.contains(index), index == Int(entry.x.rounded()) else { return "" }
    return _values[index]
  }

}
