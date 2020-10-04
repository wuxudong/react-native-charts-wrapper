# Documentation


## Description (prop for all charts)

| Prop         | Type     | Default | Note |
| ------------ | -------- | ------- | ---- |
| `text`       | `string` |         |      |
| `textColor`  | `number` |         |      |
| `textSize`   | `number` |         |      |
| `positionX`  | `number` |         |      |
| `positionY`  | `number` |         |      |

## Legend (prop for all charts)

| Prop              | Type                                                          | Default | Note |
| ----------------- | ------------------------------------------------------------- | ------- | ---- |
| `enabled`         | `bool`                                                        |         |      |
| `text`            | `string`                                                      |         |      |
| `textColor`       | `number`                                                      |         |      |
| `textSize`        | `number`                                                      |         |      |
| `fontFamily`      | `string`                                                      |         |      |
| `fontStyle`       | `number`                                                      |         |      |
| `wordWrapEnabled` | `bool`                                                        |         |      |
| `maxSizePercent`  | `number`                                                      |         |      |
| `position`        | `number`                                                      |         |      |
| `form`            | `string`                                                      |         |      |
| `formSize`        | `number`                                                      |         |      |
| `xEntrySpace`     | `bool`                                                        |         |      |
| `yEntrySpace`     | `number`                                                      |         |      |
| `formToTextSpace` | `number`                                                      |         |      |
| `custom`          | `{`<br />`colors: [number],`<br />`labels: [string]`<br />`}` |         |      |

## Common Props for xAxis and yAxis

| Prop                       | Type                                                                                     | Default | Note                                                                                                                        |
| -------------------------- | ---------------------------------------------------------------------------------------- | ------- | --------------------------------------------------------------------------------------------------------------------------- |
| `enabled`                  | `bool`                                                                                   |         |                                                                                                                             |
| `drawLabels`               | `bool`                                                                                   |         |                                                                                                                             |
| `drawAxisLine`             | `bool`                                                                                   |         |                                                                                                                             |
| `drawGridLines`            | `bool`                                                                                   |         |                                                                                                                             |
| `textColor`                | `number`                                                                                 |         |                                                                                                                             |
| `textSize`                 | `number`                                                                                 |         |                                                                                                                             |
| `fontFamily`               | `string`                                                                                 |         |                                                                                                                             |
| `fontStyle`                | `number`                                                                                 |         |                                                                                                                             |
| `gridColor`                | `bool`                                                                                   |         |                                                                                                                             |
| `gridLineWidth`            | `bool`                                                                                   |         |                                                                                                                             |
| `axisLineColor`            | `bool`                                                                                   |         |                                                                                                                             |
| `axisLineWidth`            | `bool`                                                                                   |         |                                                                                                                             |
| `gridDashedLine`           | `{`<br />`lineLength: number,`<br />`spaceLength: number,`<br />`phase: number`<br />`}` |         |                                                                                                                             |
| `limitLines`               | array : `[{ limit: number, label: string, lineColor: number, lineWidth: number, valueTextColor: number, valueFont: number, labelPosition: string, lineDashPhase: number, lineDashLengths: [number] }]`                                                                                 |         |                                                                                                                             |
| `drawLimitLinesBehindData` | `bool`                                                                                   |         |                                                                                                                             |
| `axisMaximum`              | `number`                                                                                 |         |                                                                                                                             |
| `axisMinimum`              | `number`                                                                                 |         |                                                                                                                             |
| `granularity`              | `number`                                                                                 |         |                                                                                                                             |
| `granularityEnabled`       | `bool`                                                                                   |         |                                                                                                                             |
| `labelCount`               | `number`                                                                                 |         |                                                                                                                             |
| `labelCountForce`          | `bool`                                                                                   |         |                                                                                                                             |
| `centerAxisLabels`         | `bool`                                                                                   |         | Centers the axis labels instead of drawing them at their original position. This is useful especially for grouped BarChart. |
| `valueFormatter`           | one of `'largeValue', 'percent', 'date', string, [string]`                               |         |                                                                                                                             |
| `valueFormatterPattern`    | `string`                                                                                 |         |

## xAxis

#### Common props plus props below.

| Prop                     | Type     | Default | Note |
| ------------------------ | -------- | ------- | ---- |
| `labelRotationAngle`     | `number` |         |      |
| `avoidFirstLastClipping` | `bool`   |         |      |
| `position`               | `string` |         | Should be in upper case. you will get an error in android if the position is in lower case      |
| `valueFormatterPattern`  | `string` |         |      |

## yAxis

#### Common props plus props below.

