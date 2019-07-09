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

class LinkageChartScreen extends React.Component {

  constructor() {
    super();

    this.state = {
      priceData: {dataSets: [{
        values: Array.from(new Array(600), (val, index) => index),
        label: 'price',
      }]},
      volumeData: {dataSets: [{
        values: Array.from(new Array(600), (val, index) => index),
        label: 'volume',
      }]}
    }
  }

  // unfortunately, doubleTapToZoomEnabled is not supported in linkage chart,
  // because in iOS Charts, the double tap event is handled by Charts itself, and no callback/custom listener
  // so it is not possible to sync double tap event to other charts in the same group

  // charts will broadcast their operation to other charts in the same group
  // different chart should have different identifier
  // synX is enabled by default, and syncY is disabled by default
  render() {
    return (
      <View style={{flex: 1}}>

        <View style={{height:40}}>
          <Text>Drag or zoom chart</Text>
        </View>

        <View style={styles.container}>

          <LineChart
            style={styles.chart}
            data={this.state.priceData}
            xAxis={this.state.xAxis}
            group="stock"
            identifier="price"
            syncX={true}
            syncY={true}

            visibleRange={{x:{min:1, max:100}}}
            dragDecelerationEnabled={false}
            doubleTapToZoomEnabled={false}  // it has to be false!!

          />
        </View>

        <View style={styles.container}>
          <LineChart
            style={styles.chart}
            data={this.state.volumeData}
            xAxis={this.state.xAxis}
            group="stock"
            identifier="volume"
            syncX={true}
            syncY={true}

            visibleRange={{x:{min:1, max:100}}}
            dragDecelerationEnabled={false}
            doubleTapToZoomEnabled={false}  // it has to be false!!
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

export default LinkageChartScreen;
