package example;

//import static cmath.UpdatedCalculator.eval;

import cmath.UpdatedCalculator;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args ) {
        System.out.println("\t\t\t\t" + UpdatedCalculator.eval("2.0 ^ (((2 * 4) * 2.0) / 4.0)", false));

        //System.out.println("\t\t\t\t" + UpdatedCalculator.eval("sin(15) * cos(30) + cos(15) * sin(30)", true));

    }
}