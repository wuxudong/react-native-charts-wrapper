import React, {Component} from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

import ChartsListScreen from './app/ChartsListScreen';

class Example extends React.Component {
  render() {
    return (

      <ChartsListScreen>
      </ChartsListScreen>
    );
  }
}

export default Example;

AppRegistry.registerComponent('Example', () => Example);
