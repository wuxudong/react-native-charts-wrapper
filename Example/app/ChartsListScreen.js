import React from 'react';
import {
  Platform,
  ScrollView,
  TouchableOpacity,
  StyleSheet,
  View,
  Text,
} from 'react-native';

import {StackNavigator, SafeAreaView} from 'react-navigation';

import AxisLineChartScreen from './AxisLineChartScreen';
import MultipleChartScreen from './MultipleChartScreen';
import MovingWindowChartScreen from './MovingWindowChartScreen';
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
  item: {
    backgroundColor: '#fff',
    paddingHorizontal: 16,
    paddingVertical: 12,
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderBottomColor: '#ddd',
  },
  image: {
    width: 120,
    height: 120,
    alignSelf: 'center',
    marginBottom: 20,
    resizeMode: 'contain',
  },
  title: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#444',
  },
  description: {
    fontSize: 13,
    color: '#999',
  },
});

var ExampleRoutes = {
  PieChartScreen: {
    name: 'PieChart',
    screen: PieChartScreen,
    description: 'Displays a PieChart',
  },
  BarChartScreen: {
    name: 'BarChart',
    screen: BarChartScreen,
    description: 'Displays a BarChart',
  },
  StackedBarChartScreen: {
    name: 'StackedBarChart',
    screen: StackedBarChartScreen,
    description: 'Displays a StackedBarChart',
  },
  LineChartScreen: {
    name: 'LineChart',
    screen: LineChartScreen,
    description: 'Displays a LineChart',
  },
  RadarChartScreen: {
    name: 'RadarChart',
    screen: RadarChartScreen,
    description: 'Displays a RadarChart',
  },
  BubbleChartScreen: {
    name: 'BubbleChart',
    screen: BubbleChartScreen,
    description: 'Displays a BubbleChart',
  },
  ScatterChartScreen: {
    name: 'ScatterChart',
    screen: ScatterChartScreen,
    description: 'Displays a ScatterChart',
  },
  CandleStickChartScreen: {
    name: 'CandleStickChart',
    screen: CandleStickChartScreen,
    description: 'Displays a CandleStickChart',
  },
  TimeSeriesLineChartScreen: {
    name: 'TimeSeriesLineChart',
    screen: TimeSeriesLineChartScreen,
    description: 'Displays a Time Series Line Chart with custom marker',
  },
  CombinedChartScreen: {
    name: 'CombinedChart',
    screen: CombinedChartScreen,
    description: 'Displays a CombinedChart with Bar and Line data.',
  },
  ZeroLineChartScreen: {
    name: 'ZeroLineChart',
    screen: ZeroLineChartScreen,
    description: 'Displays a zero-based BarChart.',
  },
  LiveUpdateChartScreen: {
    name: 'LiveUpdateChart',
    screen: LiveUpdateChartScreen,
    description: 'Live updating a line chart',
  },
  GroupBarChartScreen: {
    name: 'GroupBarChart',
    screen: GroupBarChartScreen,
    description: 'Displays a group BarChart',
  },
  HorizontalBarChartScreen: {
    name: 'HorizontalBarChart',
    screen: HorizontalBarChartScreen,
    description: 'Displays a HorizontalBarChart',
  },
  AxisLineChartScreen: {
    name: 'AxisLineChart',
    screen: AxisLineChartScreen,
    description: 'Displays a AxisLineChart',
  },
  MultipleChartScreen: {
    name: 'MultipleChartScreen',
    screen: MultipleChartScreen,
    description: 'Displays a correlated MultipleChartScreen',
  },
  MovingWindowChartScreen: {
    name: 'MovingWindowChartScreen',
    screen: MovingWindowChartScreen,
    description: 'Displays a MovingWindowChartScreen',
  },
};

const MainScreen = ({navigation}) => (

  <ScrollView style={{ flex: 1 }} contentInsetAdjustmentBehavior="automatic">
    {Object.keys(ExampleRoutes).map((routeName) => (
      <TouchableOpacity
        key={routeName}
        onPress={() => {
          const { path, params, screen } = ExampleRoutes[routeName];
          const { router } = screen;
          const action = path && router.getActionForPathAndParams(path, params);
          navigation.navigate(routeName, {}, action);
        }}
      >
        <SafeAreaView
          style={styles.itemContainer}
          forceInset={{ vertical: 'never' }}
        >
          <View style={styles.item}>
            <Text style={styles.title}>{ExampleRoutes[routeName].name}</Text>
            <Text style={styles.description}>
              {ExampleRoutes[routeName].description}
            </Text>
          </View>
        </SafeAreaView>
      </TouchableOpacity>
    ))}
  </ScrollView>
);

MainScreen.navigationOptions = {
  title: 'Home',
};

const ChartsExplorer = StackNavigator(
  {
    Index: {
      screen: MainScreen,
    },
    ...ExampleRoutes,

  },
  {
    initialRouteName: 'Index',
    headerMode: 'screen',


    /*
   * Use modal on iOS because the card mode comes from the right,
   * which conflicts with the drawer example gesture
   */
    mode: Platform.OS === 'ios' ? 'modal' : 'card',
  },
);

export default ChartsExplorer;