| Prop          | Type                                                                                  | Default | Note |
| ------------- | ------------------------------------------------------------------------------------- | ------- | ---- |
| `inverted`    | `number`                                                                              |         |      |
| `spaceTop`    | `bool`                                                                                |         |      |
| `spaceBottom` | `number`                                                                              |         |      |
| `position`    | `number`                                                                              |         |      |
| `maxWidth`    | `bool`                                                                                |         |      |
| `minWidth`    | `string`                                                                              |         |      |
| `zeroLine`    | `{`<br />`enabled: bool,`<br />`lineWidth: number,`<br />`lineColor: number`<br />`}` |         |      |

## Chart Base (All charts have these props)

#### Chart Base inherits props from react-native 'View' in addition to the props below.

| Prop                           | Type                                                                                                                                            | Default | Note                                                                                                                                                                                                                                                        |
| ------------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------- | ------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `animation`                    | `{`<br />`durationX: number,`<br />`durationY: number,`<br />`easingX: string,`<br />`easingY: string`<br />`}`                                 |         | Durations are in milliseconds.                                                                                                                                                                                                                              |
| `chartBackgroundColor`         | `number`                                                                                                                                        |         |                                                                                                                                                                                                                                                             |
| `logEnabled`                   | `bool`                                                                                                                                          |         |                                                                                                                                                                                                                                                             |
| `noDataText`                   | `string`                                                                                                                                        |         |                                                                                                                                                                                                                                                             |
| `touchEnabled`                 | `bool`                                                                                                                                          |         |                                                                                                                                                                                                                                                             |
| `dragDecelerationEnabled`      | `bool`                                                                                                                                          |         |                                                                                                                                                                                                                                                             |
| `dragDecelerationFrictionCoef` | `function`                                                                                                                                      |         |                                                                                                                                                                                                                                                             |
| `chartDescription`             | `Description`                                                                                                                                   |         |                                                                                                                                                                                                                                                             |
| `legend`                       | `Legend`                                                                                                                                        |         |                                                                                                                                                                                                                                                             |
| `xAxis`                        | `XAksis`                                                                                                                                        |         |                                                                                                                                                                                                                                                             |
| `marker`                       | `{`<br />`enabled: bool,`<br />`digits: number,`<br />`markerColor: number,`<br />`textColor: number,`<br />`textSize: number`<br />`}`         |         |                                                                                                                                                                                                                                                             |
| `highlights`                   | Array of <br/>`{`<br />`x: number,`<br />`dataSetIndex: number,`<br />`dataIndex: number,`<br />`y: number,`<br />`stackIndex: number`<br />`}` |         | `x` is required and represents the index of the x values.<br /> `dataSetIndex` is used in stacked bar chart.<br />`dataIndex` is necessary in combined chart when default highlight is set. The default sequence is line, bar, scatter, candle, bubble. |

## BarLineChartBase

#### _Chart Base props plus props listed below_.

| Prop                     | Type                                                                                                                                                            | Default | Note |
| ------------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------- | ---- |
| `drawGridBackground`     | `bool`                                                                                                                                                          |         |      |
| `gridBackgroundColor`    | `number`                                                                                                                                                        |         |      |
| `drawBorders`            | `bool`                                                                                                                                                          |         |      |
| `borderColor`            | `number`                                                                                                                                                        |         |      |
| `borderWidth`            | `number`                                                                                                                                                        |         |      |
| `minOffset`              | `number`                                                                                                                                                        |         |      |
| `maxVisibleValueCount`   | `number`                                                                                                                                                        |         |      |
| `visibleRange`           | `{`<br />`x: { min: number, max: number },`<br />`y: {`<br />`left: { min: number, max: number },`<br />`right: { min: number, max: number }`<br />`}`<br />`}` |         |      |
| `autoScaleMinMaxEnabled` | `bool`                                                                                                                                                          |         |      |
| `keepPositionOnRotation` | `bool`                                                                                                                                                          |         |      |
| `scaleEnabled`           | `bool`                                                                                                                                                          |         |      |
| `scaleXEnabled`          | `bool`                                                                                                                                                          |         |      |
| `scaleYEnabled`          | `bool`                                                                                                                                                          |         |      |
| `dragEnabled`            | `bool`                                                                                                                                                          |         |      |
| `pinchZoom`              | `bool`                                                                                                                                                          |         |      |
| `doubleTapToZoomEnabled` | `bool`                                                                                                                                                          |         |      |
| `yAksis`                 | `{ left: YAksis, right: YAksis }`                                                                                                                               |         |      |
| `zoom`                   | `{`<br />`scaleX: number,`<br />`scaleY: number,`<br />`xValue: number,`<br />`yValue: number,`<br />`axisDependency: 'LEFT' or 'RIGHT'`<br />`}`               |         |      |
| `viewPortOffsets`        | `{`<br />`left: number,`<br />`top: number,`<br />`right: number,`<br />`bottom: number,`<br />`}`                                                              |         |      |

