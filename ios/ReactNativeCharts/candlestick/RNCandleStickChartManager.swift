//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNCandleStickChartManager)
public class RNCandleStickChartManager: RCTViewManager {
  override public func view() -> UIView! {
    let ins = RNCandleStickChartView()
    return ins;
  }
  
}
