//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNCombinedChartManager)
public class RNCombinedChartManager: RCTViewManager {
  override public func view() -> UIView! {
    let ins = RNCombinedChartView()
    return ins;
  }
  
}
