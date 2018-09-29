import PropTypes from 'prop-types';
import React, {Component} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import {combinedData} from './ChartDataConfig';
import MoveEnhancer from './MoveEnhancer'
import ScaleEnhancer from "./ScaleEnhancer";
import HighlightEnhancer from "./HighlightEnhancer";
import ScrollEnhancer from "./ScrollEnhancer";

class CombinedChart extends React.Component {
  getNativeComponentName() {
    return 'RNCombinedChart'
  }

  getNativeComponentRef() {
    return this.nativeComponentRef
  }

  render() {
    return <RNCombinedChart {...this.props} ref={ref => this.nativeComponentRef = ref} />;
  }

}

CombinedChart.propTypes = {
  ...BarLineChartBase.propTypes,

  data: combinedData
};

var RNCombinedChart = requireNativeComponent('RNCombinedChart', CombinedChart, {
  nativeOnly: {onSelect: true, onChange: true}
});

export default ScrollEnhancer(HighlightEnhancer(ScaleEnhancer(MoveEnhancer(CombinedChart))))
