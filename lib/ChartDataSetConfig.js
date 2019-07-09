import PropTypes from 'prop-types';
import {
  requireNativeComponent,
  View
} from 'react-native';


const chartDataSetConfig = {
  common: {
    color: PropTypes.number,
    colors: PropTypes.arrayOf(PropTypes.number),
    highlightEnabled:PropTypes.bool,
    drawValues: PropTypes.bool,
    valueTextSize:PropTypes.number,
    valueTextColor:PropTypes.number,
    visible:PropTypes.bool,
    valueFormatter: PropTypes.oneOfType([
      PropTypes.oneOf(['largeValue', 'percent', 'date']),
      PropTypes.string,
      PropTypes.arrayOf(PropTypes.string)
    ]),
    valueFormatterPattern: PropTypes.string,
    axisDependency:PropTypes.oneOf(['LEFT', 'RIGHT'])
  },

  barLineScatterCandleBubble: {
    highlightColor: PropTypes.number
  },

  lineScatterCandleRadar: {
    drawVerticalHighlightIndicator: PropTypes.bool,
    drawHorizontalHighlightIndicator: PropTypes.bool,
    highlightLineWidth: PropTypes.number
  },

  lineRadar: {
    fillGradient: PropTypes.shape({
      colors: PropTypes.arrayOf(PropTypes.number),
      // iOS
      positions: PropTypes.arrayOf(PropTypes.number),
      angle: PropTypes.number,
      // Android
      orientation: PropTypes.oneOf([
        // draw the gradient from the top to the bottom
        'TOP_BOTTOM',
        // draw the gradient from the top-right to the bottom-left
        'TR_BL',
        // draw the gradient from the right to the left
        'RIGHT_LEFT',
        // draw the gradient from the bottom-right to the top-left
        'BR_TL',
        // draw the gradient from the bottom to the top
        'BOTTOM_TOP',
        // draw the gradient from the bottom-left to the top-right
        'BL_TR',
        // draw the gradient from the left to the right
        'LEFT_RIGHT',
        // draw the gradient from the top-left to the bottom-right
        'TL_BR',
      ]),
    }),
    fillColor: PropTypes.number,
    fillAlpha: PropTypes.number,
    drawFilled: PropTypes.bool,
    lineWidth: (props, propName, componentName) => {
      let lineWidth = props[propName];
      if (lineWidth && (typeof lineWidth !== 'number' || lineWidth < 0.2 || lineWidth > 10)) {
        return new Error(
          `Invalid prop ${propName} supplied to '${componentName}'. Value must be number and between 0.2f and 10f`
        );
      }
    }
  }
};

export default chartDataSetConfig;