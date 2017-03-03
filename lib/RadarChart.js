import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
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

export default requireNativeComponent('RNRadarChart', iface);
