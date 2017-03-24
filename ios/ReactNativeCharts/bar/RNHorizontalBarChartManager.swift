//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNHorizontalBarChartManager)
open class RNHorizontalBarChartManager: RCTViewManager {
  override open func view() -> UIView! {
    let ins = RNHorizontalBarChartView()
    return ins;
  }
  
}
