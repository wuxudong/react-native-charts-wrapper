import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
processColor
} from 'react-native';
import reactAddonsUpdate from 'react-addons-update';

import _ from 'lodash';
import {ScatterChart} from 'react-native-charts-wrapper';

class ScatterChartScreen extends React.Component {

  constructor() {
    super();

    this.state = {
      legend: {
        enabled: true,
        textSize: 14,
        form: 'CIRCLE',
        wordWrapEnabled: true
      },
      marker: {
        enabled: true,
        type: 'com.github.reactNativeMPAndroidChart.example.marker.OvalBlueMarker'
      }
    };
  }

  componentDidMount() {
    const size = 30;
    const range = 20;

    this.setState(
      reactAddonsUpdate(this.state, {
        data: {
          $set: {
            dataSets: [{
              values: this._randomYValues(range, size),
              label: 'DS 1',
              config: {
                color: processColor('gray'),
                scatterShape: 'X'
              }
            }, {
              values: this._randomYValues(range, size),
              label: 'DS 2',
              config: {
                color: processColor('blue'),
                scatterShape: 'CIRCLE',
                scatterShapeHoleRadius: 6,
                scatterShapeHoleColor: processColor('teal')
              }
            }, {
              values: this._randomYValues(range, size),
              label: 'DS 3',
              config: {
                color: processColor('green'),
                drawHighlightIndicators: false,
                scatterShape: 'SQUARE',
                scatterShapeSize: 8
              }
            }],
          }
        }
      })
    );
  }

  _randomYValues(range: number, size: number) {
    return _.times(size, () => {
      return {y: Math.random() * range}
    });
  }

  render() {
    return (
      <View style={styles.container}>
        <ScatterChart
          style={styles.chart}
          data={this.state.data}
          legend={this.state.legend}
          marker={this.state.marker}
        />
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

export default ScatterChartScreen;
