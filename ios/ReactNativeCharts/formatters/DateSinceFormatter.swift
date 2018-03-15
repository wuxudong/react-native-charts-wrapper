import Foundation
import Charts

open class DateSinceFormatter: NSObject, IValueFormatter, IAxisValueFormatter {

  open var dateFormatter = DateFormatter();
  open var startingTimestamp = 0;
  
  public override init() {
    
  }
  
  public init(pattern: String?, startingTimestamp: Int) {
    self.dateFormatter.dateFormat = pattern;
    self.startingTimestamp = startingTimestamp;
  }
  
  open func stringForValue(_ value: Double, axis: AxisBase?) -> String {
    return format(value)
  }
  
  open func stringForValue(_ value: Double, entry: ChartDataEntry, dataSetIndex: Int, viewPortHandler: ViewPortHandler?) -> String {
    return format(value)
  }
  
  fileprivate func format(_ value: Double) -> String
  {
    let date = Date(timeIntervalSince1970: (Double(self.startingTimestamp) + value) / 1000.0);
    return self.dateFormatter.string(from: date);
  }

}
