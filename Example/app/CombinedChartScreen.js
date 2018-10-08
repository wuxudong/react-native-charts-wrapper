/**
 * Created by xudong on 02/03/2017.
 */

import React, {Component} from 'react';
import {View, Text, StyleSheet, processColor} from 'react-native';

import {CombinedChart} from 'react-native-charts-wrapper';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'stretch',
    backgroundColor: 'transparent'
  }
});

export default class Combined extends Component {

  constructor() {
    super();
    this.state = {
      xAxis: {
        valueFormatter: ['1990', '1991', '1992', '1993', '1994'],
        granularityEnabled: true,
        granularity: 1
      },

      yAxis: {
        left: {      
          granularityEnabled: true,
          granularity: 10
        },
        right: {
          granularityEnabled: true,
          granularity: 100
        }
      },

      marker: {
        enabled: true,
        markerColor: processColor('#F0C0FF8C'),
        textColor: processColor('white'),
        markerFontSize: 14,
      },

      data: {
        barData: {
          dataSets: [{
            values: [{y:[40, 30, 20], marker: ["row1", "row2", "row3"]}, {y:[10, 20, 10], marker:"second"}, {y:[30, 20, 50], marker:["hello", "world","third"]}, {y:[30, 50, 10], marker:"fourth"}],
            label: 'Stacked Bar dataset',
            config: {
              colors: [processColor('#C0FF8C'), processColor('#FFF78C'), processColor('#FFD08C')],
              stackLabels: ['Engineering', 'Sales', 'Marketing']
            }
          }],
        },
        lineData: {
          dataSets: [{
            values: [50, 100, 50, 100, 50],
            label: 'Sine function',

            config: {
              drawValues: false,
              colors: [processColor('green')],
              mode: "CUBIC_BEZIER",
              drawCircles: false,
              lineWidth: 2,
              axisDependency: "RIGHT",
            }
          }, {
            values: [100, 50, 100, 50, 100],
            label: 'Cosine function',

            config: {
              drawValues: false,
              colors: [processColor('blue')],
              mode: "CUBIC_BEZIER",
              drawCircles: false,
              lineWidth: 2,
            }
          }],
        },
        bubbleData: {
          dataSets: [{
            values: [{
              size: 2.3,
              y: 180,
              marker: "marker 1"
            }, {
              size: 1.4,
              y: 150,
              marker: "marker 2"
            }, {
              size: 2.0,
              y: 106,
              marker: "marker 3"
            }, {
              size: 5.0,
              y: 100
            }, {
              size: 4.1,
              y: 65
            }],
            label: 'Company A',
            config: {
              drawValues: false,
              colors: [processColor('pink')],
              axisDependency: "RIGHT",
            }
          }],
        },
        candleData: {
          dataSets: [{
            values: [{
              shadowH: 20,
              shadowL: 5,
              open: 15,
              close: 10,
              marker: "marker 1"
            }, {
              shadowH: 30,
              shadowL: 10,
              open: 25,
              close: 15,
              marker: "marker 1"
            }, {
              shadowH: 10,
              shadowL: 5,
              open: 15,
              close: 10,
              marker: "marker 1"
            }, {
              shadowH: 50,
              shadowL: 5,
              open: 15,
              close: 25
            }],
            label: 'Company A',

            config: {
              drawValues: false,
              axisDependency: "RIGHT",
              highlightColor: processColor('darkgray'),

              shadowColor: processColor('black'),
              shadowWidth: 1,
              shadowColorSameAsCandle: true,
              increasingColor: processColor('yellow'),
              increasingPaintStyle: 'FILL',
              decreasingColor: processColor('green')
            }
          }],
        },
        scatterData: {
          dataSets: [{
            values: [15, 40, 77, 81, 43],
            label: 'Company A',

            config: {
              colors: [processColor('purple')],
              drawValues: false,
              scatterShape: 'SQUARE',
            }

          }, {
            values: [40, 5, 50, 23, 79],
            label: 'Company B',

            config: {
              drawValues: false,
              colors: [processColor('grey')],
              scatterShape: 'CIRCLE',
            }
          }, {
            values: [10, 55, 35, 90, 82],
            label: 'Company C',

            config: {
              drawValues: false,
              axisDependency: "RIGHT",
              colors: [processColor('brown')],
              scatterShape: 'TRIANGLE',
            }
          }],
        },
      }
    };

  }

  componentDidMount() {
    // in this example, there are line, bar, candle, scatter, bubble in this combined chart.
    // according to MpAndroidChart, the default data sequence is line, bar, scatter, candle, bubble.
    // so 4 should be used as dataIndex to highlight bubble data.

    // if there is only bar, bubble in this combined chart.
    // 1 should be used as dataIndex to highlight bubble data.

    this.setState({...this.state, highlights: [{x: 1, y:150, dataIndex: 4}, {x: 2, y:106, dataIndex: 4}]})
  }


  static displayName = 'Combined';

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

        <View style={{height: 80}}>
          <Text> selected entry</Text>
          <Text> {this.state.selectedEntry}</Text>
        </View>


        <View style={styles.container}>
          <CombinedChart
            data={this.state.data}
            xAxis={this.state.xAxis}
            onSelect={this.handleSelect.bind(this)}
            onChange={(event) => console.log(event.nativeEvent)}
            marker={this.state.marker}
            highlights={this.state.highlights}
            highlightFullBarEnabled={false}
            style={styles.container}/>

        </View>
      </View>
    );
  }
}
