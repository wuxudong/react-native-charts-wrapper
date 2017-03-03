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
      reactAddonsUpdate(this.state, {
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

  render() {
    return (
      <View style={styles.container}>
        <BubbleChart
          style={styles.chart}
          data={this.state.data}
          legend={this.state.legend}
          animation={this.state.animation}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: processColor('#F5FCFF')
  },
  chart: {
    flex: 1
  }
});

export default BubbleChartScreen;
