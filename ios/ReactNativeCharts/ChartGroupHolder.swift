//
//  ChartGroupHolder.swift
//  Charts
//
//  Created by wuxudong on 2018/10/1.
//

import Foundation


class ChartHolder {
    open weak var chart:BarLineChartViewBase?
    open var syncX: Bool;
    open  var syncY:Bool;
    
    init( chart: BarLineChartViewBase,  syncX: Bool,  syncY: Bool) {
        self.chart = chart
        self.syncX = syncX
        self.syncY = syncY
    }
}

open class ChartGroupHolder {
    static var chartGroup: Dictionary<String, Dictionary<String, ChartHolder>> = [:]
    
    public static func addChart(group: String, identifier: String ,  chart: BarLineChartViewBase,  syncX: Bool, syncY: Bool) {
        objc_sync_enter(chartGroup)
        defer { objc_sync_exit(chartGroup) }
        
        let keyExists = chartGroup[group] != nil
        
        if !keyExists {
            chartGroup[group] = [:]
        }
        
        chartGroup[group]![identifier] = ChartHolder(chart: chart, syncX: syncX, syncY: syncY)
    }
    
    
    // sync gesture to other chart in the same group
    public static func sync( group: String,  identifier: String,  scaleX: CGFloat,  scaleY:CGFloat,  centerX: CGFloat,  centerY:CGFloat,  performImmediately: Bool) {
        
        objc_sync_enter(chartGroup)
        defer { objc_sync_exit(chartGroup) }
        
        if let identifierMap = chartGroup[group] {
            for (identifierKey, chartHolder) in identifierMap {
                if identifierKey != identifier {
                    if let chart = chartHolder.chart {
                        
                        let axis = chart.getAxis(YAxis.AxisDependency.left).enabled ? YAxis.AxisDependency.left : YAxis.AxisDependency.right
                        
                        let contentRect = chart.contentRect
                        
                        let originalCenterValue = chart.valueForTouchPoint(point: CGPoint(x: contentRect.midX, y: contentRect.midY), axis: axis)

                        let finalScaleX = chartHolder.syncX ? scaleX : chart.scaleX
                        let finalScaleY = chartHolder.syncY ? scaleY : chart.scaleY
                        
                        let finalCenterX = chartHolder.syncX ? centerX : originalCenterValue.x;
                        let finalCenterY = chartHolder.syncY ? centerY : originalCenterValue.y;
                        
                        chart.zoom(scaleX: finalScaleX, scaleY: finalScaleY, xValue: Double(finalCenterX), yValue: Double(finalCenterY), axis: axis);                        
                    }
                }
            }
        }
    }
}
