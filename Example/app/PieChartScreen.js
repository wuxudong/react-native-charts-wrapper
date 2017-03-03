import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
processColor
} from 'react-native';

import {PieChart} from 'react-native-charts-wrapper';

class PieChartScreen extends React.Component {

  constructor() {
    super();

    this.state = {
      legend: {
        enabled: true,
        textSize: 8,
        form: 'CIRCLE',
        position: 'RIGHT_OF_CHART',
        wordWrapEnabled: true
      },
      data: {
        dataSets: [{
          values: [{value: 40, label: 'Sandwiches'},
            {value: 21, label: 'Salads'},
            {value: 15, label: 'Soup'},
            {value: 9, label: 'Beverages'},
            {value: 15, label: 'Desserts'}],
          label: 'Pie dataset',
          config: {
            colors: [processColor('#C0FF8C'), processColor('#FFF78C'), processColor('#FFD08C'), processColor('#8CEAFF'), processColor('#FF8C9D')],

            sliceSpace: 5,
            selectionShift: 13
          }
        }],
      },
      description: {
        text: 'This is Pie chart description',
        textSize: 15,
        textColor: processColor('darkgray'),

      }
    };
  }

  render() {
    return (
      <View style={styles.container}>
        <PieChart
          style={styles.chart}
          logEnabled={true}
          chartBackgroundColor={processColor('pink')}
          description={this.state.description}
          data={this.state.data}
          legend={this.state.legend}

          drawSliceText={true}
          usePercentValues={false}
          centerText={'Pie center text!'}
          centerTextRadiusPercent={100}
          holeRadius={40}
          holeColor={processColor('#f0f0f0')}
          transparentCircleRadius={45}
          transparentCircleColor={processColor('#f0f0f088')}

          maxAngle={350}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  chart: {
    flex: 1
  }
});

export default PieChartScreen;
