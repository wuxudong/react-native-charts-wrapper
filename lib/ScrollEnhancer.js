import React, {Component} from 'react';
import { UIManager, findNodeHandle} from 'react-native';


export default function ScrollEnhancer(Chart) {
  return class ScrollExtended extends Chart {
    setDataAndLockIndex(data) {
      UIManager.dispatchViewManagerCommand(
        findNodeHandle(this.getNativeComponentRef()),
        UIManager[this.getNativeComponentName()].Commands.setDataAndLockIndex,
        [data]
      );
    }
  }
}