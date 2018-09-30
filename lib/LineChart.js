import PropTypes from 'prop-types';
import React, {Component} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import {lineData} from './ChartDataConfig';
import MoveEnhancer from './MoveEnhancer'
import ScaleEnhancer from "./ScaleEnhancer";
import HighlightEnhancer from "./HighlightEnhancer";
import ScrollEnhancer from "./ScrollEnhancer";

class LineChart extends React.Component {
  getNativeComponentName() {
    return 'RNLineChart'
  }

  getNativeComponentRef() {
    return this.nativeComponentRef
  }

  render() {
    return <RNLineChart {...this.props} ref={ref => this.nativeComponentRef = ref} />;
  }
}

LineChart.propTypes = {
  ...BarLineChartBase.propTypes,

  data: lineData,
};

var RNLineChart = requireNativeComponent('RNLineChart', LineChart, {
  nativeOnly: {onSelect: true, onChange: true}
});

export default ScrollEnhancer(HighlightEnhancer(ScaleEnhancer(MoveEnhancer(LineChart))))
