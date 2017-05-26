import { requireNativeComponent } from 'react-native';
import BarLineChartBase from './BarLineChartBase';
import { scatterData } from './ChartDataConfig';
const iface = {
    name: 'ScatterChart',
    propTypes: {
        ...BarLineChartBase.propTypes,
        data: scatterData
    }
};
export default requireNativeComponent('RNScatterChart', iface, {
    nativeOnly: { onSelect: true }
});
