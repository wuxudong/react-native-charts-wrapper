import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import {combinedData} from './ChartDataConfig';

const iface = {
  name: 'CombinedChart',
  propTypes: {
    ...BarLineChartBase.propTypes,

    data: combinedData
  }
};

export default requireNativeComponent('RNCombinedChart', iface, {
  nativeOnly: { onSelect: true }
});
