# A step by step installation tutorial

## Environment:

Outdated react-native version may cause some anonying problems, so keep them up to date.

This is my environment.

```
$ yarn -v
1.16.0

$ react-native -v
react-native-cli: 2.0.1

$ pod --version
1.7.4

Xcode 11.0

swift 5

```



## Steps


-  react-native init demo
 
-  cd demo
 
-  yarn add react-native-charts-wrapper
-  yarn add @babel/runtime
 
-  update your App.js 



 
```
import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View, processColor
} from 'react-native';

import {LineChart} from 'react-native-charts-wrapper';

export default class App extends React.Component {

  render() {
    return (
      <View style={{flex: 1}}>
        <View style={styles.container}>
          <LineChart style={styles.chart}
            data={{dataSets:[{label: "demo", values: [{y: 1}, {y: 2}, {y: 1}]}]}}
          />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F5FCFF'
  },
  chart: {
    flex: 1
  }
});

```

-  Additional setup is required because library is using native code.


## android

#### 1. update android/build.gradle, upgrade gradle tools version, add jitpack.io

add `maven { url "https://jitpack.io" }` as **the last entry** under allprojects -> repositories if it is absent, IT IS USED BY MPAndroidChart

make sure compileSdkVersion >= 28

#### 2. make sure gradle version >= 5.1.1 in android/gradle/wrapper/gradle-wrapper.properties 

#### 3. link subproject

*   **Mostly automatic install with react-native link**

		react-native link react-native-charts-wrapper
				
*   **Manual install**


    **add react-native-charts-wrapper to android/settings.gradle**

    ```
	include ':react-native-charts-wrapper'
	project(':react-native-charts-wrapper').projectDir = new File(
	  rootProject.projectDir,
	  '../node_modules/react-native-charts-wrapper/android'
	)
    ```

    **add react-native-charts-wrapper to android/app/build.gradle**

	```
	dependencies {
	    ...
	    implementation project(':react-native-charts-wrapper')
	}
	```

	**MainApplication.java**

	On top where imports are:
	
	```java
	import com.github.wuxudong.rncharts.MPAndroidChartPackage;
	```

	Add package in `getPackages` method:
	
	```java
	protected List<ReactPackage> getPackages() {
	    return Arrays.<ReactPackage>asList(
	        new MainReactPackage(),
	        new MPAndroidChartPackage()             // <----- Add this
	    );
	}
	```

#### 4. Run

react-native run-android, that is it.


## iOS

* link subproject

	*   **use cocoapods(suggested)**
		
		* add `pod 'RNCharts', :path => '../node_modules/react-native-charts-wrapper'` to ios/Podfile

		* cd ios && pod install
		* open demo.xcworkspace
		* create a empty swift file, the xcode will prompt a message 'Would you like to configure an Objective-C bridging header?' to Create Bridging Header, accept it.

		![](https://raw.githubusercontent.com/wuxudong/react-native-charts-wrapper/master/installation_guide/create-oc-bridging-header.png)
		
		* run it from XCode or run `react-native run-ios`, that is it.

		![](https://raw.githubusercontent.com/wuxudong/react-native-charts-wrapper/master/installation_guide/iOS.png)


	* **manual setup**

		You can also link this lib manually.
	
		* Add Source Files
		
		create a group under your project top level and add files under directory node_modules/react-native-charts-wrapper/ios/ReactNativeCharts
		
		Groups are required, not folder reference!
		The folder color should be yellow! not blue!
		
		![](https://raw.githubusercontent.com/wuxudong/react-native-charts-wrapper/master/installation_guide/add_source_files.png)
		
		
		
		* Add Bridge File
		
		When you add the files XCode should prompt you to create a bridging header if you haven't done so already, or you can create empty swift file to trigger xcode prompt. Create the bridging header and import the RCTViewManager.h. It should look something like this.
		
		```
		#import "React/RCTBridge.h"
		#import "React/RCTViewManager.h"
		#import "React/RCTUIManager.h"
		#import "React/UIView+React.h"
		#import "React/RCTBridgeModule.h"
		#import "React/RCTEventDispatcher.h"
		#import "React/RCTEventEmitter.h"
		#import "React/RCTFont.h"
		```
				
		* Add Charts and SwiftyJSON, you can do it by cocoapods or link them manually.
			* use cocoapods
			
			  add a Podfile to your ios directory with the following content. Then run `pod install` and open the generated .xcworkspace from now on in xcode.


			```
			target 'demo' do
			  pod 'SwiftyJSON', '5.0'      
			  pod 'Charts', '3.3.0'         
			end

			```
			
			* manual install


			  1. Install [SwiftyJSON](https://github.com/SwiftyJSON/SwiftyJSON) and [iOS Charts](https://github.com/danielgindi/ios-charts) libraries and add `SwiftyJSON.xcodeproj` and `Charts.xcodeproj` files to your project.
			  2. Under `Build Phases`, under `Link Binary With Libraries`, click the plus sign and add `SwiftyJSON.framework` and `Charts.framework`.
			  3. Add the `SwiftyJSON.framework` and `Charts.framework` to the `Embedded Binaries` section in your app.
			  4. In your project's build settings, go to build options and change the `Embedded Content Contains Swift Code` to `Yes`.

		* update project setting
		
     	  update `Swift Language Version` in `Build Settings` to 5.0
		
		* run it from XCode or run `react-native run-ios`, that is it.

		
