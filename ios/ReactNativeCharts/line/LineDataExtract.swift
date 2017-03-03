//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation

import SwiftyJSON
import Charts

class LineDataExtract : DataExtract {
    override func createData() -> ChartData {
        return LineChartData();
    }
    
    override func createDataSet(entries: [ChartDataEntry]?, label: String?) -> IChartDataSet {
        return LineChartDataSet(values: entries, label: label)
    }
    
    override func dataSetConfig(dataSet: IChartDataSet, config: JSON) {
        
        
        let lineDataSet = dataSet as! LineChartDataSet;
        
        ChartDataSetConfigUtils.commonConfig(dataSet: lineDataSet, config: config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(dataSet: lineDataSet, config: config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(dataSet: lineDataSet, config: config);
        ChartDataSetConfigUtils.commonLineRadarConfig(dataSet: lineDataSet, config: config);
        
        // LineDataSet only config
        if config["circleRadius"].number != nil {
            lineDataSet.circleRadius = CGFloat(config["circleRadius"].numberValue)
        }
        
        
        if config["drawCircles"].bool != nil {
            lineDataSet.drawCirclesEnabled = config["drawCircles"].boolValue
        }
        
        
        if config["mode"].string != nil {
            lineDataSet.mode = BridgeUtils.parseLineChartMode(config["mode"].stringValue)
        }
        
        
        if config["drawCubicIntensity"].number != nil {
            lineDataSet.cubicIntensity = CGFloat(config["drawCubicIntensity"].numberValue);
        }
        
        
        if config["circleColor"].int != nil {
            lineDataSet.setCircleColor(RCTConvert.uiColor(config["circleColor"].intValue))
        }
        
        if config["circleColors"].array != nil {
            lineDataSet.circleColors = BridgeUtils.parseColors(config["circleColors"].arrayValue)
        }
        
        if config["circleHoleColor"].int != nil {
            lineDataSet.circleHoleColor = RCTConvert.uiColor(config["circleHoleColor"].intValue)
        }
        
        
        if config["drawCircleHole"].bool != nil {
            lineDataSet.drawCircleHoleEnabled = config["drawCircleHole"].boolValue
        }
        
        if config["dashedLine"].exists() {
            let dashedLine = config["dashedLine"]
            var lineLength = CGFloat(0);
            var spaceLength = CGFloat(0);
            var phase = CGFloat(0);
            
            if dashedLine["lineLength"].number != nil {
                lineLength = CGFloat(dashedLine["lineLength"].numberValue)
            }
            if dashedLine["spaceLength"].number != nil {
                spaceLength = CGFloat(dashedLine["spaceLength"].numberValue)
            }
            if dashedLine["phase"].number != nil {
                phase = CGFloat(dashedLine["phase"].numberValue)
            }
            
            lineDataSet.lineDashLengths = [lineLength, spaceLength]
            lineDataSet.lineDashPhase = phase
        }            
    }
    
    override func createEntry(values: [JSON], index: Int) -> ChartDataEntry {
        var entry: ChartDataEntry;
        
        var x = Double(index);
        let value = values[index];
        
        if value.dictionary != nil {
            let dict = value;
            
            if dict["x"].double != nil {
                x = Double((dict["x"].doubleValue));
            }
            
            if dict["y"].number != nil {
                entry = ChartDataEntry(x: x, y: dict["y"].doubleValue, data: dict as AnyObject?);
            } else {
                fatalError("invalid data " + values.description);
            }
            
            
        } else if value.double != nil {
            entry = ChartDataEntry(x: x, y: value.doubleValue);
        } else {
            fatalError("invalid data " + values.description);
        }
        
        return entry;
    }
}
