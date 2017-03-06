//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNCombinedChartManager)
open class RNCombinedChartManager: RCTViewManager {
  override open func view() -> UIView! {
    let ins = RNCombinedChartView()
    return ins;
  }
  
}
