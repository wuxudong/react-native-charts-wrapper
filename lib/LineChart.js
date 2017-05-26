import { requireNativeComponent, } from 'react-native';
import BarLineChartBase from './BarLineChartBase';
import { lineData } from './ChartDataConfig';
const iface = {
    name: 'LineChart',
    propTypes: {
        ...BarLineChartBase.propTypes,
        data: lineData,
    }
};
export default requireNativeComponent('RNLineChart', iface, {
    nativeOnly: { onSelect: true }
});
