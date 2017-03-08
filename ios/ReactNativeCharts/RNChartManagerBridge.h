//
//  RNChartManagerBridge.h
//  reactNativeCharts
//
//  Created by xudong wu on 25/02/2017.
//  Copyright wuxudong
//

#ifndef RNChartManagerBridge_h
#define RNChartManagerBridge_h

#define EXPORT_CHART_BASE_PROPERTIES \
RCT_EXPORT_VIEW_PROPERTY(data, NSDictionary) \
RCT_EXPORT_VIEW_PROPERTY(legend, NSDictionary) \
RCT_EXPORT_VIEW_PROPERTY(chartBackgroundColor, NSInteger) \
RCT_EXPORT_VIEW_PROPERTY(chartDescription, NSDictionary) \
RCT_EXPORT_VIEW_PROPERTY(noDataText, NSString) \
RCT_EXPORT_VIEW_PROPERTY(touchEnabled, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(dragDecelerationEnabled, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(dragDecelerationFrictionCoef, NSNumber) \
RCT_EXPORT_VIEW_PROPERTY(animation, NSDictionary) \
RCT_EXPORT_VIEW_PROPERTY(xAxis, NSDictionary) \
RCT_EXPORT_VIEW_PROPERTY(marker, NSDictionary) \
RCT_EXPORT_VIEW_PROPERTY(onSelect, RCTBubblingEventBlock)



#endif /* RNChartManagerBridge_h */
