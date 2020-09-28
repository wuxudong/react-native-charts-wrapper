import React from "react";
import { UIManager, findNodeHandle } from "react-native";

export default function LiveUpdateProvider(Chart) {
    return class LiveUpdateChart extends Chart {
        /**
         * @param entries Array of objects: [{ index: number, values: [ { x: number, y: number }, ... ]}, ...]
         * where index matches to which dataset index the values should be appended.
         * and values is the array of entries to be appended
         */
        addEntries(entries) {
            UIManager.dispatchViewManagerCommand(
                findNodeHandle(this.getNativeComponentRef()),
                UIManager.getViewManagerConfig(this.getNativeComponentName())
                    .Commands.addEntries,
                entries
            );
        }
    };
}
