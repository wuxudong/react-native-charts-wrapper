import {PropTypes} from 'react';
import {
  View
} from 'react-native';

import ChartBase from './ChartBase';

const iface = {
  propTypes: {
    ...ChartBase.propTypes,

    rotationEnabled:PropTypes.bool,
  }
};

export default iface;
