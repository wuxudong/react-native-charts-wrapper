import * as PropTypes from 'prop-types';
import ChartBase from './ChartBase';
const iface = {
    propTypes: {
        ...ChartBase.propTypes,
        rotationEnabled: PropTypes.bool,
    }
};
export default iface;
