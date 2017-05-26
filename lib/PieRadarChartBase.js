import { PropTypes } from 'react';
import ChartBase from './ChartBase';
const iface = {
    propTypes: {
        ...ChartBase.propTypes,
        rotationEnabled: PropTypes.bool,
    }
};
export default iface;
