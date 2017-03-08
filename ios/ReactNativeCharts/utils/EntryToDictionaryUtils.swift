//
//  EntryToDictionaryUtils.swift
//  
//
//  Created by xudong wu on 08/03/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

import UIKit
import Charts
import SwiftyJSON

class EntryToDictionaryUtils: NSObject {
  static func entryToDictionary(_ entry: ChartDataEntry) -> [AnyHashable: Any]{
    var dict = [AnyHashable: Any]()
    
    if entry.data != nil {
        dict["data"] = (entry.data as! JSON).dictionaryObject!
    }
    
    if type(of:entry) == BarChartDataEntry.self {
        
        let barEntry = entry as! BarChartDataEntry;

        dict["x"] = barEntry.x
        
        if barEntry.yValues != nil {
            dict["yValues"] = barEntry.yValues
        } else {
            dict["y"] = barEntry.y
        }
    } else if type(of:entry) == BubbleChartDataEntry.self {
        
        let bubbleEntry = entry as! BubbleChartDataEntry;
        
        dict["x"] = bubbleEntry.x
        dict["y"] = bubbleEntry.y
        
        dict["size"] = bubbleEntry.size
    } else if type(of:entry) == CandleChartDataEntry.self {
        let candleEntry = entry as! CandleChartDataEntry;
        
        dict["x"] = candleEntry.x
        dict["open"] = candleEntry.open
        dict["close"] = candleEntry.close
        dict["low"] = candleEntry.low
        dict["high"] = candleEntry.high
    } else if type(of:entry) == PieChartDataEntry.self {
        let pieEntry = entry as! PieChartDataEntry;
        
        dict["value"] = pieEntry.value
        
        if pieEntry.label != nil {
            dict["label"] = pieEntry.label!
        }            
    } else if type(of:entry) == RadarChartDataEntry.self {
        let radarEntry = entry as! RadarChartDataEntry;
        
        dict["value"] = radarEntry.value
    } else {
        dict["x"] = entry.x
        dict["y"] = entry.y
    }
    
    return dict
  }
}
