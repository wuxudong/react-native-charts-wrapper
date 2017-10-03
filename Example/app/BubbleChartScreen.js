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
import {BubbleChart} from 'react-native-charts-wrapper';

class BubbleChartScreen extends React.Component {

  constructor() {
    super();

    this.state = {
      legend: {
        enabled: true,
        textSize: 14,
        form: 'CIRCLE',
        wordWrapEnabled: true
      },
      animation: {
        durationX: 1500,
        durationY: 1500,
        easingX: 'EaseInCirc'
      }
    };
  }

  componentDidMount() {
    const size = 10;
    this.setState(
      update(this.state, {
        data: {
          $set: {
            dataSets: [{
              values: this._randomYValues(20, size),
              label: 'DS 1',
              config: {
                color: processColor('#C0FF8C'),
                highlightCircleWidth: 2
              }
            }, {
              values: this._randomYValues(20, size),
              label: 'DS 2',
              config: {
                color: processColor('#FFF78C')
              }
            }, {
              values: this._randomYValues(20, size),
              label: 'DS 3',
              config: {
                color: processColor('#FFD08C')
              }
            }],
          }
        }
      })
    );
  }

  _randomYValues(range: number, size: number) {
    return _.times(size, (index) => {
      return {
        y: Math.random() * range,
        size: Math.random() * range
      };
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
          <BubbleChart
            style={styles.chart}
            data={this.state.data}
            legend={this.state.legend}
            animation={this.state.animation}
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

export default BubbleChartScreen;
