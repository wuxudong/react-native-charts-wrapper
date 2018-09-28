import React from "react";
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  processColor,
  LayoutAnimation
} from "react-native";
import update from "immutability-helper";

import { LineChart } from "react-native-charts-wrapper";

const greenBlue = "rgb(26, 182, 151)";
const petrel = "rgb(59, 145, 153)";

class LineChartScreen extends React.Component {
  constructor() {
    super();

    this.state = {};
  }

  handleSelect(event) {
    let entry = event.nativeEvent;
    if (entry == null) {
      this.setState({ ...this.state, selectedEntry: null });
    } else {
      this.setState({ ...this.state, selectedEntry: JSON.stringify(entry) });
    }

    console.log(event.nativeEvent);
  }

  render() {
    return (
      <View style={{ flex: 1 }}>
        <View style={{ height: 80 }}>
          <Text> selected entry</Text>
          <Text> {this.state.selectedEntry}</Text>
        </View>

        <View style={styles.container}>
          <LineChart
            style={styles.chart}
            data={{
              dataSets: [
                {
                  values: [
                    {
                      y: 65,
                      x: 0,
                      marker: "65 kg"
                    },
                    {
                      y: 77,
                      x: 1,
                      marker: "77 kg"
                    },
                    {
                      y: 76,
                      x: 2,
                      marker: "76 kg"
                    },
                    {
                      y: 74,
                      x: 3,
                      marker: "74 kg"
                    },
                    {
                      y: 76,
                      x: 4,
                      marker: "76 kg"
                    },
                    {
                      y: 65,
                      x: 5,
                      marker: "Today: 65 kg"
                    }
                  ],
                  label: "",
                  config: {
                    mode: "CUBIC_BEZIER",
                    drawValues: false,
                    lineWidth: 2,
                    drawCircles: true,
                    circleColor: processColor(petrel),
                    drawCircleHole: false,
                    circleRadius: 5,
                    highlightColor: processColor("transparent"),
                    color: processColor(petrel),
                    drawFilled: true,
                    fillGradient: {
                      colors: [processColor(petrel), processColor(greenBlue)],
                      positions: [0, 0.5],
                      angle: 90,
                      orientation: "TOP_BOTTOM"
                    },
                    fillAlpha: 1000,
                    valueTextSize: 15
                  }
                },

                {
                  values: [
                    {
                      y: 35,
                      x: 0,
                      marker: "35 kg"
                    },
                    {
                      y: 47,
                      x: 1,
                      marker: "47 kg"
                    },
                    {
                      y: 46,
                      x: 2,
                      marker: "46 kg"
                    },
                    {
                      y: 44,
                      x: 3,
                      marker: "44 kg"
                    },
                    {
                      y: 46,
                      x: 4,
                      marker: "46 kg"
                    },
                    {
                      y: 35,
                      x: 5,
                      marker: "Today: 35 kg"
                    }
                  ],
                  label: "",
                  config: {
                    mode: "CUBIC_BEZIER",
                    drawValues: false,
                    lineWidth: 2,
                    drawCircles: true,
                    circleColor: processColor(petrel),
                    drawCircleHole: false,
                    circleRadius: 5,
                    highlightColor: processColor("transparent"),
                    color: processColor(petrel),
                    drawFilled: true,
                    fillGradient: {
                      colors: [processColor('red'), processColor('yellow')],
                      positions: [0, 0.5],
                      angle: 90,
                      orientation: "TOP_BOTTOM"
                    },
                    fillAlpha: 1000,
                    valueTextSize: 15
                  }
                }
              ]
            }}
            chartDescription={{ text: "" }}
            legend={{
              enabled: false
            }}
            marker={{
              enabled: true,
              markerColor: processColor("white"),
              textColor: processColor("black")
            }}
            xAxis={{
              enabled: true,
              granularity: 1,
              drawLabels: true,
              position: "BOTTOM",
              drawAxisLine: true,
              drawGridLines: false,
              fontFamily: "HelveticaNeue-Medium",
              fontWeight: "bold",
              textSize: 12,
              textColor: processColor("gray"),
              valueFormatter: ["M", "T", "W", "T", "F", "S"]
            }}
            yAxis={{
              left: {
                enabled: false
              },
              right: {
                enabled: false
              }
            }}
            autoScaleMinMaxEnabled={true}
            animation={{
              durationX: 0,
              durationY: 1500,
              easingY: "EaseInOutQuart"
            }}
            drawGridBackground={false}
            drawBorders={false}
            touchEnabled={true}
            dragEnabled={false}
            scaleEnabled={false}
            scaleXEnabled={false}
            scaleYEnabled={false}
            pinchZoom={false}
            doubleTapToZoomEnabled={false}
            dragDecelerationEnabled={true}
            dragDecelerationFrictionCoef={0.99}
            keepPositionOnRotation={false}
            onSelect={this.handleSelect.bind(this)}
            onChange={event => console.log(event.nativeEvent)}
          />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#F5FCFF",
    padding: 20
  },
  chart: {
    height: 250
  }
});

export default LineChartScreen;
