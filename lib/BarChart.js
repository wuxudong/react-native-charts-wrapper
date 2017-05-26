import * as PropTypes from 'prop-types';
import { requireNativeComponent, } from 'react-native';
import BarLineChartBase from './BarLineChartBase';
import { barData } from './ChartDataConfig';
const iface = {
    name: 'BarChart',
    propTypes: {
        ...BarLineChartBase.propTypes,
        drawValueAboveBar: PropTypes.bool,
        drawBarShadow: PropTypes.bool,
        data: barData
    }
};
export default requireNativeComponent('RNBarChart', iface, {
    nativeOnly: { onSelect: true }
});
