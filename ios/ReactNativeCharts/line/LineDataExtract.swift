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
    
    override func createDataSet(_ entries: [ChartDataEntry]?, label: String?) -> IChartDataSet {
        return LineChartDataSet(entries: entries, label: label)
    }
    
    override func dataSetConfig(_ dataSet: IChartDataSet, config: JSON) {
        
        
        let lineDataSet = dataSet as! LineChartDataSet;
        
        ChartDataSetConfigUtils.commonConfig(lineDataSet, config: config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(lineDataSet, config: config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(lineDataSet, config: config);
        ChartDataSetConfigUtils.commonLineRadarConfig(lineDataSet, config: config);
        
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
    
    override func createEntry(_ values: [JSON], index: Int) -> ChartDataEntry {
        var entry: ChartDataEntry;
        
        var x = Double(index);
        let value = values[index];
        
        if value.dictionary != nil {
            let dict = value;
            var y = Double(index);
            
            if dict["x"].double != nil {
                x = Double((dict["x"].doubleValue));
            }

            if dict["y"].number != nil {
                y = dict["y"].doubleValue;
            } else {
                fatalError("invalid data " + values.description);
            }
            
            if dict["icon"].exists() {
                let icon = dict["icon"]
                if icon["bundle"].dictionary != nil {
                    let bundle = icon["bundle"];
                    
                    let uiImage = RCTConvert.uiImage(bundle.dictionaryObject);
                    entry = ChartDataEntry(x: x, y: dict["y"].doubleValue, icon: uiImage);
                } else {
                    entry = ChartDataEntry(x: x, y: dict["y"].doubleValue, data: dict as AnyObject?);
                }
            } else {
                entry = ChartDataEntry(x: x, y: dict["y"].doubleValue, data: dict as AnyObject?);
            }
            
        } else if value.double != nil {
            entry = ChartDataEntry(x: x, y: value.doubleValue);
        } else {
            fatalError("invalid data " + values.description);
        }
        
        return entry;
    }
}
