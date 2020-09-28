//  Created by xudong wu on 26/02/2017.
//  Copyright wuxudong
//

#ifndef RNBarLineChartManagerBridge_h
#define RNBarLineChartManagerBridge_h

#define EXPORT_BAR_LINE_CHART_BASE_PROPERTIES \
EXPORT_Y_AXIS_CHART_BASE_PROPERTIES \
RCT_EXPORT_VIEW_PROPERTY(drawGridBackground, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(maxHighlightDistance, CGFloat) \
RCT_EXPORT_VIEW_PROPERTY(gridBackgroundColor, NSInteger) \
RCT_EXPORT_VIEW_PROPERTY(drawBorders, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(borderColor, NSInteger) \
RCT_EXPORT_VIEW_PROPERTY(borderWidth, CGFloat) \
RCT_EXPORT_VIEW_PROPERTY(maxVisibleValueCount, NSInteger) \
RCT_EXPORT_VIEW_PROPERTY(visibleRange, NSDictionary) \
RCT_EXPORT_VIEW_PROPERTY(autoScaleMinMaxEnabled, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(keepPositionOnRotation, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(scaleEnabled, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(dragEnabled, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(scaleXEnabled, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(scaleYEnabled, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(pinchZoom, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(highlightPerDragEnabled, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(doubleTapToZoomEnabled, BOOL) \
RCT_EXPORT_VIEW_PROPERTY(zoom, NSDictionary) \
RCT_EXPORT_VIEW_PROPERTY(viewPortOffsets, NSDictionary) \
RCT_EXPORT_VIEW_PROPERTY(extraOffsets, NSDictionary) \
RCT_EXTERN_METHOD(moveViewToX:(nonnull NSNumber *)node xValue:(nonnull NSNumber *)xValue) \
RCT_EXTERN_METHOD(moveViewTo:(nonnull NSNumber *)node xValue:(nonnull NSNumber *)xValue yValue:(nonnull NSNumber *)yValue axisDependency:(nonnull NSString *)axisDependency) \
RCT_EXTERN_METHOD(moveViewToAnimated:(nonnull NSNumber *)node xValue:(nonnull NSNumber *)xValue yValue:(nonnull NSNumber *)yValue axisDependency:(nonnull NSString *)axisDependency duration:(nonnull NSNumber *)duration) \
RCT_EXTERN_METHOD(centerViewTo:(nonnull NSNumber *)node xValue:(nonnull NSNumber *)xValue yValue:(nonnull NSNumber *)yValue axisDependency:(nonnull NSString *)axisDependency) \
RCT_EXTERN_METHOD(centerViewToAnimated:(nonnull NSNumber *)node xValue:(nonnull NSNumber *)xValue yValue:(nonnull NSNumber *)yValue axisDependency:(nonnull NSString *)axisDependency duration:(nonnull NSNumber *)duration) \
RCT_EXTERN_METHOD(highlights:(nonnull NSNumber *)node config:(nonnull NSArray *)config) \
RCT_EXTERN_METHOD(fitScreen:(nonnull NSNumber *)node) \
RCT_EXTERN_METHOD(setDataAndLockIndex:(nonnull NSNumber *)node data:(nonnull NSDictionary *)data) \
RCT_EXTERN_METHOD(addEntries:(nonnull NSNumber *)node data:(nonnull NSArray *)data)
#endif /* RNBarLineChartManagerBridge_h */
