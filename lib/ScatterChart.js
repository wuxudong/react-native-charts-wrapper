import React, {Component} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import {scatterData} from './ChartDataConfig';
import MoveEnhancer from './MoveEnhancer'
import ScaleEnhancer from "./ScaleEnhancer";
import HighlightEnhancer from "./HighlightEnhancer";
import ScrollEnhancer from "./ScrollEnhancer";

class ScatterChart extends React.Component {
  getNativeComponentName() {
    return 'RNScatterChart'
  }

  getNativeComponentRef() {
    return this.nativeComponentRef
  }

  render() {
    return <RNScatterChart {...this.props} ref={ref => this.nativeComponentRef = ref} />;
  }
}

ScatterChart.propTypes = {
  ...BarLineChartBase.propTypes,

  data: scatterData
};

var RNScatterChart = requireNativeComponent('RNScatterChart', ScatterChart, {
  nativeOnly: {onSelect: true, onChange: true}
});

export default ScrollEnhancer(HighlightEnhancer(ScaleEnhancer(MoveEnhancer(ScatterChart))))
