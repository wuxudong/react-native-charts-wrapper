import React, {Component} from 'react';
import {StyleSheet, processColor} from 'react-native';

import {LineChart} from 'react-native-charts-wrapper';

const colors = [processColor('red'), processColor('blue'), processColor('green'), processColor('yellow'), processColor('purple'), processColor('pink')];

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'stretch',
    backgroundColor: 'transparent'
  }
});

export default class LiveUpdating extends Component {
  static displayName = 'LiveUpdating';

  constructor(props) {
    super(props);
    this.state = {
      values: [0],
      colorIndex: 0,
      marker: {
        enabled: true,
        digits: 2,
        backgroundTint: processColor('teal'),
        markerColor: processColor('#F0C0FF8C'),
        textColor: processColor('white'),
      }
    }
  }

  next(values, colorIndex) {
    return {
      data: {
        dataSets: [{
          values: values,
          label: 'Sine function',

          config: {
            drawValues: false,
            color: colors[colorIndex],
            mode: "CUBIC_BEZIER",
            drawCircles: false,
            lineWidth: 2
          }
        }]
      },
      xAxis: {
        axisLineWidth: 0,
        drawLabels: false,
        position: 'BOTTOM',
        drawGridLines: false
      }
    }

  }

  componentDidMount() {
    this.interval = setInterval(() => {
      if (this.state.values.length >= 10) {
        // https://github.com/PhilJay/MPAndroidChart/issues/2450
        // MpAndroidChart 3.0.2 will crash when data entry list is empty.

        this.refs.chart.highlights([])
        this.setState({values: [0], colorIndex: 0});
      } else {
        this.setState({
          values: this.state.values.concat([Math.floor((Math.random() * 100) + 1)]),
          colorIndex: (this.state.colorIndex + 1) % colors.length
        });
      }
    }, 500);
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }

  render() {
    const {values, colorIndex} = this.state;
    const config = this.next(values, colorIndex);
    return (
      <LineChart data={config.data} xAxis={config.xAxis} style={styles.container} marker={this.state.marker} ref="chart"/>
    );
  }
}