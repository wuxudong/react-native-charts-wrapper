import PropTypes from 'prop-types';
import React, {Component} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import {bubbleData} from './ChartDataConfig';
import MoveEnhancer from './MoveEnhancer'
import ScaleEnhancer from "./ScaleEnhancer";

class BubbleChart extends React.Component {
  getNativeComponentName() {
    return 'RNBubbleChart'
  }

  getNativeComponentRef() {
    return this.nativeComponentRef
  }

  render() {
    return <RNBubbleChart {...this.props} ref={ref => this.nativeComponentRef = ref} />;
  }
}

BubbleChart.propTypes = {
  ...BarLineChartBase.propTypes,

  data: bubbleData
};

var RNBubbleChart = requireNativeComponent('RNBubbleChart', BubbleChart, {
  nativeOnly: {onChartSelect: true, onChange: true}
});

export default ScaleEnhancer(MoveEnhancer(BubbleChart))

