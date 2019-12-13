import React, {Component} from 'react';
import { UIManager, findNodeHandle} from 'react-native';


export default function ConfigEnhancer(Chart) {
  return class ConfigExtended extends Chart {
    updateConfig(data) {
      UIManager.dispatchViewManagerCommand(
        findNodeHandle(this.getNativeComponentRef()),
        UIManager.getViewManagerConfig(this.getNativeComponentName()).Commands.updateConfig,
        [data]
      );
    }
  }
}