## BarChart

#### _ChartBase props plus props listed below_.

| Prop                | Type                | Default | Note |
| ------------------- | ------------------- | ------- | ---- |
| `drawValueAboveBar` | `bool`              |         |      |
| `drawBarShadow`     | `bool`              |         |      |
| `data`              | `DataTypes.barData` |         |      |

## BubbleChart

#### _BarLineChartBase props plus props listed below_.

| Prop   | Type                   | Default | Note |
| ------ | ---------------------- | ------- | ---- |
| `data` | `DataTypes.bubbleData` |         |      |

## CandleStickChart

#### _BarLineChartBase props plus props listed below_.

| Prop   | Type                   | Default | Note |
| ------ | ---------------------- | ------- | ---- |
| `data` | `DataTypes.candleData` |         |      |

## CombinedChart

#### _BarLineChartBase props plus props listed below_.

| Prop   | Type                     | Default | Note |
| ------ | ------------------------ | ------- | ---- |
| `data` | `DataTypes.combinedData` |         |      |

## HorizontalBarChart

#### _BarLineChartBase props plus props listed below_.

| Prop                | Type                | Default | Note |
| ------------------- | ------------------- | ------- | ---- |
| `drawValueAboveBar` | `bool`              |         |      |
| `drawBarShadow`     | `bool`              |         |      |
| `data`              | `DataTypes.barData` |         |      |

## LineChart

#### _BarLineChartBase props plus props listed below_.

| Prop   | Type                 | Default | Note |
| ------ | -------------------- | ------- | ---- |
| `data` | `DataTypes.lineData` |         |      |

## ScatterChart

#### _BarLineChartBase props plus props listed below_.

| Prop   | Type                    | Default | Note |
| ------ | ----------------------- | ------- | ---- |
| `data` | `DataTypes.scatterData` |         |      |

## PieRadarChartBase

#### _ChartBase props plus props listed below_.

| Prop              | Type     | Default | Note |
| ----------------- | -------- | ------- | ---- |
| `minOffset`       | `number` |         |      |
| `rotationEnabled` | `bool`   |         |      |
| `rotationAngle`   | `number` |         |      |

## PieChart

#### _PieRadarChartBase props plus props listed below_.

| Prop                      | Type                                                                                                   | Default | Note |
| ------------------------- | ------------------------------------------------------------------------------------------------------ | ------- | ---- |
| `extraOffsets`            | `{`<br />`left: number,`<br />`top: number,`<br />`right: number,`<br />`bottom: number,`<br />`}`     |         |      |
| `drawEntryLabels`         | `bool`                                                                                                 |         |      |
| `usePercentValues`        | `bool`                                                                                                 |         |      |
| `centerText`              | `string`                                                                                               |         |      |
| `styledCenterText`        | `{`<br />`text: string,`<br />`color: number,`<br />`fontFamily: string,`<br />`size: number`<br />`}` |         |      |
| `centerTextRadiusPercent` | `number`                                                                                               |         |      |
| `holeRadius`              | `number`                                                                                               |         |      |
| `holeColor`               | `number`                                                                                               |         |      |
| `transparentCircleRadius` | `number`                                                                                               |         |      |
| `transparentCircleColor`  | `number`                                                                                               |         |      |
| `entryLabelColor`         | `number`                                                                                               |         |      |
| `entryLabelTextSize`      | `number`                                                                                               |         |      |
| `entryLabelFontFamily`    | `string`                                                                                               |         |      |
| `maxAngle`                | `number`                                                                                               |         |      |
| `data`                    | `DataTypes.pieData`                                                                                    |         |      |

## RadarChart

#### _PieRadarChartBase props plus props listed below_.

| Prop               | Type                  | Default | Note |
| ------------------ | --------------------- | ------- | ---- |
| `yAxis`            | `YAxis`               |         |      |
| `drawWeb`          | `bool`                |         |      |
| `skipWebLineCount` | `number`              |         |      |
| `data`             | `DataTypes.radarData` |         |      |

# ConfigTypes

```
type common {
  colors: [number],
  highlightEnabled: bool,
  drawValues: bool,
  valueTextSize: number,
  valueTextColor: number,
  visible: bool,
  valueFormatter: string or 'largeValue' or 'percent' or 'date' or 'labelByXValue',
  valueFormatterPattern: string,
  valueFormatterLabels: {
    x: number, // required
    label: string // required
  },
  axisDependency: string,
}
```

```
type barLineScatterCandleBubble { highlightColor: number }
```

```
type lineScatterCandleRadar {
  drawVerticalHighlightIndicator: bool,
  drawHorizontalHighlightIndicator: bool,
  highlightLineWidth: number
}
```

