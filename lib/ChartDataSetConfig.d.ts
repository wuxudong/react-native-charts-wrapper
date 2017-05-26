import * as PropTypes from 'prop-types';
declare const chartDataSetConfig: {
    common: {
        colors: PropTypes.Requireable<any>;
        highlightEnabled: PropTypes.Requireable<any>;
        drawValues: PropTypes.Requireable<any>;
        valueTextSize: PropTypes.Requireable<any>;
        valueTextColor: PropTypes.Requireable<any>;
        visible: PropTypes.Requireable<any>;
        valueFormatter: PropTypes.Requireable<any>;
        axisDependency: PropTypes.Requireable<any>;
    };
    barLineScatterCandleBubble: {
        highlightColor: PropTypes.Requireable<any>;
    };
    lineScatterCandleRadar: {
        drawVerticalHighlightIndicator: PropTypes.Requireable<any>;
        drawHorizontalHighlightIndicator: PropTypes.Requireable<any>;
        highlightLineWidth: PropTypes.Requireable<any>;
    };
    lineRadar: {
        fillColor: PropTypes.Requireable<any>;
        fillAlpha: PropTypes.Requireable<any>;
        drawFilled: PropTypes.Requireable<any>;
        lineWidth: (props: any, propName: string, componentName: string) => Error | undefined;
    };
};
export default chartDataSetConfig;
