# React Native Charts Wrapper
This library is React Native wrapper of popular Native charting library [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) and [Charts](https://github.com/danielgindi/Charts)


## Introduction

Inspired by [react-native-mp-android-chart](https://github.com/mskec/react-native-mp-android-chart) and [react-native-ios-charts](https://github.com/Jpadilla1/react-native-ios-charts)

React Native Charts Wrapper is built on MPAndroidChart(v3.0.3) & Charts(v3.1.1), support both android & iOS.

## WARNING

As Swift evolves, if you are not using latest Swift compiler, you shouldn't check out master branch. Instead, you should go to release page and pick up whatever suits you.

* Xcode 10.0 / Swift 4.2 (master branch)
* iOS >= 8.0 (Use as an **Embedded** Framework)
* tvOS >= 9.0
* macOS >= 10.11


### ANDROID 
![](https://raw.githubusercontent.com/wuxudong/react-native-charts-wrapper/master/screenshot/Android%20ScreenShot.png)

### IOS
![](https://raw.githubusercontent.com/wuxudong/react-native-charts-wrapper/master/screenshot/IOS%20ScreenShot.png)

## Supported Chart Type

* Bar(Stack,Group)
* Line
* Scatter
* Bubble
* Pie
* Radar
* Combined
* CandleStick

## Setup

A step by step tutorial to set up a new project can be found [here](https://github.com/wuxudong/react-native-charts-wrapper/blob/master/installation_guide/README.md)


## Usage
There are 8 supported charts with many configuration options.
Almost all configuration available in base MPAndroidChart library are available through this wrapper.
More details on available configuration can be found on their [wiki](https://github.com/PhilJay/MPAndroidChart/wiki).

Example of how charts are used and how to apply configuration can be found in example.



## Convention

Android and IOS have different convention:

1. color's alpha in android is 0-255, in ios is 0-1
2. percent in android is 0-100, in ios is 0-1
3. animation.duration in MpAndroidChart is milliseconds, in Charts is seconds.
4. their enum case name is always different, for example XAxisPosition, in MpAndroidChart is BOTH_SIDED, in Charts is bothSided.

**Here we use android Convention**

**Always use processColor to set color**



## Data Format

- Complete Form


		data : {
			...
			dataSets: [
				{
					values: [
						{x: 5, y: 90},
						{x: 10, y: 130},
						{x: 50, y: 2000, marker: "eat more"},
						{x: 80, y: 9000, marker: "eat less"}
					]
				},
				...
			]
		}

marker is optional,  if x is omitted, index will be used.

- Simplified Form

	    data: {
			...
	        dataSets: [
	        	{
	          		values: [5, 40, 77, 81, 43]
	          	},
	          	....
	        ]
	    }

index will used as x.


check Example->TimeSeriesLineChart for details


## Callback

**Support value selection callBack: onSelect**

you can do whatever you want, even pop your own modal, or jump to another page.

**Support gesture callBack: onChange**

check Example->MultipleChart for details.

## Direct Function Call

Support direct function call.

You can use `chart.moveViewToX(...)` or other functions directly.

check Example->MovingWindowChart for details.

supported functions:

1. highlights([...]) 

   it can be used to highlight entries programmatically, or clear already highlighted entries if you pass empty array to it: highlights([])

2. moveViewTo/moveViewToX/moveViewToAnimated/centerViewTo/centerViewToAnimated
3. fitScreen
4. setDataAndLockIndex

   It will rescale and move to the begining of your data when new data is set by default, this is not expected when you want to load more data when user scrolls.  
   setDataAndLockIndex will remain x/y/zoom when you load more data.  
   Because of the implementation of MpAndroidChart, if the action of setDataAndLockIndex is triggered by user dragging,  
   then the range of new data (xMax - xMin) should be equal to original data(this basicly means size of new data equals to old one), otherwise the calculation of position transition won't be accurate,  
   use may find the chart suddenly blink to another position.         
   This restriction only exists in android, in iOS, we have no such problem.
   
   You can check the example InfiniteScrollLineChartScreen.

## Special properties

Several extra properties are introduced:

1. group&identifier&syncX&syncY

   They are useful when you want to implement linkage charts.
   
   Charts will sync its operation to other charts in the same group. All these sync jobs are done at native side.
   
   You can check the example LinkageChartScreen.
   
   Another way of syncing charts is to use onChange, but the performace is poor. 
   You can check the example MultipleChartScreen.
   
   
   There is a stock kLine chart in example, it combines group&identifier and setDataAndLockIndex together.
      

## Custom Marker Content 

Support custom marker content. 

check Example->TimeSeriesLineChart for details.

## Notice

**Several settings are removed.**

1. fontFamily & fontStyle

  The font is a little different in android & ios, I don't know how to configure it in js in the same way.
  
**size of chart**

you can set chart to fixed width & height, or flex:1
  



## License
The MIT License

Copyright (c) 2017 wuxudong

Copyright (c) 2016 Martin Skec

Copyright (c) 2016 Jose E. Padilla

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
