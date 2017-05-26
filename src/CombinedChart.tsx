import * as PropTypes from 'prop-types';
import {
  requireNativeComponent,
  View,
  ViewProperties,
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
