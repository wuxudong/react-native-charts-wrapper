# A step by step installation tutorial

## Environment:

Outdated react-native version may cause some anonying problems, so keep them update to date.

This is my environment.

```
$ yarn -v
1.3.2

$ react-native -v
react-native-cli: 2.0.1
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

1. add postinstall in package.json

```
  "scripts": {
    "start": "node node_modules/react-native/local-cli/cli.js start",
    "test": "jest",
    "postinstall": "sed -i '' 's/#import <RCTAnimation\\/RCTValueAnimatedNode.h>/#import \"RCTValueAnimatedNode.h\"/' ./node_modules/react-native/Libraries/NativeAnimation/RCTNativeAnimatedNodesManager.h"
  }

```

2. run `yarn install` again
3. create ios/Podfile

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

4. cd ios && pod install
5. open demo.xcworkspace
6. create a empty swift file, the xcode will prompt a message 'Would you like to configure an Objective-C bridging header?' to Create Bridging Header, accept it.
7. run it from XCode or run `react-native run-ios`, that is it.
