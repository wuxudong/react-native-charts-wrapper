//  Created by xudong wu on 24/02/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

#import "React/RCTViewManager.h"
#import "RNChartManagerBridge.h"
#import "RNYAxisChartManagerBridge.h"


@interface RCT_EXTERN_MODULE(RNRadarChartManager, RCTViewManager)

EXPORT_Y_AXIS_CHART_BASE_PROPERTIES
RCT_EXPORT_VIEW_PROPERTY(skipWebLineCount, NSInteger)
RCT_EXPORT_VIEW_PROPERTY(minOffset, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(drawWeb, BOOL)
RCT_EXPORT_VIEW_PROPERTY(rotationEnabled, BOOL)
RCT_EXPORT_VIEW_PROPERTY(rotationAngle, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(extraOffsets, NSDictionary) 
RCT_EXPORT_VIEW_PROPERTY(webLineWidth, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(webLineWidthInner, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(webAlpha, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(webAlphaInner, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(webColor, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(webColorInner, NSNumber)

@end
