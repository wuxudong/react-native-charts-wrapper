//
//  HighlightUtils.swift
//
//  Created by wuxudong on 2018/7/26.
//  Copyright © 2018 wuxudong. All rights reserved.
//

import Foundation
import SwiftyJSON

class HighlightUtils {
  static func getHighlights(_ config: NSArray) -> [Highlight] {
    var highlights : [Highlight] = []
    for object in config {
      if let dict = object as? NSDictionary {
        let json = BridgeUtils.toJson(dict)
        
        if json["x"].double != nil {
          let dataSetIndex = json["dataSetIndex"].int != nil ? json["dataSetIndex"].intValue : 0
          let y = json["y"].double != nil ? json["y"].doubleValue : 0
          
          var highlight : Highlight
          if json["stackIndex"].int != nil {
            highlight = Highlight(x: json["x"].doubleValue, dataSetIndex: dataSetIndex, stackIndex: json["stackIndex"].intValue)
          } else {
            highlight = Highlight(x: json["x"].doubleValue, y: y, dataSetIndex: dataSetIndex)
          }
          
          if json["dataIndex"].int != nil {
            highlight.dataIndex = json["dataIndex"].intValue
          }
          
          highlights.append(highlight)
        }
      }
    }
    
    return highlights
  }
}
