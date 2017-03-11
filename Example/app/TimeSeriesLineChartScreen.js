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
import {LineChart} from 'react-native-charts-wrapper';

class TimeSeriesLineChartScreen extends React.Component {

  constructor() {
    super();

    this.state = {
      data: {},
      legend: {
        enabled: true,
        textColor: processColor('red'),
        textSize: 12,
        position: 'BELOW_CHART_RIGHT',
        form: 'SQUARE',
        formSize: 14,
        xEntrySpace: 10,
        yEntrySpace: 5,
        formToTextSpace: 5,
        wordWrapEnabled: true,
        maxSizePercent: 0.5,
        custom: {
          colors: [processColor('red'), processColor('red')],
          labels: ['REFER', 'USER',]
        }
      },
      marker: {
        enabled: true,
        markerColor: processColor('grey'),
        textColor: processColor('white'),
        markerFontSize: 14,
      },

      selectedEntry: ""
    }

  }

  componentDidMount() {
    const size = 100;

    this.setState(
      reactAddonsUpdate(this.state, {
        data: {
          $set: {
            dataSets: [{
              values: this._randomParabolaValues(size),
              label: 'refer',
              config: {
                lineWidth: 2,
                drawCircles: false,
                drawCubic: true,
                highlightColor: processColor('red'),
                color: processColor('red'),
                drawFilled: true,
                fillColor: processColor('blue'),
                fillAlpha: 60,
                highlightEnabled: false,
                dashedLine: {
                  lineLength: 20,
                  spaceLength: 20
                }
              }
            }, {
              values: [
                {x: 1, y: 11000, marker: "a very long long long long long long long long \nmarker at top left"},
                {x: 5, y: 90, marker: "eat eat eat, never\n stop eat"},
                {x: 10, y: 130},
                {x: 15, y: 11000, marker: "test top center marker"},
                {x: 50, y: 2000, marker: "eat more"},
                {x: 90, y: 9000, marker: "your are overweight, eat less"},
                {x: 100, y: 11000, marker: "test top right marker"}],

              label: 'user',
              config: {
                lineWidth: 1,
                circleRadius: 5,
                highlightEnabled: true,
                drawHighlightIndicators: true,
                color: processColor('red'),
                drawFilled: true,
                fillColor: processColor('red'),
                fillAlpha: 45,
                circleColor: processColor('red')
              }
            }],
          }
        }
      })
    );
  }

  _randomParabolaValues(size: number) {
    return _.times(size, (index) => {
      return {x: index, y: index * index}
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

    let borderColor = processColor("red");
    return (
      <View style={{flex: 1}}>

        <View style={{height:80}}>
          <Text> selected entry</Text>
          <Text> {this.state.selectedEntry}</Text>
        </View>

        <View style={styles.container}>


          <LineChart
            style={styles.chart}
            data={this.state.data}
            chartDescription={{text: ''}}
            legend={this.state.legend}
            marker={this.state.marker}

            drawGridBackground={true}

            borderColor={borderColor}
            borderWidth={1}
            drawBorders={true}

            touchEnabled={true}
            dragEnabled={true}
            scaleEnabled={true}
            scaleXEnabled={true}
            scaleYEnabled={true}
            pinchZoom={true}
            doubleTapToZoomEnabled={false}

            dragDecelerationEnabled={true}
            dragDecelerationFrictionCoef={0.99}
            yAxis={{left:{valueFormatter:"percent", axisMaximum:11000}}}

            keepPositionOnRotation={false}

            xAxis={{position:'BOTTOM'}}

            onSelect={this.handleSelect.bind(this)}

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


export default TimeSeriesLineChartScreen;
