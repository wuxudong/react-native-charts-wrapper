//
//  ScatterDataExtract.swift
//  reactNativeCharts
//
//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation

import SwiftyJSON
import Charts
import UIKit

class ScatterDataExtract : DataExtract {
    override func createData() -> ChartData {
        return ScatterChartData();
    }
    
    override func createDataSet(_ entries: [ChartDataEntry]?, label: String?) -> IChartDataSet {
        return ScatterChartDataSet(entries: entries, label: label)
    }
    
    override func dataSetConfig(_ dataSet: IChartDataSet, config: JSON) {
        let scatterDataSet = dataSet as! ScatterChartDataSet;
        
        ChartDataSetConfigUtils.commonConfig(scatterDataSet, config: config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(scatterDataSet, config: config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(scatterDataSet, config: config);
        
        // ScatterDataSet only config
        if config["scatterShapeSize"].float != nil {
            scatterDataSet.scatterShapeSize = CGFloat(config["scatterShapeSize"].floatValue)
        }
        if config["scatterShape"].string != nil {
            scatterDataSet.setScatterShape(BridgeUtils.parseScatterShape(config["scatterShape"].stringValue))
        }
        
        if config["scatterShapeHoleColor"].int != nil {
            scatterDataSet.scatterShapeHoleColor = RCTConvert.uiColor(config["scatterShapeHoleColor"].intValue);
        }
        
        if config["scatterShapeHoleRadius"].float != nil {
            scatterDataSet.scatterShapeHoleRadius = CGFloat(config["scatterShapeHoleRadius"].floatValue)
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
                    let width = CGFloat(icon["width"].numberValue);
                    let height = CGFloat(icon["height"].numberValue);
                    
                    if let image = uiImage {
                        let realIconImage = resizeImage(image: image, width: width, height: height);
                        entry = ChartDataEntry(x: x, y: dict["y"].doubleValue, icon: realIconImage, data: dict as AnyObject?);
                    } else {
                        entry = ChartDataEntry(x: x, y: dict["y"].doubleValue, icon: uiImage, data: dict as AnyObject?);
                    }
                    
                    
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
    
    func resizeImage(image: UIImage, width: CGFloat, height: CGFloat) -> UIImage {
        let targetSize = CGSize(width: width, height: height)
        let size = image.size
        
        let widthRatio  = targetSize.width  / size.width
        let heightRatio = targetSize.height / size.height
        
        // Figure out what our orientation is, and use that to form the rectangle
        var newSize: CGSize
        if(widthRatio > heightRatio) {
            newSize = CGSize(width: size.width * heightRatio, height: size.height * heightRatio)
        } else {
            newSize = CGSize(width: size.width * widthRatio,  height: size.height * widthRatio)
        }
        

        if #available(iOS 10.0, *) {
            let renderer = UIGraphicsImageRenderer(size: newSize)
            return renderer.image { (context) in
                image.draw(in: CGRect(origin: .zero, size: newSize))
            }
        } else {
            return image
        }
        
        
    }
}
