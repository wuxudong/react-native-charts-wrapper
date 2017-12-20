import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  Button,
  View, processColor
} from 'react-native';
import update from 'immutability-helper';

import {LineChart} from 'react-native-charts-wrapper';

class MultipleChartScreen extends React.Component {

  constructor() {
    super();

    this.state = {
      data: {},
      chart1Zoom: {scaleX: 3, scaleY: 1, xValue: 50, yValue: 0},
      chart2Zoom: {scaleX: 3, scaleY: 1, xValue: 50, yValue: 0}
    }
  }

  componentDidMount() {
    this.setState(
      update(this.state, {
        data: {
          $set: {
            dataSets: [{
              values: Array.from(new Array(100), (val, index) => index),
              label: 'Company X',
            }, {
              values: Array.from(new Array(100), (val, index) => index + 5),
              label: 'Company Y',
            }]
          }
        }
      })
    );
  }

  syncToChart2(event) {
    if (event.action == 'chartScaled' || event.action == 'chartTranslated') {
      let {scaleX, scaleY, centerX, centerY} = event
      this.setState({...this.state, chart2Zoom: {scaleX: scaleX, scaleY: scaleY, xValue: centerX, yValue: centerY}})
      console.log("sync chart2" +
        " to " + centerX + " " + centerY)
    }

    console.log(event)
  }


  render() {
    return (
      <View style={{flex: 1}}>

        <View style={{height:40}}>
          <Text>Drag or zoom first chart</Text>
        </View>

        <View style={styles.container}>

          <LineChart
            style={styles.chart}
            data={this.state.data}
            xAxis={this.state.xAxis}

            touchEnabled={true}
            dragEnabled={true}
            scaleEnabled={true}
            scaleXEnabled={true}
            scaleYEnabled={true}
            pinchZoom={true}
            doubleTapToZoomEnabled={true}
            dragDecelerationEnabled={false}
            dragDecelerationFrictionCoef={0.99}
            zoom={this.state.chart1Zoom}

            ref="chart1"

            onChange={(event) => this.syncToChart2(event.nativeEvent)}
          />
        </View>

        <View style={styles.container}>
          <LineChart
            style={styles.chart}
            data={this.state.data}
            xAxis={this.state.xAxis}

            touchEnabled={true}
            dragEnabled={true}
            scaleEnabled={true}
            scaleXEnabled={true}
            scaleYEnabled={true}
            pinchZoom={true}
            doubleTapToZoomEnabled={true}
            dragDecelerationEnabled={false}
            dragDecelerationFrictionCoef={0.99}

            ref="chart2"

            zoom={this.state.chart2Zoom}
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

export default MultipleChartScreen;
