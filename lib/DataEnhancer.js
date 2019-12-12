import React, {Component} from 'react';
import { UIManager, findNodeHandle} from 'react-native';


export default function DataEnhancer(Chart) {
  return class DataExtended extends Chart {
    addDataPoints(data) {
      UIManager.dispatchViewManagerCommand(
        findNodeHandle(this.getNativeComponentRef()),
        UIManager.getViewManagerConfig(this.getNativeComponentName()).Commands.addDataPoints,
        [data]
      );
    }
  }
}