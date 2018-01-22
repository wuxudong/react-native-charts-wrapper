//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNPieChartManager)
open class RNPieChartManager: RCTViewManager {
  override open func view() -> UIView! {
    let ins = RNPieChartView()
    return ins;
  }

  open static func requiresMainQueueSetup() -> Bool {
    return true;
  }

}
