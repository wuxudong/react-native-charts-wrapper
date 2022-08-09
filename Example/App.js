import "react-native-gesture-handler";
import React, {Component} from "react";
import {AppRegistry} from "react-native";
import {NavigationContainer} from "@react-navigation/native";
import ChartsListScreen from "./app/ChartsListScreen";

const Example = () => (
  <NavigationContainer>
    <ChartsListScreen>
    </ChartsListScreen>
  </NavigationContainer>
)


export default Example;

AppRegistry.registerComponent('Example', () => Example);

