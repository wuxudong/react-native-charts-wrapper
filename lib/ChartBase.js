import PropTypes from 'prop-types';
import {
  View
} from 'react-native';

import {xAxisIface} from './AxisIface'

const descriptionIface = {
  text: PropTypes.string,
  textColor: PropTypes.number,
  textSize: PropTypes.number,

  positionX: PropTypes.number,
  positionY: PropTypes.number,
};

const legendIface = {
  enabled: PropTypes.bool,

  textColor: PropTypes.number,
  textSize: PropTypes.number,
  fontFamily: PropTypes.string,
  fontStyle: PropTypes.number,
  fontWeight: PropTypes.number,

  wordWrapEnabled: PropTypes.bool,
  maxSizePercent: PropTypes.number,

  horizontalAlignment: PropTypes.string, // LEFT, CENTER, RIGHT
  verticalAlignment: PropTypes.string, // TOP, CENTER, BOTTOM
  orientation: PropTypes.string, // HORIZONTAL, VERTICAL
  drawInside: PropTypes.bool,
  direction: PropTypes.string, // LEFT_TO_RIGHT, RIGHT_TO_LEFT

  form: PropTypes.string, // NONE, EMPTY, DEFAULT, SQUARE, CIRCLE, LINE
  formSize: PropTypes.number,
  xEntrySpace: PropTypes.number,
  yEntrySpace: PropTypes.number,
  formToTextSpace: PropTypes.number,

  custom: PropTypes.shape({
    colors: PropTypes.arrayOf(PropTypes.number),
    labels: PropTypes.arrayOf(PropTypes.string)
  })
};

const chartIface = {
  propTypes: {
    ...View.propTypes,

    animation: PropTypes.shape({
      durationX: PropTypes.number, // Millis
      durationY: PropTypes.number, // Millis

      easingX: PropTypes.string,
      easingY: PropTypes.string
    }),

    chartBackgroundColor: PropTypes.number,
    logEnabled: PropTypes.bool,
    noDataText: PropTypes.string,

    touchEnabled: PropTypes.bool,
    dragDecelerationEnabled: PropTypes.bool,
    dragDecelerationFrictionCoef: (props, propName, componentName) => {
      let coef = props[propName];
      if (coef && (typeof coef !== 'number' || coef < 0 || coef > 1)) {
        return new Error(
          `Invalid prop ${propName} supplied to '${componentName}'. Value must be number and between 0 and 1.`
        );
      }
    },

    chartDescription: PropTypes.shape(descriptionIface),

    legend: PropTypes.shape(legendIface),

    xAxis: PropTypes.shape(xAxisIface),

    marker: PropTypes.shape({
      enabled: PropTypes.bool,
      digits: PropTypes.number,
      markerColor: PropTypes.number,
      textColor: PropTypes.number,
      textSize: PropTypes.number,

    }),

    // stackIndex for StackBarChart
    highlights: PropTypes.arrayOf(
      PropTypes.shape({
        x: PropTypes.number.isRequired,
        dataSetIndex: PropTypes.number,  // this is used in stacked bar chart
        dataIndex: PropTypes.number,  // this is necessary in combined chart when default highlight is set. the default sequence is line, bar, scatter, candle, bubble
        y: PropTypes.number,
        stackIndex: PropTypes.number
      }))
  }
};

export default chartIface;
