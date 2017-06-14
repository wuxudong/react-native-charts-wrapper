import {PropTypes} from 'react';
import {
  View
} from 'react-native';

import ChartBase from './ChartBase';
import {yAxisIface} from './AxisIface';

const iface = {
  propTypes: {
    ...ChartBase.propTypes,

    drawGridBackground: PropTypes.bool,
    gridBackgroundColor: PropTypes.number,

    drawBorders: PropTypes.bool,
    borderColor: PropTypes.number,
    borderWidth: PropTypes.number,

    maxVisibleValueCount: PropTypes.number,
    autoScaleMinMaxEnabled: PropTypes.bool,
    keepPositionOnRotation: PropTypes.bool,

    scaleEnabled: PropTypes.bool,
    scaleXEnabled: PropTypes.bool,
    scaleYEnabled: PropTypes.bool,
    dragEnabled: PropTypes.bool,
    pinchZoom: PropTypes.bool,
    doubleTapToZoomEnabled: PropTypes.bool,

    yAxis: PropTypes.shape({
      left: PropTypes.shape(yAxisIface),
      right: PropTypes.shape(yAxisIface)
    }),
    zoom: PropTypes.shape({
      scaleX: PropTypes.number.isRequired,
      scaleY: PropTypes.number.isRequired,
      xValue: PropTypes.number.isRequired,
      yValue: PropTypes.number.isRequired,
      axisDependency: PropTypes.oneOf(['LEFT', 'RIGHT'])
    }),
    viewPortOffsets: PropTypes.shape({
      left: PropTypes.number,
      top: PropTypes.number, 
      right: PropTypes.number,
      bottom: PropTypes.number
    }),
  }
};

export default iface;
