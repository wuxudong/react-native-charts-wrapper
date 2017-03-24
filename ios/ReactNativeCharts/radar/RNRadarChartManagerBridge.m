//  Created by xudong wu on 24/02/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

#import "React/RCTViewManager.h"
#import "RNChartManagerBridge.h"
#import "RNYAxisChartManagerBridge.h"


@interface RCT_EXTERN_MODULE(RNRadarChartManager, RCTViewManager)

EXPORT_Y_AXIS_CHART_BASE_PROPERTIES
RCT_EXPORT_VIEW_PROPERTY(skipWebLineCount, NSInteger)
RCT_EXPORT_VIEW_PROPERTY(rotationEnabled, BOOL)

@end
