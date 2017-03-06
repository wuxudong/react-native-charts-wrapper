//
// Created by xudong wu on 26/02/2017.
// Copyright (c) wuxudong. All rights reserved.
//

import Foundation
import Charts
import SwiftyJSON

class RNYAxisChartViewBase: RNChartViewBase {
    func setYAxis(_ config: NSDictionary) {
        fatalError("subclass should override this function.")
    }

    func setYAxisConfig(_ axis: YAxis, config: JSON) {
        if config["inverted"].bool != nil {
            axis.inverted = config["inverted"].boolValue
        }

        if config["spaceTop"].number != nil {
            axis.spaceTop = CGFloat(config["spaceTop"].numberValue)
        }

        if config["spaceBottom"].number != nil {
            axis.spaceBottom = CGFloat(config["spaceBottom"].numberValue)
        }


        if config["position"].string != nil {
            axis.labelPosition = BridgeUtils.parseYAxisLabelPosition(config["position"].stringValue)
        }

        // TODO docs says the remaining config needs to be applied before setting data. Test it
        // zero line
        if config["zeroLine"].exists() {
            let zeroLineConfig = config["zeroLine"]

            if zeroLineConfig["enabled"].bool != nil {
                axis.drawZeroLineEnabled = zeroLineConfig["enabled"].boolValue
            }


            if zeroLineConfig["lineWidth"].number != nil {
                axis.zeroLineWidth = CGFloat(zeroLineConfig["lineWidth"].numberValue);
            }

            if zeroLineConfig["lineColor"].int != nil {
                axis.zeroLineColor = RCTConvert.uiColor(zeroLineConfig["lineColor"].intValue);
            }
        }

    }
}
