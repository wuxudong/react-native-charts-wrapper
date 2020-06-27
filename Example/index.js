import "react-native-gesture-handler";
import React, {Component} from "react";
import {AppRegistry} from "react-native";
import { enableScreens } from 'react-native-screens';
import ChartsListScreen from "./app/ChartsListScreen";

enableScreens();

const Example = () => (
  <ChartsListScreen>
  </ChartsListScreen>
)


export default Example;

AppRegistry.registerComponent('Example', () => Example);

