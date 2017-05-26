import * as PropTypes from 'prop-types';
import {
  requireNativeComponent,
  View,
  ViewProperties,
} from 'react-native';

import PieRadarChartBase from './PieRadarChartBase';
import {yAxisIface} from './AxisIface';
import {radarData} from './ChartDataConfig';

const iface = {
  name: 'RadarChart',
  propTypes: {
    ...PieRadarChartBase.propTypes,

    yAxis: PropTypes.shape(yAxisIface),

    drawWeb:PropTypes.bool,
    skipWebLineCount: PropTypes.number,

    data: radarData
  }
};

export default requireNativeComponent('RNRadarChart', iface, {
  nativeOnly: { onSelect: true }
});
