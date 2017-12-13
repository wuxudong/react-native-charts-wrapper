import PropTypes from 'prop-types';
import React, {Component} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import {combinedData} from './ChartDataConfig';
import MoveEnhancer from './MoveEnhancer'

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
  nativeOnly: {onSelect: true}
});

export default MoveEnhancer(CombinedChart)
