import * as PropTypes from 'prop-types';
import {
  requireNativeComponent,
  View,
  ViewProperties,
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
  nativeOnly: { onSelect: true }
});
