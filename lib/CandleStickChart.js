import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
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
