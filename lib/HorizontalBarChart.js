import PropTypes from 'prop-types';
import React, {Component} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import {barData} from './ChartDataConfig';
import MoveEnhancer from './MoveEnhancer'
import ScaleEnhancer from "./ScaleEnhancer";
import HighlightEnhancer from "./HighlightEnhancer";

class HorizontalBarChart extends React.Component {
  getNativeComponentName() {
    return 'RNHorizontalBarChart'
  }

  getNativeComponentRef() {
    return this.nativeComponentRef
  }

  render() {
    return <RNHorizontalBarChart {...this.props} ref={ref => this.nativeComponentRef = ref} />;
  }

}

HorizontalBarChart.propTypes = {
  ...BarLineChartBase.propTypes,

  drawValueAboveBar: PropTypes.bool,
  drawBarShadow: PropTypes.bool,
  highlightFullBarEnabled: PropTypes.bool,

  data: barData
};

var RNHorizontalBarChart = requireNativeComponent('RNHorizontalBarChart', HorizontalBarChart, {
  nativeOnly: {onSelect: true, onChange: true}
});

export default HighlightEnhancer(ScaleEnhancer(MoveEnhancer(HorizontalBarChart)))