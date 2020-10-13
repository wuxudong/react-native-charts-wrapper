//
//  FontUtils.swift
//  Example
//
//  Created by wuxudong on 2018/7/25.
//  Copyright © 2018 Facebook. All rights reserved.
//

import Foundation
import SwiftyJSON

class FontUtils {
  static func getFont(_ json: JSON) -> UIFont? {    
    var font : UIFont?;
    if json["fontFamily"].string != nil {
      font = RCTFont.update(font ?? nil, withFamily: json["fontFamily"].string)
    }
    
    if json["valueTextSize"].number != nil {
      font = RCTFont.update(font ?? nil, withSize: json["valueTextSize"].number)
    }

    if json["fontStyle"].string != nil {
      font = RCTFont.update(font ?? nil, withStyle: json["fontStyle"].string)
    }
    
    if json["fontWeight"].string != nil {
      font = RCTFont.update(font ?? nil, withWeight: json["fontWeight"].string)
    }
    
    return font
  }
}
