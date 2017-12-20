import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  processColor
} from 'react-native';

import {HorizontalBarChart} from 'react-native-charts-wrapper';

class HorizontalBarChartScreen extends React.Component {

  constructor() {
    super();

    this.state = {
      legend: {
        enabled: true,
        textSize: 14,
        form: 'SQUARE',
        formSize: 14,
        xEntrySpace: 10,
        yEntrySpace: 5,
        formToTextSpace: 5,
        wordWrapEnabled: true,
        maxSizePercent: 0.5,
      },
      data: {
        dataSets: [{
          values: [{y: 100}, {y: 105}, {y: 102}, {y: 110}, {y: 114}, {y: 109}, {y: 105}, {y: 99}, {y: 95}],
          label: 'Bar dataSet',
          config: {
            color: processColor('teal'),
            barSpacePercent: 40,
            barShadowColor: processColor('lightgrey'),
            highlightAlpha: 90,
            highlightColor: processColor('red'),
          }
        }],
      },
      xAxis: {
        valueFormatter: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'],
        position: 'BOTTOM',
        granularityEnabled: true,
        granularity: 1,
        labelCount: 10,
      },
      yAxis: {left:{axisMinimum: 0}}
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
          <HorizontalBarChart
            style={styles.chart}
            data={this.state.data}
            xAxis={this.state.xAxis}
            yAxis={this.state.yAxis}
            animation={{durationX: 2000}}
            legend={this.state.legend}
            gridBackgroundColor={processColor('#ffffff')}
            drawBarShadow={false}
            drawValueAboveBar={true}
            drawHighlightArrow={true}
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

export default HorizontalBarChartScreen;
