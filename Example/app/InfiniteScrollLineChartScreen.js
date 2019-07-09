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

const distanceToLoadMore = 10
const pageSize = 100

class InfiniteScrollLineChartScreen extends React.Component {

  constructor() {
    super();

    this.isLoading = false
    this.xMin = 0
    this.xMax = 0


    this.state = {
      data: {},
    };
  }

  componentDidMount() {
    let _this = this
    this.mockLoadDataFromServer(-pageSize, pageSize).then(function (data) {
      _this.setState(
        {
          data: data,
          visibleRange: {x: {min: 25, max: 50}}
        })
    })
  }

  mockLoadDataFromServer(from, to) {
    let _this = this
    return new Promise(function (resolve) {
      setTimeout(function () {
        _this.xMin = from
        _this.xMax = to

        console.log("load data from " + from + " to " + to)
        resolve({
          dataSets: [{
            values: Array.from(new Array(parseInt(to - from)), (val, index) => ({
              x: from + index,
              y: Math.sin(0.1 * (from + index))
            })), label: 'sin', config: {color: processColor('blue'), drawCircles: false}
          }, {
            values: Array.from(new Array(parseInt(to - from)), (val, index) => ({
              x: from + index,
              y: Math.cos(0.1 * (from + index))
            })), label: 'cos', config: {color: processColor('green'), drawCircles: false}
          }],
        })
      }, 50);
    })
  }

  handleChange(event) {
    let nativeEvent = event.nativeEvent

    let _this = this


    if (nativeEvent.action == 'chartTranslated') {
      let {left, right, centerX} = nativeEvent

      console.log("data is from " + _this.xMin + " to " + _this.xMax + " left " + left + " right " + right + " isLoading " + _this.isLoading)
      if (!_this.isLoading) {

        if (_this.xMin > left - distanceToLoadMore || right + distanceToLoadMore > _this.xMax) {

          _this.isLoading = true

          // Because of the implementation of MpAndroidChart, if the action of setDataAndLockIndex is triggered by user dragging,
          // then the size of new data should be equal to original data, otherwise the calculation of position transition won't be accurate,
          // use may find the chart suddenly blink to another position.          
          // This restriction only exists in android, in iOS, we have no such problem.
          
          _this.mockLoadDataFromServer(centerX - pageSize, centerX + pageSize).then(function (data) {

            _this.refs.chart.setDataAndLockIndex(data)

            _this.isLoading = false

          })
        }
      }
    }
  }

  render() {
    return (
      <View style={{flex: 1}}>

        <View style={styles.container}>
          <LineChart
            style={styles.chart}
            data={this.state.data}
            xAxis={this.state.xAxis}
            yAxis={this.state.yAxis}
            touchEnabled={true}
            dragEnabled={true}
            scaleEnabled={true}
            scaleXEnabled={true}
            scaleYEnabled={true}
            visibleRange={this.state.visibleRange}
            dragDecelerationEnabled={false}
            ref="chart"
            onChange={this.handleChange.bind(this)}
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

export default InfiniteScrollLineChartScreen;
