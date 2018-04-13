//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

#import "React/RCTViewManager.h"
#import "RNChartManagerBridge.h"
#import "RNYAxisChartManagerBridge.h"
#import "RNBarLineChartManagerBridge.h"

@interface RCT_EXTERN_MODULE(RNBarChartManager, RCTViewManager)

EXPORT_BAR_LINE_CHART_BASE_PROPERTIES
RCT_EXPORT_VIEW_PROPERTY(drawValueAboveBar, BOOL)
RCT_EXPORT_VIEW_PROPERTY(drawBarShadow, BOOL)

@end
