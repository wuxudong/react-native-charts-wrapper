//
//  Created by wuxudong on 14/12/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation
import Charts

protocol RNBarLineChartBaseManager {
  weak var _bridge : RCTBridge? {get}
}

extension RNBarLineChartBaseManager {
  func _moveViewToX(_ reactTag: NSNumber, xValue: NSNumber) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: BarLineChartViewBase = viewRegistry![reactTag] as! BarLineChartViewBase;
      view.moveViewToX(xValue.doubleValue);
    }
  }
  
  func _moveViewTo(_ reactTag: NSNumber, xValue: NSNumber, yValue: NSNumber, axisDependency: NSString) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: BarLineChartViewBase = viewRegistry![reactTag] as! BarLineChartViewBase;
      view.moveViewTo(xValue: xValue.doubleValue, yValue: yValue.doubleValue, axis: BridgeUtils.parseAxisDependency(axisDependency as String));
    }
  }
  
  func _moveViewToAnimated(_ reactTag: NSNumber, xValue: NSNumber, yValue: NSNumber, axisDependency: NSString, duration: NSNumber) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: RNBarLineChartViewBase = viewRegistry![reactTag] as! RNBarLineChartViewBase;
      (view.chart as! BarLineChartViewBase).moveViewToAnimated(xValue: xValue.doubleValue, yValue: yValue.doubleValue, axis: BridgeUtils.parseAxisDependency(axisDependency as String), duration: duration.doubleValue / 1000.0);
    }
  }
}



