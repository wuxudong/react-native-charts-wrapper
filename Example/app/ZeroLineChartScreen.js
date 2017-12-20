import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  processColor
} from 'react-native';

import {BarChart} from 'react-native-charts-wrapper';

const GREEN = processColor('#71BD6A');
const RED = processColor('#D14B5A');

class ZeroLineChartScreen extends React.Component {

  constructor() {
    super();

    this.state = {
      data: {
        dataSets: [{
          values: [{y: -224.1}, {y: 238.5}, {y: 1280.1}, {y: -442.3}, {y: -2280.1}],
          label: 'Zero line dataset',
          config: {
            colors: [RED, GREEN, GREEN, RED, RED]
          }
        }],
      },
      xAxis: {
        enabled: false
      },
      yAxis: {
        left: {
          drawLabels: false,
          drawAxisLine: false,
          drawGridLines: false,
          zeroLine: {
            enabled: true,
            lineWidth: 1.5
          }
        },
        right: {
          enabled: false
        }
      }
    };
  }

  handleSelect(event) {
    let entry = event.nativeEvent
    if (entry == null) {
      this.setState({...this.state, selectedEntry: null})
    } else {
      this.setState({...this.state, selectedEntry: JSON.stringify(entry)})
    }

    console.log(event.nativeEvent)
  }

  render() {
    return (

      <View style={{flex: 1}}>

        <View style={{height:80}}>
          <Text> selected entry</Text>
          <Text> {this.state.selectedEntry}</Text>
        </View>

        <View style={styles.container}>
          <BarChart
            style={styles.chart}
            data={this.state.data}
            xAxis={this.state.xAxis}
            yAxis={this.state.yAxis}
            chartDescription={{text: ''}}
            legend={{enabled: false}}
            onSelect={this.handleSelect.bind(this)}
            onChange={(event) => console.log(event.nativeEvent)}
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

export default ZeroLineChartScreen;
