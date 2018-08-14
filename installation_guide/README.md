# A step by step installation tutorial

## Environment:

Outdated react-native version may cause some anonying problems, so keep them update to date.

This is my environment.

```
$ yarn -v
1.3.2

$ react-native -v
react-native-cli: 2.0.1

$ pod --version
1.5.3

```



## Steps


-  react-native init demo
 
-  cd demo
 
-  yarn add react-native-charts-wrapper
 
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




    // Top-level build file where you can add configuration options common to all sub-projects/modules.

    buildscript {
        repositories {
            jcenter()
            maven {
                url 'https://maven.google.com/'
                name 'Google'
            }
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:3.1.3'  // UPGRADE VERSION TO 3.x

            // NOTE: Do not place your application dependencies here; they belong
            // in the individual module build.gradle files
        }
    }

    allprojects {
        repositories {
            mavenLocal()
            jcenter()
            maven {
                // All of React Native (JS, Obj-C sources, Android binaries) is installed from npm
                url "$rootDir/../node_modules/react-native/android"
            }
            maven {
                url 'https://maven.google.com/'
                name 'Google'
            }
            maven { url "https://jitpack.io" }    // ADD jitpack.io, IT IS USED BY MPAndroidChart
        }
    }

    ext {
        buildToolsVersion = "27.0.3"   // UPGRADE VERSION
        minSdkVersion = 16
        compileSdkVersion = 26
        targetSdkVersion = 26
        supportLibVersion = "26.1.0"
    }


#### 2. update gradle version >= 4.4 in android/gradle/wrapper/gradle-wrapper.properties 

```
distributionUrl=https\://services.gradle.org/distributions/gradle-4.4-all.zip	
```


#### 3. link subproject

*   **Mostly automatic install with react-native**

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


#### 4.  **Additional setting**
   
Add following code to MainApplication.java for RN >= 0.54, check [#229](https://github.com/wuxudong/react-native-charts-wrapper/issues/229) and the code in android example.

```java
  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    ReadableNativeArray.setUseNativeAccessor(true);
    ReadableNativeMap.setUseNativeAccessor(true);
  }
```



#### 5. Run

react-native run-android, that is it.


## iOS

* add postinstall in package.json

```
  "scripts": {
    "start": "node node_modules/react-native/local-cli/cli.js start",
    "test": "jest",
    "postinstall": "sed -i '' 's/#import <RCTAnimation\\/RCTValueAnimatedNode.h>/#import \"RCTValueAnimatedNode.h\"/' ./node_modules/react-native/Libraries/NativeAnimation/RCTNativeAnimatedNodesManager.h"
  }

```

* run `yarn install` again
* link subproject

	*   **use cocoapods**

		* create ios/Podfile

		```
		platform :ios, '9.0'
			
		use_frameworks!
			
		target 'demo' do
		    pod 'yoga', path: '../node_modules/react-native/ReactCommon/yoga/'
		    pod 'React', path: '../node_modules/react-native/', :subspecs => [
		    'Core',
		    'ART',
		    'RCTActionSheet',
		    'RCTAnimation',
		    'RCTLinkingIOS',
		    'RCTGeolocation',
		    'RCTImage',
		    'RCTNetwork',
		    'RCTText',
		    'RCTVibration',
		    'RCTWebSocket',
		    'DevSupport',
		    'CxxBridge',
		    ]
		    
		    pod 'DoubleConversion', :podspec => '../node_modules/react-native/third-party-podspecs/DoubleConversion.podspec'
		    pod 'glog', :podspec => '../node_modules/react-native/third-party-podspecs/glog.podspec'
		    pod 'Folly', :podspec => '../node_modules/react-native/third-party-podspecs/Folly.podspec'
		    
		    pod 'RNCharts', :path => '../node_modules/react-native-charts-wrapper'
		end
			
		post_install do |installer|
		  installer.pods_project.targets.each do |target|
		    target.build_configurations.each do |config|
		      config.build_settings['SWIFT_VERSION'] = '4.1'
		    end
		  end
		end
		```

		
		* cd ios && pod install
		* open demo.xcworkspace
		* create a empty swift file, the xcode will prompt a message 'Would you like to configure an Objective-C bridging header?' to Create Bridging Header, accept it.

		![](https://raw.githubusercontent.com/wuxudong/react-native-charts-wrapper/master/installation_guide/create-oc-bridging-header.png)

		* run it from XCode or run `react-native run-ios`, that is it.
	* **manual setup**

		Sometime `use_frameworks!` in Podfile conflict with other libs like  react-native-maps.  In this case, you can link this lib manually.
	
		* Add Source Files
		
		create a group under your project top level and add files under directory node_modules/react-native-charts-wrapper/ios/ReactNativeCharts
		
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
			  pod 'SwiftyJSON', '4.0.0'      
			  pod 'Charts', '3.1.1'         
			end
			post_install do |installer|
			  installer.pods_project.targets.each do |target|
			    target.build_configurations.each do |config|
			      config.build_settings['SWIFT_VERSION'] = '4.0'
			    end
			  end
			end 
			```
			
			* manual install


			  1. Install [SwiftyJSON](https://github.com/SwiftyJSON/SwiftyJSON) and [iOS Charts](https://github.com/danielgindi/ios-charts) libraries and add `SwiftyJSON.xcodeproj` and `Charts.xcodeproj` files to your project.
			  2. Under `Build Phases`, under `Link Binary With Libraries`, click the plus sign and add `SwiftyJSON.framework` and `Charts.framework`.
			  3. Add the `SwiftyJSON.framework` and `Charts.framework` to the `Embedded Binaries` section in your app.
			  4. In your project's build settings, go to build options and change the `Embedded Content Contains Swift Code` to `Yes`.

		* update project setting
		
     	  update `Swift Language Version` in `Build Settings` to 4.1
		
		* run it from XCode or run `react-native run-ios`, that is it.

		![](https://raw.githubusercontent.com/wuxudong/react-native-charts-wrapper/master/installation_guide/iOS.png)
