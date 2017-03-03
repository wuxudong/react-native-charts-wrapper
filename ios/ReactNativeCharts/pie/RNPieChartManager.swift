//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit

@objc(RNPieChartManager)
public class RNPieChartManager: RCTViewManager {
  override public func view() -> UIView! {
    let ins = RNPieChartView()
    return ins;
  }
  
}
