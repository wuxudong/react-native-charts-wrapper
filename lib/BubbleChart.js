import { requireNativeComponent, } from 'react-native';
import ChartBase from './ChartBase';
import { bubbleData } from './ChartDataConfig';
const iface = {
    name: 'BubbleChart',
    propTypes: {
        ...ChartBase.propTypes,
        data: bubbleData
    }
};
export default requireNativeComponent('RNBubbleChart', iface, {
    nativeOnly: { onSelect: true }
});
