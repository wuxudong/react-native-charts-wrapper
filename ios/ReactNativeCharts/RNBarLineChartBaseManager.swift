//
//  Created by wuxudong on 14/12/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation
import Charts

protocol RNBarLineChartBaseManager {
  var _bridge : RCTBridge? {get}
}

extension RNBarLineChartBaseManager {
  func _moveViewToX(_ reactTag: NSNumber, xValue: NSNumber) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: RNBarLineChartViewBase = viewRegistry![reactTag] as! RNBarLineChartViewBase;
      (view.chart as! BarLineChartViewBase).moveViewToX(xValue.doubleValue);
    }
  }
  
  func _moveViewTo(_ reactTag: NSNumber, xValue: NSNumber, yValue: NSNumber, axisDependency: NSString) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: RNBarLineChartViewBase = viewRegistry![reactTag] as! RNBarLineChartViewBase;
      (view.chart as! BarLineChartViewBase).moveViewTo(xValue: xValue.doubleValue, yValue: yValue.doubleValue, axis: BridgeUtils.parseAxisDependency(axisDependency as String));
    }
  }
  
  func _moveViewToAnimated(_ reactTag: NSNumber, xValue: NSNumber, yValue: NSNumber, axisDependency: NSString, duration: NSNumber) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: RNBarLineChartViewBase = viewRegistry![reactTag] as! RNBarLineChartViewBase;
      (view.chart as! BarLineChartViewBase).moveViewToAnimated(xValue: xValue.doubleValue, yValue: yValue.doubleValue, axis: BridgeUtils.parseAxisDependency(axisDependency as String), duration: duration.doubleValue / 1000.0);
    }
  }
  
  func _centerViewTo(_ reactTag: NSNumber, xValue: NSNumber, yValue: NSNumber, axisDependency: NSString) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: RNBarLineChartViewBase = viewRegistry![reactTag] as! RNBarLineChartViewBase;
      (view.chart as! BarLineChartViewBase).centerViewTo(xValue: xValue.doubleValue, yValue: yValue.doubleValue, axis: BridgeUtils.parseAxisDependency(axisDependency as String));
    }
  }
  
  func _centerViewToAnimated(_ reactTag: NSNumber, xValue: NSNumber, yValue: NSNumber, axisDependency: NSString, duration: NSNumber) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: RNBarLineChartViewBase = viewRegistry![reactTag] as! RNBarLineChartViewBase;
      (view.chart as! BarLineChartViewBase).centerViewToAnimated(xValue: xValue.doubleValue, yValue: yValue.doubleValue, axis: BridgeUtils.parseAxisDependency(axisDependency as String), duration: duration.doubleValue / 1000.0);
    }
  }
  
  func _fitScreen(_ reactTag: NSNumber) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: RNBarLineChartViewBase = viewRegistry![reactTag] as! RNBarLineChartViewBase;
      (view.chart as! BarLineChartViewBase).fitScreen();
    }
  }
  
  func _highlights(_ reactTag: NSNumber, config: NSArray) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: RNBarLineChartViewBase = viewRegistry![reactTag] as! RNBarLineChartViewBase;
      (view.chart as! BarLineChartViewBase).highlightValues(HighlightUtils.getHighlights(config));
    }
  }

  func _setDataAndLockIndex(_ reactTag: NSNumber, data: NSDictionary) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: RNBarLineChartViewBase = viewRegistry![reactTag] as! RNBarLineChartViewBase;
      view.setDataAndLockIndex(data);
    }
  }

  func _addEntries(_ reactTag: NSNumber, data: NSArray) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: RNBarLineChartViewBase = viewRegistry![reactTag] as! RNBarLineChartViewBase;
      view.addEntries(data);
    }
  }

  func _replaceDataSets(_ reactTag: NSNumber, data: NSArray) {
    _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
      let view: RNBarLineChartViewBase = viewRegistry![reactTag] as! RNBarLineChartViewBase;
      view.replaceDataSets(data);
    }
  }
}



