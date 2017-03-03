import React from 'react';
import {
  AppRegistry,
  BackAndroid,
  Navigator,
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

AppRegistry.registerComponent('Example', () => Example);
