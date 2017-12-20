import React, {Component} from 'react';
import { UIManager, findNodeHandle} from 'react-native';


export default function ScaleEnhancer(Chart) {
  return class ScaleExtended extends Chart {
    fitScreen() {
      UIManager.dispatchViewManagerCommand(
        findNodeHandle(this.getNativeComponentRef()),
        UIManager[this.getNativeComponentName()].Commands.fitScreen,
        []
      );
    }

  }
}