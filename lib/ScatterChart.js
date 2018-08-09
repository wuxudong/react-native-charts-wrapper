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
  nativeOnly: {onChartSelect: true, onChange: true}
});

export default HighlightEnhancer(ScaleEnhancer(MoveEnhancer(ScatterChart)))
