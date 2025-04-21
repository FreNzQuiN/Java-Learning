package Kuliah.Semester2;
import java.math.BigDecimal;
import java.util.Scanner;

public class Tugas4 {

    public static class Rational extends Number implements Comparable<Rational> {
        private long numerator = 0;
        private long denominator = 1;

        public Rational() {
            this(0, 1);
        }

        public Rational(long numerator, long denominator) {
            long gcd = gcd(numerator, denominator);
            this.numerator = ((denominator > 0) ? 1 : -1) * numerator / gcd;
            this.denominator = Math.abs(denominator) / gcd;
        }

        private static long gcd(long n, long d) {
            long n1 = Math.abs(n);
            long n2 = Math.abs(d);
            long gcd = 1;

            for (long k = 1; k <= n1 && k <= n2; k++) {
                if (n1 % k == 0 && n2 % k == 0)
                    gcd = k;
            }

            return gcd;
        }

        public long getNumerator() {
            return numerator;
        }

        public long getDenominator() {
            return denominator;
        }

        public Rational add(Rational secondRational) {
            long n = numerator * secondRational.getDenominator() +
                    denominator * secondRational.getNumerator();
            long d = denominator * secondRational.getDenominator();
            return new Rational(n, d);
        }

        public Rational subtract(Rational secondRational) {
            long n = numerator * secondRational.getDenominator() -
                    denominator * secondRational.getNumerator();
            long d = denominator * secondRational.getDenominator();
            return new Rational(n, d);
        }

        public Rational multiply(Rational secondRational) {
            long n = numerator * secondRational.getNumerator();
            long d = denominator * secondRational.getDenominator();
            return new Rational(n, d);
        }

        public Rational divide(Rational secondRational) {
            long n = numerator * secondRational.getDenominator();
            long d = denominator * secondRational.numerator;
            return new Rational(n, d);
        }

        @Override
        public String toString() {
            if (denominator == 1)
                return numerator + "";
            else
                return numerator + "/" + denominator;
        }

        @Override
        public boolean equals(Object other) {
            if ((this.subtract((Rational) (other))).getNumerator() == 0)
                return true;
            else
                return false;
        }

        @Override
        public int intValue() {
            return (int) doubleValue();
        }

        @Override
        public float floatValue() {
            return (float) doubleValue();
        }

        @Override
        public double doubleValue() {
            return numerator * 1.0 / denominator;
        }

        @Override
        public long longValue() {
            return (long) doubleValue();
        }

        @Override
        public int compareTo(Rational o) {
            if (this.subtract(o).getNumerator() > 0)
                return 1;
            else if (this.subtract(o).getNumerator() < 0)
                return -1;
            else
                return 0;
        }
    }

    public static class Fraction {
        private double number;

        public Fraction(double number) {
            this.number = number;
        }

        public double getValue() {
            return number;
        }

        public int getGCD(int a, int b) {
            return b == 0 ? a : getGCD(b, a % b);
        }

        public void getFraction() {
            String numStr = String.valueOf(number);
            int decimalDigits = numStr.length() - numStr.indexOf('.') - 1;
            int denominator = (int) Math.pow(10, decimalDigits);
            int numerator = (int) (number * denominator);
            int gcd = getGCD(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;

            System.out.println("The fraction number is " + numerator + "/" + denominator);
        }
    }

    public static void main(String[] args) {

        // System.out.println("====++  BAGIAN 2 - SOAL 1330 ++====\n");

        // Rational r1 = new Rational(-2, 6);
        // System.out.println(r1.getNumerator());
        // System.out.println(r1.getDenominator());
        // System.out.println(r1.intValue());
        // System.out.println(r1.doubleValue());

        // System.out.println("\n\n====++  BAGIAN 2 - SOAL 1331 ++====\n");

        // Rational r1 = new Rational(-2,6);
        // Object r2 = new Rational(1,45);
        // System.out.println(r2.compareTo(r1));
        // System.out.println("Wrong because r2 is Object");

        // System.out.println("\n\n====++  BAGIAN 2 - SOAL 1334 ++====\n");
        // Rational r1 = new Rational(1, 2);
        // Rational r2 = new Rational(1, -2);
        // System.out.println(r1.add(r2));

        System.out.println("\n\n====++  BAGIAN 3 - SOAL 1318 ++====\n");
        int n = 100;
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 1; i < n; i++) {
            // System.out.println(sum + " + " + i + "/" + (i + 1) + " = ");
            double x = new Rational(i, i + 1).doubleValue();
            sum = sum.add(BigDecimal.valueOf(x));
        }
        System.out.println(sum);

        System.out.println("\n\n====++ BAGIAN 3 - SOAL 1319 ++====");
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter a decimal number: ");
        String input = scan.next();
        double x = Double.parseDouble(input);
        Fraction fraction = new Fraction(x);
        fraction.getFraction();
        
        scan.close();
    }
}