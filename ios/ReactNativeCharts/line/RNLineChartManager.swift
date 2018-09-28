//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNLineChartManager)
@objcMembers
open class RNLineChartManager: RCTViewManager, RNBarLineChartBaseManager {
  var _bridge: RCTBridge? {get{return self.bridge}}
  
  override open func view() -> UIView! {
    let ins = RNLineChartView()
    return ins;
  }

  override open static func requiresMainQueueSetup() -> Bool {
    return true;
  }
  
  func moveViewToX(_ reactTag: NSNumber, xValue: NSNumber) {
    (self as RNBarLineChartBaseManager)._moveViewToX(reactTag, xValue: xValue)
  }
  
  func moveViewTo(_ reactTag: NSNumber, xValue: NSNumber, yValue: NSNumber, axisDependency: NSString) {
    (self as RNBarLineChartBaseManager)._moveViewTo(reactTag, xValue: xValue, yValue: yValue, axisDependency: axisDependency)
  }
  
  func moveViewToAnimated(_ reactTag: NSNumber, xValue: NSNumber, yValue: NSNumber, axisDependency: NSString, duration: NSNumber) {
    (self as RNBarLineChartBaseManager)._moveViewToAnimated(reactTag, xValue: xValue, yValue: yValue, axisDependency: axisDependency, duration: duration)
  }
  
  func centerViewTo(_ reactTag: NSNumber, xValue: NSNumber, yValue: NSNumber, axisDependency: NSString) {
    (self as RNBarLineChartBaseManager)._centerViewTo(reactTag, xValue: xValue, yValue: yValue, axisDependency: axisDependency)
  }
  
  func centerViewToAnimated(_ reactTag: NSNumber, xValue: NSNumber, yValue: NSNumber, axisDependency: NSString, duration: NSNumber) {
    (self as RNBarLineChartBaseManager)._centerViewToAnimated(reactTag, xValue: xValue, yValue: yValue, axisDependency: axisDependency, duration: duration)
  }
  
  func fitScreen(_ reactTag: NSNumber) {
    (self as RNBarLineChartBaseManager)._fitScreen(reactTag)
  }
  
  func highlights(_ reactTag: NSNumber, config: NSArray) {
    (self as RNBarLineChartBaseManager)._highlights(reactTag, config: config)
  }

}
