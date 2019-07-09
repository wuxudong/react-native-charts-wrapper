import PropTypes from 'prop-types';
import ChartDataSetConfig from './ChartDataSetConfig';


const lineData = PropTypes.shape({
  dataSets: PropTypes.arrayOf(PropTypes.shape({
    values: PropTypes.arrayOf(
      PropTypes.oneOfType([
        PropTypes.shape({
          x: PropTypes.number,
          y: PropTypes.number.isRequired,
          marker: PropTypes.string
        }),
        PropTypes.number
      ])
    ),
    label: PropTypes.string.isRequired,
    config: PropTypes.shape({
      ...ChartDataSetConfig.common,
      ...ChartDataSetConfig.barLineScatterCandleBubble,
      ...ChartDataSetConfig.lineScatterCandleRadar,
      ...ChartDataSetConfig.lineRadar,

      circleRadius: PropTypes.number,
      drawCircles: PropTypes.bool,
      mode: PropTypes.oneOf(['LINEAR', 'STEPPED', 'CUBIC_BEZIER', 'HORIZONTAL_BEZIER']),
      drawCubicIntensity: PropTypes.number,
      circleColor: PropTypes.number,
      circleColors: PropTypes.arrayOf(PropTypes.number),
      circleHoleColor: PropTypes.number,
      drawCircleHole: PropTypes.bool,

      dashedLine: PropTypes.shape({
        lineLength: PropTypes.number.isRequired,
        spaceLength: PropTypes.number.isRequired,
        phase: PropTypes.number,
      })
    })
  }))
})

const barData = PropTypes.shape({
  dataSets: PropTypes.arrayOf(PropTypes.shape({
    values: PropTypes.arrayOf(
      PropTypes.oneOfType([
        PropTypes.shape({
          x: PropTypes.number,
          y: PropTypes.oneOfType([
            PropTypes.number,
            PropTypes.arrayOf(PropTypes.number)
          ]),
          marker: PropTypes.oneOfType([PropTypes.string, PropTypes.arrayOf(PropTypes.string)])
        }),
        PropTypes.oneOfType([
          PropTypes.number,
          PropTypes.arrayOf(PropTypes.number)
        ])
      ])
    ),
    label: PropTypes.string.isRequired,
    config: PropTypes.shape({
      ...ChartDataSetConfig.common,
      ...ChartDataSetConfig.barLineScatterCandleBubble,

      barShadowColor: PropTypes.number,
      highlightAlpha: PropTypes.number,  // using android format (0-255), not ios format(0-1), the conversion is x/255
      stackLabels: PropTypes.arrayOf(PropTypes.string)
    })
  })),

  config: PropTypes.shape({
    barWidth: PropTypes.number,
    group: PropTypes.shape({
      fromX: PropTypes.number.isRequired,
      groupSpace: PropTypes.number.isRequired,
      barSpace: PropTypes.number.isRequired
    })
  })

})

const bubbleData = PropTypes.shape({
  dataSets: PropTypes.arrayOf(PropTypes.shape({
    values: PropTypes.arrayOf(PropTypes.shape({
      x: PropTypes.number,
      y: PropTypes.number.isRequired,
      size: PropTypes.number.isRequired,
      marker: PropTypes.string,
    })),
    label: PropTypes.string.isRequired,
    config: PropTypes.shape({
      ...ChartDataSetConfig.common,
      ...ChartDataSetConfig.barLineScatterCandleBubble,

    })
  }))
})

const candleData = PropTypes.shape({
  dataSets: PropTypes.arrayOf(PropTypes.shape({
    values: PropTypes.arrayOf(
      PropTypes.shape({
        x: PropTypes.number,
        shadowH: PropTypes.number.isRequired,
        shadowL: PropTypes.number.isRequired,
        open: PropTypes.number.isRequired,
        close: PropTypes.number.isRequired,
        marker: PropTypes.string,
      })
    ),
    label: PropTypes.string.isRequired,
    config: PropTypes.shape({
      ...ChartDataSetConfig.common,
      ...ChartDataSetConfig.barLineScatterCandleBubble,
      ...ChartDataSetConfig.lineScatterCandleRadar,

      barSpace: PropTypes.number,
      shadowWidth: PropTypes.number,
      shadowColor: PropTypes.number,
      shadowColorSameAsCandle: PropTypes.bool,
      neutralColor: PropTypes.number,
      decreasingColor: PropTypes.number,
      decreasingPaintStyle: PropTypes.oneOf(['FILL', 'STROKE', 'FILL_AND_STROKE']),
      increasingColor: PropTypes.number,
      increasingPaintStyle: PropTypes.oneOf(['FILL', 'STROKE', 'FILL_AND_STROKE'])
    })
  })),
})

const pieData = PropTypes.shape({
  dataSets: PropTypes.arrayOf(PropTypes.shape({
    values: PropTypes.arrayOf(
      PropTypes.oneOfType([
        PropTypes.shape({
          value: PropTypes.number.isRequired,
          label: PropTypes.string
        }),
        PropTypes.number
      ])
    ),
    label: PropTypes.string.isRequired,
    config: PropTypes.shape({
      ...ChartDataSetConfig.common,

      sliceSpace: PropTypes.number,
      selectionShift: PropTypes.number,
      xValuePosition: PropTypes.oneOf(['INSIDE_SLICE', 'OUTSIDE_SLICE']),
      yValuePosition: PropTypes.oneOf(['INSIDE_SLICE', 'OUTSIDE_SLICE']),
      valueLinePart1Length: PropTypes.number,
      valueLinePart2Length: PropTypes.number,
      valueLineColor: PropTypes.number,
      valueLineWidth: PropTypes.number,
      valueLinePart1OffsetPercentage: PropTypes.number,
      valueLineVariableLength: PropTypes.bool
    })
  })),
})

const radarData = PropTypes.shape({
  dataSets: PropTypes.arrayOf(PropTypes.shape({
    values: PropTypes.arrayOf(
      PropTypes.oneOfType([
        PropTypes.shape({value: PropTypes.number.isRequired,}),
        PropTypes.number
      ])
    ),
    label: PropTypes.string.isRequired,
    config: PropTypes.shape({
      ...ChartDataSetConfig.common,
      ...ChartDataSetConfig.lineScatterCandleRadar,
      ...ChartDataSetConfig.lineRadar

    })
  })),
  labels: PropTypes.arrayOf(PropTypes.string)
})

const scatterData = PropTypes.shape({
  dataSets: PropTypes.arrayOf(PropTypes.shape({
    values: PropTypes.arrayOf(
      PropTypes.oneOfType([
        PropTypes.shape({
          x: PropTypes.number,
          y: PropTypes.number.isRequired,
          marker: PropTypes.string,
        }),
        PropTypes.number
      ])
    ),
    label: PropTypes.string.isRequired,
    config: PropTypes.shape({
      ...ChartDataSetConfig.common,
      ...ChartDataSetConfig.barLineScatterCandleBubble,
      ...ChartDataSetConfig.lineScatterCandleRadar,

      scatterShapeSize: PropTypes.number,
      scatterShape: PropTypes.oneOf(['SQUARE', 'CIRCLE', 'TRIANGLE', 'CROSS', 'X']),
      scatterShapeHoleColor: PropTypes.number,
      scatterShapeHoleRadius: PropTypes.number
    })
  }))
})

const combinedData = PropTypes.shape({
  lineData: lineData,
  barData: barData,
  scatterData: scatterData,
  candleData: candleData,
  bubbleData: bubbleData
})

export {lineData, barData, pieData, bubbleData, scatterData, candleData, radarData, combinedData};
