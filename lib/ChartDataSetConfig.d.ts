/// <reference types="react" />
declare const chartDataSetConfig: {
    common: {
        colors: React.Requireable<any>;
        highlightEnabled: React.Requireable<any>;
        drawValues: React.Requireable<any>;
        valueTextSize: React.Requireable<any>;
        valueTextColor: React.Requireable<any>;
        visible: React.Requireable<any>;
        valueFormatter: React.Requireable<any>;
        axisDependency: React.Requireable<any>;
    };
    barLineScatterCandleBubble: {
        highlightColor: React.Requireable<any>;
    };
    lineScatterCandleRadar: {
        drawVerticalHighlightIndicator: React.Requireable<any>;
        drawHorizontalHighlightIndicator: React.Requireable<any>;
        highlightLineWidth: React.Requireable<any>;
    };
    lineRadar: {
        fillColor: React.Requireable<any>;
        fillAlpha: React.Requireable<any>;
        drawFilled: React.Requireable<any>;
        lineWidth: (props: any, propName: any, componentName: any) => Error | undefined;
    };
};
export default chartDataSetConfig;
