import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import {barData} from './ChartDataConfig';

const iface = {
  name: 'HorizontalBarChart',
  propTypes: {
    ...BarLineChartBase.propTypes,

    drawValueAboveBar: PropTypes.bool,
    drawBarShadow: PropTypes.bool,

    data:  barData
  }
};

export default requireNativeComponent('RNHorizontalBarChart', iface, {
  nativeOnly: { onSelect: true, onChartGestureEnd: true }
});
