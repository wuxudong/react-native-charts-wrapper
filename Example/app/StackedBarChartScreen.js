import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,processColor
} from 'react-native';

import {BarChart} from 'react-native-charts-wrapper';

class StackedBarChartScreen extends React.Component {

  constructor() {
    super();

    this.state = {
      legend: {
        enabled: true,
        textSize: 14,
        form: "SQUARE",
        formSize: 14,
        xEntrySpace: 10,
        yEntrySpace: 5,
        wordWrapEnabled: true
      },
      data: {
        dataSets: [{
          values: [[40, 30, 20], [10, 20, 10], [30, 20, 50], [30, 50, 10]],
          label: 'Stacked Bar dataset',
          config: {
            colors: [processColor('#C0FF8C'), processColor('#FFF78C'), processColor('#FFD08C')],
            stackLabels: ['Engineering', 'Sales', 'Marketing']
          }
        }],
      },
      xAxis: {
        valueFormatter: ['Q1', 'Q2', 'Q3', 'Q4'],
        granularityEnabled:true,
        granularity:1

      }

    };
  }

  render() {
    return (
      <View style={styles.container}>
        <BarChart
          style={styles.chart}
          xAxis={this.state.xAxis}
          data={this.state.data}
          legend={this.state.legend}
          drawValueAboveBar={false}
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


export default StackedBarChartScreen;
