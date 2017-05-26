import * as PropTypes from 'prop-types';
import {
  View,
  ViewProperties,
} from 'react-native';

import ChartBase from './ChartBase';

const iface = {
  propTypes: {
    ...ChartBase.propTypes,

    rotationEnabled:PropTypes.bool,
  }
};

export default iface;
