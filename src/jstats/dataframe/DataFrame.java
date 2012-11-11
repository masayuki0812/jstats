package jstats.dataframe;

import org.jblas.DoubleMatrix;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class DataFrame {

    private DoubleMatrix data;

    public DataFrame(double[][] data) {
        this.data = new DoubleMatrix(data);
    }

    public DataFrame(File file) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(file));
        
        String line;
        double[] row = null;

        while ((line = bf.readLine()) != null) {
            String[] elements = line.split(",");
            if (row == null) {
                row = new double[elements.length];
                continue;
            }
            for (int i = 0; i < elements.length; i++ ) {
                row[i] = Double.parseDouble(elements[i]);
            }
            if (this.data == null) {
                this.data = new DoubleMatrix(1, row.length, row);
            }
            else {
                this.data = DoubleMatrix.concatVertically(this.data, new DoubleMatrix(1, row.length, row));
            }
        }
    }

    public DoubleMatrix getData() {
        return this.data;
    }
}