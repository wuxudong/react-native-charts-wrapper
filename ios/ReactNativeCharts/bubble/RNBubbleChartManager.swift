//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNBubbleChartManager)
public class RNBubbleChartManager: RCTViewManager {
  override public func view() -> UIView! {
    let ins = RNBubbleChartView()
    return ins;
  }
  
}
