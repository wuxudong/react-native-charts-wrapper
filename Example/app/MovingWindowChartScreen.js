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

class MovingWindowChartScreen extends React.Component {

  constructor() {
    super();

    this.state = {
      data: {
        dataSets: [{
          values: Array.from(new Array(100), (val, index) => index),
          label: 'Company X',
        }, {
          values: Array.from(new Array(100), (val, index) => index + 5),
          label: 'Company Y',
        }]
      },
    }
  }

  componentDidMount() {
    this.refs.chart.moveViewToAnimated(100, 0, 'left', 10000)
  }

  componentWillUnmount() {
  }

  render() {
    return (
      <View style={{flex: 1}}>

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
            zoom={{scaleX: 5, scaleY: 1, xValue: 0, yValue: 0}}

            ref="chart"
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

export default MovingWindowChartScreen;
