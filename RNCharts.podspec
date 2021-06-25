require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = 'RNCharts'
  s.version      = package["version"]
  s.summary      = package["description"]
  s.author       = package["author"]

  s.homepage     = package["homepage"]

  s.license      = package["license"]
  s.platform     = :ios, "9.0"

  s.source       = { :git => "https://github.com/hortau/react-native-charts-wrapper.git", :tag => "#{s.version}" }
  s.source_files = "ios/ReactNativeCharts/**/*.{h,m,swift}"
  s.static_framework = true

  s.swift_version = '5.0'
  s.dependency 'React'
  s.dependency 'SwiftyJSON', '5.0'
  s.dependency 'Charts', '3.6.0'


end
