import React, {Component} from 'react';
import { UIManager, findNodeHandle} from 'react-native';


export default function BoundsEnhancer(Chart) {
  return class BoundsExtended extends Chart {
    setBounds(data) {
      UIManager.dispatchViewManagerCommand(
        findNodeHandle(this.getNativeComponentRef()),
        UIManager.getViewManagerConfig(this.getNativeComponentName()).Commands.setChartBounds,
        [data]
      );
    }
  }
}