```
type lineRadar 	{
  fillGradient: {
    colors: [number],
    // iOS
    positions: [numbers],
    angle: number,
    //Android
    orientation: 'TOP_BOTTOM' | 'TR_BL' | 'RIGHT_LEFT' | 'BR_TL' | 'BOTTOM_TOP' | 'BL_TR' | 'LEFT_RIGHT' | 'TL_BR',
  },
  fillColor: number,
  fillAlpha: number,
  drawFilled: function
}
```

# DataTypes

```
type lineData {
  dataSets: [
    {
      values: [
        number or
        {
          x: number,
          y: number,
          marker: string
        }
      ],
      label: string, // required
      config: {
        ...ConfigTypes.common,
        ...ConfigTypes.barLineScatterCandleBubble,
        ...ConfigTypes.lineScatterCandleRadar,
        ...ConfigTypes.lineRadar,
        circleRadius: number,
        drawCircles: bool,
        mode: bool,
        lineWidth: number, // min: 0, max: 10
        drawCubicIntensity: number,
        circleColor: number,
        circleColors: [number],
        circleHoleColor: number,
        drawCircleHole: bool,
        dashedLine: {
          lineLength: number, // required
          spaceLength: number, // required
          phase: number
        },
        fillFormatter: {
            min: number // required
        }
      }
    }
  ]
}
```

```
type barData {
  dataSets: [
    {
      values: [
        {
          x: number,
          y: number or [number],
          marker: string or [string]
        }
        or number or [number]
      ],
      label: string, // required
      config: {
        ...ConfigTypes.common,
        ...ConfigTypes.barLineScatterCandleBubble,
        barShadowColor: number,
        highlightAlpha: number,  // using android format (0-255), not ios format(0-1), the conversion is x/255
        stackLabels: [string]
      }
    }
  ],
  config: {
    barWidth: number,
    group: {
      fromX: number, // required
      groupSpace: number, // required
      barSpace: number // required
    }
  }
}
```

```
type bubbleData {
  dataSets: [
    {
      values: [
        {
          x: PropTypes.number,
          y: PropTypes.number, // required
          size: PropTypes.number, // required
          marker: PropTypes.string,
        }
      ],
      label: string, // required
      config: {
        ...ConfigTypes.common,
        ...ConfigTypes.barLineScatterCandleBubble
      }
    }
  ]
}
```

```
type candleData {
  dataSets: [
    {
      values: [
        {
          x: number,
          shadowH: number, // required
          shadowL: number, // required
          open: number, // required
          close: number, // required
          marker: string,
        }
      ],
      label: string, // required
      config: {
        ...ConfigTypes.common,
        ...ConfigTypes.barLineScatterCandleBubble,
        ...ConfigTypes.lineScatterCandleRadar,

        barSpace: number,
        shadowWidth: number,
        shadowColor: number,
        shadowColorSameAsCandle: bool,
        neutralColor: number,
        decreasingColor: number,
        decreasingPaintStyle: string,
        increasingColor: number,
        increasingPaintStyle: string
      }
    }
  ]
}
```

```
type pieData {
  dataSets: [
    {
      values: [
        {
          value: number, // required
          label: string
        }
        or number
      ],
      label: string, // required
      config: {
        ...ConfigTypes.common,

        sliceSpace: number,
        selectionShift: number,
        xValuePosition: string, // INSIDE_SLICE,OUTSIDE_SLICE
        yValuePosition: string // INSIDE_SLICE,OUTSIDE_SLICE
      }
    }
  ]
}
```

```
type radarData {
  dataSets: [
    {
      values: [
        {
          value: number // required
        }
        or number
      ],
      label: string, // required
      config: {
        ...ConfigTypes.common,
        ...ConfigTypes.lineScatterCandleRadar,
        ...ConfigTypes.lineRadar
      }
    }
  ],
  labels: [string]
}
```

```
type scatterData {
  dataSets: [
    {
      values: [
        {
          x: number,
          y: number, // required
          marker: string,
        }
        or number
      ],
      label: string, // required
      config: {
        ...ConfigTypes.common,
        ...ConfigTypes.barLineScatterCandleBubble,
        ...ConfigTypes.lineScatterCandleRadar,

        scatterShapeSize: number,
        scatterShape: 'SQUARE' or 'CIRCLE' or 'TRIANGLE' or 'CROSS' or 'X'
        scatterShapeHoleColor: number,
        scatterShapeHoleRadius: number
      }
    }
  ],
  labels: [string]
}
```

```
type combinedData {
  lineData: lineData,
  barData: barData,
  scatterData: scatterData,
  candleData: candleData,
  bubbleData: bubbleData
}
```
