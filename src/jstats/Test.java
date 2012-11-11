package jstats;

import org.jblas.*;

import jstats.estimation.Ols;
import jstats.dataframe.DataFrame;

import java.io.File;

public class Test {

    public static void main(String[] args) {

        if (args.length > 0){
            try {
                Ols.estimate(new DataFrame(new File(args[0])));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("## Error : filepath required.");
        }
    }
}
