//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNRadarChartManager)
public class RNRadarChartManager: RCTViewManager {
  override public func view() -> UIView! {
    let ins = RNRadarChartView()
    return ins;
  }
  
}
