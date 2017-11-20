
## How to run Example




cd Example

npm install 


react-native link



### android


react-native run-android


### ios


cd ios

pod install 

react-native run-ios





# A step by step tutorial to setup a new project




	react-native init ForBeginner
	cd ForBeginner
	npm install --save react-native-charts-wrapper


	# lodash and react-addons-update are used in tutorial, not required by your project.
	npm install --save lodash
	npm install --save react-addons-update


	react-native link react-native-charts-wrapper

	# copy example js to your project
	cp -r  node_modules/react-native-charts-wrapper/Example/app .
	cp node_modules/react-native-charts-wrapper/Example/index.android.js .
	cp node_modules/react-native-charts-wrapper/Example/index.ios.js .




update index.android.js and index.ios.js manually   
replace    
`AppRegistry.registerComponent('Example', () => Example);`   
with   
`AppRegistry.registerComponent('ForBeginner', () => Example);`





## Android

update android/build.gradle, add `maven { url "https://jitpack.io" }  ` in repositories




react-native run-android


That is all.


## IOS







create Podfile in ios with following content

	use_frameworks!
	
	target 'ForBeginner' do
		pod 'SwiftyJSON', '3.1.4'
		pod 'Charts', '3.0.3'
	end
		
	post_install do |installer|
		installer.pods_project.targets.each do |target|
		  target.build_configurations.each do |config|
		    config.build_settings['SWIFT_VERSION'] = '3.0'
		  end
		end
	end






run `pod install`



open ForBeginner.xcworkspace/

create a group `ReactNativeCharts` under your project *top level* and add files under directory node_modules/react-native-charts-wrapper/ios/ReactNativeCharts

choose Group ForBeginner, create a empty swift file, the xcode will prompt creating a bridging file, let's name it `ForBeginner-Bridging-Header.h`


replace content with 

    #import "React/RCTBridge.h"
    #import "React/RCTViewManager.h"
    #import "React/RCTUIManager.h"
    #import "React/UIView+React.h"
    #import "React/RCTBridgeModule.h"
    #import "React/RCTEventDispatcher.h"
    #import "React/RCTEventEmitter.h"





set `ForBeginner-Bridging-Header.h` in `Build Settings -> Swift Compiler - General -> Object-C Bridging Header`   


set `No` in `Build Settings -> Swift Compiler - Version -> User Legacy Swift Language Version` 


click run  or use `react-native run-ios`

That is all.
