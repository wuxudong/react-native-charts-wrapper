import React from 'react';
import {
  AppRegistry,
  ListView,
  TouchableHighlight,
  StyleSheet,
  View,
  Text,
  Navigator,
  processColor
} from 'react-native';

import AxisLineChartScreen from './AxisLineChartScreen';
import BarChartScreen from './BarChartScreen';
import HorizontalBarChartScreen from './HorizontalBarChartScreen';
import BubbleChartScreen from './BubbleChartScreen';
import CandleStickChartScreen from './CandleStickChartScreen';
import CombinedChartScreen from './CombinedChartScreen';
import LineChartScreen from './LineChartScreen';
import TimeSeriesLineChartScreen from './TimeSeriesLineChartScreen';
import PieChartScreen from './PieChartScreen';
import RadarChartScreen from './RadarChartScreen';
import ScatterChartScreen from './ScatterChartScreen';
import StackedBarChartScreen from './StackedBarChartScreen';
import ZeroLineChartScreen from './ZeroLineChartScreen';
import LiveUpdateChartScreen from './LiveUpdateChartScreen';
import GroupBarChartScreen from './GroupBarChartScreen';


const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'flex-start',
    backgroundColor: 'white',
    padding: 20,
    paddingTop: 30,
    paddingBottom: 30
  },
  bar: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'flex-end',
    height: 60,
    paddingLeft: 10,
    paddingRight: 10,
    paddingBottom: 10,
    backgroundColor: 'rgba(230, 230, 230, 0.3)',
    borderBottomWidth: 1,
    borderBottomColor: 'rgba(100, 100, 100, 0.2)'
  },
  navText: {
    fontSize: 16
  },
  right: {
    opacity: 0
  },
  list: {
    backgroundColor: 'white',
    padding: 0
  },
  listItem: {
    padding: 10,
    paddingLeft: 20,
    borderBottomWidth: 1,
    borderBottomColor: 'rgba(100, 100, 100, 0.2)'
  },
  description: {
    marginTop: 5,
    fontSize: 12,
    color: 'rgba(100, 100, 100, 0.7)'
  }
});


const ds = new ListView.DataSource({
  rowHasChanged: (r1, r2) => r1 !== r2
});


const data = ds.cloneWithRows([
  {
    id: 2,
    title: '<PieChart>',
    description: 'Displays a PieChart'
  }, {
    id: 3,
    title: '<BarChart>',
    description: 'Displays a BarChart'
  }, {
    id: 4,
    title: '<StackedBarChart>',
    description: 'Displays a StackedBarChart'
  }, {
    id: 5,
    title: '<LineChart>',
    description: 'Displays a LineChart'
  }, {
    id: 6,
    title: '<RadarChart>',
    description: 'Displays a RadarChart'
  }, {
    id: 7,
    title: '<BubbleChart>',
    description: 'Displays a BubbleChart'
  }, {
    id: 8,
    title: '<ScatterChart>',
    description: 'Displays a ScatterChart'
  }, {
    id: 9,
    title: '<CandleStickChart>',
    description: 'Displays a CandleStickChart'
  }, {
    id: 10,
    title: '<TimeSeriesLineChart>',
    description: 'Displays a Time Series Line Chart with custom marker'
  }, {
    id: 11,
    title: '<CombinedChart>',
    description: 'Displays a CombinedChart with Bar and Line data.'
  }, {
    id: 12,
    title: '<ZeroLineChart>',
    description: 'Displays a zero-based BarChart.'
  }, {
    id: 13,
    title: 'Live Updating graph',
    description: 'Live updating a line chart'
  }, {
    id: 14,
    title: '<GroupBarChart>',
    description: 'Displays a group BarChart'
  },{
    id: 15,
    title: '<HorizontalBarChart>',
    description: 'Displays a HorizontalBarChart'
  },{
    id: 16,
    title: '<AxisLineChart>',
    description: 'Displays a AxisLineChart'
  }



]);

