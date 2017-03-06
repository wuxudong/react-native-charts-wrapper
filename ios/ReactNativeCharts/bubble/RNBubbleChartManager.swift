//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNBubbleChartManager)
open class RNBubbleChartManager: RCTViewManager {
  override open func view() -> UIView! {
    let ins = RNBubbleChartView()
    return ins;
  }
  
}
