import static java.lang.Math.*; // библеотека Math имееет статические методы

public class LabOne {
    // 327630
    public static void main(String [] args) {
        short[] e = new short[8];
        double[] x = new double[12];
        double[][] f = new double[8][12];

        short val = 6; // значение ячейки массива
        for (int i = 0;i<8;i++) {
            e[i] = val; // Заполняем массив e значениями от 6 до 20 включительно
            val += 2;
        }

        for (int i = 0; i<12; i++) {
            x[i] = 17 * random() - 14; // Заполняем массив e значениями от -14 до 3
        }

        for (int i = 0; i<8; i++) {
            for (int j = 0; j<12; j++) {
                if (e[i] == 6) {
                    f[i][j] = pow(cos(exp(x[j])), 1/(3*(tan(4/x[j])+1)));
                } else if (e[i] == 8 || e[i] == 16 || e[i] == 18 || e[i] == 20) {
                    f[i][j] = cos(atan(pow((x[j]-5.5)/17, 2)));
                } else {
                    f[i][j] = pow(2*asin(exp(cbrt(-pow(cos(x[j]), 2)))), atan(0.2*exp(-abs(x[j]))));
                }
                System.out.print(String.format("%.4f ", f[i][j]));
            }
            System.out.println();
        }
    }
}