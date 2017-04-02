//
// Created by xudong wu on 23/02/2017.
// Copyright (c) wuxudong. All rights reserved.
//

import Foundation
import SwiftyJSON
import Charts

class BridgeUtils {
    static func toIOSAlpha(_ alpha: NSNumber) -> CGFloat {
        return CGFloat(Double(alpha) / 255.0);
    }
    
    static func parseColors(_ array: [JSON]) -> [NSUIColor] {
        return array.map {
            return RCTConvert.uiColor($0.intValue);
        }
    }
    
    static func toJson(_ dict: NSDictionary) -> JSON {
        let json = try! JSONSerialization.data(withJSONObject: dict);
        
        return JSON.parse(NSString(data: json, encoding: String.Encoding.utf8.rawValue)! as String);
    }
    
    static func parseLineChartMode(_ mode: String) -> LineChartDataSet.Mode {
        let iosEnumString = androidEnumToIOSEnum(mode)
        
        switch iosEnumString {
        case "linear":
            return .linear
        case "stepped":
            return .stepped
        case "cubicBezier":
            return .cubicBezier
        case "horizontalBezier":
            return .horizontalBezier
        default:
            return .linear
        }
    }
    
    static func parseLegendPosition(_ position: String) -> Legend.Position {
        let iosEnumString = androidEnumToIOSEnum(position)
        
        switch iosEnumString {
        case "rightOfChart":
            return .rightOfChart
        case "rightOfChartCenter":
            return .rightOfChartCenter
        case "rightOfChartInside":
            return .rightOfChartInside
        case "leftOfChart":
            return .leftOfChart
        case "leftOfChartCenter":
            return .leftOfChartCenter
        case "leftOfChartInside":
            return .leftOfChartInside
        case "belowChartLeft":
            return .belowChartLeft
        case "belowChartRight":
            return .belowChartRight
        case "belowChartCenter":
            return .belowChartCenter
        case "aboveChartLeft":
            return .aboveChartLeft
        case "aboveChartRight":
            return .aboveChartRight
        case "aboveChartCenter":
            return .aboveChartCenter
        case "piechartCenter":
            return .piechartCenter
        default:
            return .belowChartLeft
        }
    }
    
    static func parseYAxisLabelPosition(_ position: String) -> YAxis.LabelPosition {
        let iosEnumString = androidEnumToIOSEnum(position)
        
        switch iosEnumString {
        case "outsideChart":
            return .outsideChart
        case "insideChart":
            return .insideChart
        default:
            return .outsideChart
        }
    }
    
    static func parseXAxisLabelPosition(_ position: String) -> XAxis.LabelPosition {
        let iosEnumString = androidEnumToIOSEnum(position)
        
        switch iosEnumString {
        case "top":
            return .top
        case "bottom":
            return .bottom
        case "bothSided":
            return .bothSided
        case "topInside":
            return .topInside
        case "bottomInside":
            return .bottomInside
        default:
            return .top
        }
    }
    
    static func parseLegendForm(_ form: String) -> Legend.Form {
        
        let iosEnumString = androidEnumToIOSEnum(form)
        
        switch iosEnumString {
        case "none":
            return .none
        case "empty":
            return .empty
        case "`default`":
            return .`default`
        case "square":
            return .square
        case "circle":
            return .circle
        case "line":
            return .line
        default:
            return .square
        }
    }
    
    static func parseScatterShape(_ shape: String) -> ScatterChartDataSet.Shape {
        let iosEnumString = androidEnumToIOSEnum(shape)
        
        switch iosEnumString {
        case "square":
            return .square
        case "circle":
            return .circle
        case "triangle":
            return .triangle
        case "cross":
            return .cross
        case "x":
            return .x
        case "chevronUp":
            return .chevronUp
        case "chevronDown":
            return .chevronDown
        default:
            return  .square
        }
    }
    
    static func parseEasingOption(_ option: String) -> ChartEasingOption {
        let iosEnumString = androidEnumToIOSEnum(option)
        
        switch iosEnumString {
        case "linear":
            return .linear
        case "easeInQuad":
            return .easeInQuad
        case "easeOutQuad":
            return .easeOutQuad
        case "easeInOutQuad":
            return .easeInOutQuad
        case "easeInCubic":
            return .easeInCubic
        case "easeOutCubic":
            return .easeOutCubic
        case "easeInOutCubic":
            return .easeInOutCubic
        case "easeInQuart":
            return .easeInQuart
        case "easeOutQuart":
            return .easeOutQuart
        case "easeInOutQuart":
            return .easeInOutQuart
        case "easeInQuint":
            return .easeInQuint
        case "easeOutQuint":
            return .easeOutQuint
        case "easeInOutQuint":
            return .easeInOutQuint
        case "easeInSine":
            return .easeInSine
        case "easeOutSine":
            return .easeOutSine
        case "easeInOutSine":
            return .easeInOutSine
        case "easeInExpo":
            return .easeInExpo
        case "easeOutExpo":
            return .easeOutExpo
        case "easeInOutExpo":
            return .easeInOutExpo
        case "easeInCirc":
            return .easeInCirc
        case "easeOutCirc":
            return .easeOutCirc
        case "easeInOutCirc":
            return .easeInOutCirc
        case "easeInElastic":
            return .easeInElastic
        case "easeOutElastic":
            return .easeOutElastic
        case "easeInOutElastic":
            return .easeInOutElastic
        case "easeInBack":
            return .easeInBack
        case "easeOutBack":
            return .easeOutBack
        case "easeInOutBack":
            return .easeInOutBack
        case "easeInBounce":
            return .easeInBounce
        case "easeOutBounce":
            return .easeOutBounce
        case "easeInOutBounce":
            return .easeInOutBounce
        default:
            return .linear
        }
    }

    static func parseAxisDependency(_ option: String) -> YAxis.AxisDependency {
        let iosEnumString = androidEnumToIOSEnum(option)

        switch iosEnumString {
        case "left":
            return .left

        case "right":
            return .right

        default:
            return .left
        }

    }
        
    static func androidEnumToIOSEnum(_ desc: String) -> String {
        let components = desc.components(separatedBy: "_")
        
        if components.count > 1 {
            return components.enumerated().reduce("") {
                accumulate, current in
                return current.offset == 0 ? current.element.lowercased() :
                    accumulate + current.element.lowercased().capitalized;
            }
        } else {
            return desc.lowercased();
        }
    }
    
    // unfortunately, this function can only work with pure swift enum without @objc
    // check http://stackoverflow.com/questions/42513337/why-does-an-objc-enum-have-a-different-description-than-a-pure-swift-enum
    static func parseIntEnumFromString<T:RawRepresentable>(_ enumType: T.Type, desc: String) -> T? where T.RawValue == Int {
        let iosEnumString = androidEnumToIOSEnum(desc);
        
        var i = 0
        while let item = enumType.init(rawValue: i) {
            if String(describing: item) == iosEnumString {
                return item
            }
            i += 1
        }
        return nil;
    }
}
