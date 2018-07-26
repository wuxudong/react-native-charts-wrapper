import React, {Component} from 'react';
import { UIManager, findNodeHandle} from 'react-native';


export default function HighlightEnhancer(Chart) {
  return class HighlightExtended extends Chart {
    highlights(config) {
      UIManager.dispatchViewManagerCommand(
        findNodeHandle(this.getNativeComponentRef()),
        UIManager[this.getNativeComponentName()].Commands.highlights,
        [config]
      );
    }
  }
}