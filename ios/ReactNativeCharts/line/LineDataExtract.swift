//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation

import SwiftyJSON
import Charts
import UIKit

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

        if config["fillFormatter"].exists() {
            let fillFormatter = config["fillFormatter"];
            var min = CGFloat(0);

            if fillFormatter["min"].number != nil {
                min = CGFloat(fillFormatter["min"].numberValue);
            }
            lineDataSet.fillFormatter = ConfigurableMinimumLinePositionFillFormatter(min);
        }
    }

    override func createEntry(_ values: [JSON], index: Int) -> ChartDataEntry {
        var entry: ChartDataEntry;

        var x = Double(index);
        let value = values[index];

        if value.dictionary != nil {
            let dict = value;
<<<<<<< HEAD
            var y = Double(index);
            
=======

>>>>>>> 8ce6702a33355161bf07aa74e5a22f4a0d0d809b
            if dict["x"].double != nil {
                x = Double((dict["x"].doubleValue));
            }

            if dict["y"].number != nil {
                y = dict["y"].doubleValue;
            } else {
                fatalError("invalid data " + values.description);
            }
<<<<<<< HEAD
            
            if dict["icon"].exists() {
                let icon = dict["icon"]
                if icon["bundle"].dictionary != nil {
                    let bundle = icon["bundle"];
                    
                    let uiImage = RCTConvert.uiImage(bundle.dictionaryObject);
                    let width = CGFloat(icon["width"].numberValue)/4;
                    let height = CGFloat(icon["height"].numberValue)/4;
                    
                    if let image = uiImage {
                        let realIconImage = resizeImage(image: image, width: width, height: height);
                        entry = ChartDataEntry(x: x, y: dict["y"].doubleValue, icon: realIconImage);
                    } else {
                        entry = ChartDataEntry(x: x, y: dict["y"].doubleValue, icon: uiImage);
                    } 
                    
                    
                } else {
                    entry = ChartDataEntry(x: x, y: dict["y"].doubleValue, data: dict as AnyObject?);
                }
            } else {
                entry = ChartDataEntry(x: x, y: dict["y"].doubleValue, data: dict as AnyObject?);
            }
            
=======


>>>>>>> 8ce6702a33355161bf07aa74e5a22f4a0d0d809b
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

      // This is the rect that we've calculated out and this is what is actually used below
      let rect = CGRect(x: 0, y: 0, width: newSize.width, height: newSize.height)

      // Actually do the resizing to the rect using the ImageContext stuff
      UIGraphicsBeginImageContextWithOptions(newSize, false, 1.0)
      image.draw(in: rect)
      let newImage = UIGraphicsGetImageFromCurrentImageContext()
      UIGraphicsEndImageContext()

      return newImage!
    }
}
