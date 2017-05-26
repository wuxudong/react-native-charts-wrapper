import { PropTypes } from 'react';
const chartDataSetConfig = {
    common: {
        colors: PropTypes.arrayOf(PropTypes.number),
        highlightEnabled: PropTypes.bool,
        drawValues: PropTypes.bool,
        valueTextSize: PropTypes.number,
        valueTextColor: PropTypes.number,
        visible: PropTypes.bool,
        valueFormatter: PropTypes.oneOfType([
            PropTypes.oneOf(['largeValue', 'percent']),
            PropTypes.string
        ]),
        axisDependency: PropTypes.string
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
        fillColor: PropTypes.number,
        fillAlpha: PropTypes.number,
        drawFilled: PropTypes.bool,
        lineWidth: (props, propName, componentName) => {
            let lineWidth = props[propName];
            if (lineWidth && (typeof lineWidth !== 'number' || lineWidth < 0.2 || lineWidth > 10)) {
                return new Error(`Invalid prop ${propName} supplied to '${componentName}'. Value must be number and between 0.2f and 10f`);
            }
        }
    }
};
export default chartDataSetConfig;
