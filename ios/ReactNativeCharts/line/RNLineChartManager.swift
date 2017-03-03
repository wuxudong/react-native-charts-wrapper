//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNLineChartManager)
public class RNLineChartManager: RCTViewManager {
  override public func view() -> UIView! {
    let ins = RNLineChartView()
    return ins;
  }
  
}