class Navbar extends React.Component {
  handleBack = () => {
    this.props.navigator.pop();
  };
  render() {
    const backStyles = this.props.routeId === 1 ? [styles.navText, styles.right] : styles.navText;
    return (
      <View style={styles.bar}>
        <TouchableHighlight
          underlayColor='white'
          onPress={this.handleBack}
        >
          <Text style={backStyles}>Back</Text>
        </TouchableHighlight>
        <Text style={styles.navText}>{this.props.title}</Text>
        <Text style={styles.right}>Back</Text>
      </View>
    );
  }
}

class ChartList extends React.Component {
  static displayName = 'ChartList';

  renderRow = (row) => {
    const handlePress = () => {
      this.props.navigator.push({id: row.id});
    };

    return (
      <TouchableHighlight
        onPress={handlePress}
        underlayColor='rgba(200, 200, 200, 0.3)'
      >
        <View style={styles.listItem}>
          <Text style={styles.title}>{row.title}</Text>
          <Text style={styles.description}>{row.description}</Text>
        </View>
      </TouchableHighlight>
    );
  };

  render() {
    return (
      <ListView
        dataSource={data}
        renderRow={this.renderRow}
        style={styles.list}
      />
    );
  }
}

class ChartsExplorer extends React.Component {

  render() {
    return (
      <Navigator
        configureScene={() => {
          return {
            ...Navigator.SceneConfigs.HorizontalSwipeJump,
            gestures: null
          };
        }}
        initialRoute={{ id: 1 }}
        renderScene={this.renderScene}
      />
    );
  }


  renderScene(route, navigator) {
    let content = null;
    let navText = 'ChartExplorer';
    switch (route.id) {
      case 1:
        content = <ChartList navigator={navigator}/>;
        navText = 'ChartExplorer';
        break;
      case 2:
        content = <PieChartScreen/>;
        navText = 'PieChart';
        break;
      case 3:
        content = <BarChartScreen/>;
        navText = 'BarChart';
        break;
      case 4:
        content = <StackedBarChartScreen/>;
        navText = 'StackedBarChart';
        break;

      case 5:
        content = <LineChartScreen/>;
        navText = 'LineChart';
        break;
      case 6:
        content = <RadarChartScreen/>;
        navText = 'RadarChart';
        break;
      case 7:
        content = <BubbleChartScreen/>;
        navText = 'BubbleChart';
        break;
      case 8:
        content = <ScatterChartScreen/>;
        navText = 'ScatterChart';
        break;
      case 9:
        content = <CandleStickChartScreen/>;
        navText = 'CandleStickChart';
        break;
      case 10:
        content = <TimeSeriesLineChartScreen/>;
        navText = 'TimeSeriesLineChart';
        break;

      case 11:
        content = <CombinedChartScreen/>;
        navText = 'CombinedChart';
        break;
      case 12:
        content = <ZeroLineChartScreen/>;
        navText = 'ZeroLineChart';
        break;

      case 13:
        content = <LiveUpdateChartScreen/>;
        navText = 'Live Updating';
        break;

      case 14:
        content = <GroupBarChartScreen/>;
        navText = 'GroupBarChart';
        break;

      case 15:
        content = <HorizontalBarChartScreen/>;
        navText = 'HorizontalBarChart';
        break;

      case 16:
        content = <AxisLineChartScreen/>;
        navText = 'AxisLineChart';
        break;

      default:
        content = null;
        break;
    }

    if (route.id !== 1) {
      content = <View style={styles.container}>{content}</View>;
    }
    return (
      <View style={{flex: 1}}>
        <Navbar
          navigator={navigator}
          title={navText}
          routeId={route.id}
        />
        {content}
      </View>
    );
  }

  renderRow(rowData: Object) {
    return (
      <TouchableHighlight
        onPress={() => this.props.navigator.push({name: rowData.screenName})}
      >
        <View style={s.row}>
          <Text>{rowData.label}</Text>
        </View>
      </TouchableHighlight>
    );
  }
}

export default ChartsExplorer;
