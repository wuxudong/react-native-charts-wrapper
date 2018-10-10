import PropTypes from 'prop-types';

export const axisIface = {
  // what is drawn
  enabled: PropTypes.bool,
  drawLabels: PropTypes.bool,
  drawAxisLine: PropTypes.bool,
  drawGridLines: PropTypes.bool,

  // style
  textColor: PropTypes.number,
  textSize: PropTypes.number,
  fontFamily: PropTypes.string,
  fontStyle: PropTypes.string,
  fontWeight: PropTypes.string,
  gridColor: PropTypes.number,
  gridLineWidth: PropTypes.number,
  axisLineColor: PropTypes.number,
  axisLineWidth: PropTypes.number,
  gridDashedLine: PropTypes.shape({
    lineLength: PropTypes.number,
    spaceLength: PropTypes.number,
    phase: PropTypes.number
  }),

  // limit lines
  limitLines: PropTypes.arrayOf(
    PropTypes.shape({
      limit: PropTypes.number.isRequired,
      label: PropTypes.string,
      lineColor: PropTypes.number,
      lineWidth: PropTypes.number,
      valueTextColor: PropTypes.number,
      valueFont: PropTypes.number,
      labelPosition: PropTypes.oneOf(['LEFT_TOP', 'LEFT_BOTTOM', 'RIGHT_TOP', 'RIGHT_BOTTOM']),
      lineDashPhase: PropTypes.number,
      lineDashLengths: PropTypes.arrayOf(PropTypes.number)
    })
  ),
  drawLimitLinesBehindData: PropTypes.bool,

  axisMaximum: PropTypes.number,
  axisMinimum: PropTypes.number,
  granularity: PropTypes.number,
  granularityEnabled: PropTypes.bool,

  labelCount: PropTypes.number,
  labelCountForce: PropTypes.bool,

  centerAxisLabels: PropTypes.bool, // Centers the axis labels instead of drawing them at their original position. This is useful especially for grouped BarChart.

  // formatting
  valueFormatter: PropTypes.oneOfType([
    PropTypes.oneOf(['largeValue', 'percent', 'date']),
    PropTypes.string,
    PropTypes.arrayOf(PropTypes.string)
  ]),

  // valueFormatterPattern, since, timeUnit are used when valueFormatter is 'date'
  // since: milliseconds of 2018-6-1, timeUnit: DAYS, x:9, valueFormatterPattern: YYYY-MM-dd
  // will display 2018-6-10
  valueFormatterPattern: PropTypes.string,
  since: PropTypes.number, // milliseconds from 1970-1-1 when x=0
  timeUnit: PropTypes.oneOf(['MILLISECONDS', 'SECONDS', 'MINUTES', 'HOURS', 'DAYS']), // timeUnit of x,
};

export const xAxisIface = {
  ...axisIface,

  labelRotationAngle: PropTypes.number,
  avoidFirstLastClipping: PropTypes.bool,
  position: PropTypes.oneOf(['TOP', 'BOTTOM', 'BOTH_SIDED', 'TOP_INSIDE', 'BOTTOM_INSIDE']),
  yOffset: PropTypes.number
};

export const yAxisIface = {
  ...axisIface,

  inverted: PropTypes.bool,
  spaceTop: PropTypes.number,
  spaceBottom: PropTypes.number,

  position: PropTypes.oneOf(['OUTSIDE_CHART', 'INSIDE_CHART']),

  maxWidth: PropTypes.number,
  minWidth: PropTypes.number,

  // zero line
  zeroLine: PropTypes.shape({
    enabled: PropTypes.bool,
    lineWidth: PropTypes.number,
    lineColor: PropTypes.number
  })
};
