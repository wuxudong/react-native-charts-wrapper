//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNBarChartManager)
public class RNBarChartManager: RCTViewManager {
  override public func view() -> UIView! {
    let ins = RNBarChartView()
    return ins;
  }
  
}
