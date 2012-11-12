package jstats.estimation;

import org.jblas.DoubleMatrix;
import org.jblas.Solve;

import jstats.dataframe.DataFrame;

import java.util.Arrays;

public class Ols {

    public static Object estimate(DataFrame df) {
        
        DoubleMatrix data = df.getData();

        data = concatConsts(data);

        int[] yIndex = new int[]{0};
        int[] xIndices = new int[data.columns-1];
        for (int i = 1; i < data.columns; i++) {
            xIndices[i-1] = i;
        }

        DoubleMatrix yData = data.getColumns(yIndex);
        DoubleMatrix xData = data.getColumns(xIndices);

        DoubleMatrix xx = xData.transpose().mmul(xData);
        DoubleMatrix xy = xData.transpose().mmul(yData);

        DoubleMatrix b = Solve.solve(xx, xy);

        return b;
    }

    private static DoubleMatrix concatConsts(DoubleMatrix data) {
        DoubleMatrix consts = DoubleMatrix.ones(data.rows);
        return DoubleMatrix.concatHorizontally(data, consts);
    }

}