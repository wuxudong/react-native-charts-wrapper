import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  processColor
} from 'react-native';
import update from 'immutability-helper';

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
      update(this.state, {
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

  handleSelect(event) {
    let entry = event.nativeEvent
    if (entry == null) {
      this.setState({...this.state, selectedEntry: null})
    } else {
      this.setState({...this.state, selectedEntry: JSON.stringify(entry)})
    }
  }

  render() {
    return (

      <View style={{flex: 1}}>

        <View style={{height:80}}>
          <Text> selected entry</Text>
          <Text> {this.state.selectedEntry}</Text>
        </View>

        <View style={styles.container}>
          <ScatterChart
            style={styles.chart}
            data={this.state.data}
            legend={this.state.legend}
            marker={this.state.marker}
            onSelect={this.handleSelect.bind(this)}
            onChange={(event) => console.log(event.nativeEvent)} // Android only!
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

export default ScatterChartScreen;
