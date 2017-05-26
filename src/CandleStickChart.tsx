import * as PropTypes from 'prop-types';
import {
  requireNativeComponent,
  View,
  ViewProperties,
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import {candleData} from './ChartDataConfig';

const iface = {
  name: 'CandleStickChart',
  propTypes: {
    ...BarLineChartBase.propTypes,

    data: candleData
  }
};

export default requireNativeComponent('RNCandleStickChart', iface, {
  nativeOnly: { onSelect: true }
});
