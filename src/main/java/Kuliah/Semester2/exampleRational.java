package Kuliah.Semester2;

public class exampleRational extends Number implements Comparable<exampleRational> {
    // Data fields untuk pembilang dan penyebut
    private long numerator = 0;
    private long denominator = 1;

    /** Konstruktor rasional dengan properti default */
    public exampleRational() {
        this(0, 1);
    }

    /** Konstruktor rasional dengan pembilang dan penyebut yang ditentukan */
    public exampleRational(long numerator, long denominator) {
        long gcd = gcd(numerator, denominator);
        this.numerator = ((denominator > 0) ? 1 : -1) * numerator / gcd;
        this.denominator = Math.abs(denominator) / gcd;
    }

    /** Mencari FPB dari dua angka */
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

    /** Mengembalikan pembilang */
    public long getNumerator() {
        return numerator;
    }

    /** Mengembalikan penyebut */
    public long getDenominator() {
        return denominator;
    }

    /** Menambahkan bilangan rasional ke bilangan rasional ini */
    public exampleRational add(exampleRational secondRational) {
        long n = numerator * secondRational.getDenominator() +
                denominator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new exampleRational(n, d);
    }

    /** Mengurangi bilangan rasional dari bilangan rasional ini */
    public exampleRational subtract(exampleRational secondRational) {
        long n = numerator * secondRational.getDenominator() -
                denominator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new exampleRational(n, d);
    }

    /** Mengalikan bilangan rasional dengan bilangan rasional ini */
    public exampleRational multiply(exampleRational secondRational) {
        long n = numerator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new exampleRational(n, d);
    }

    /** Membagi bilangan rasional dengan bilangan rasional ini */
    public exampleRational divide(exampleRational secondRational) {
        long n = numerator * secondRational.getDenominator();
        long d = denominator * secondRational.numerator;
        return new exampleRational(n, d);
    }

    @Override
    public String toString() {
        if (denominator == 1)
            return numerator + "";
        else
            return numerator + "/" + denominator;
    }

    @Override // Mengganti metode equals di kelas Object
    public boolean equals(Object other) {
        if ((this.subtract((exampleRational) (other))).getNumerator() == 0)
            return true;
        else
            return false;
    }

    @Override // Mengimplementasikan metode abstrak intValue di Number
    public int intValue() {
        return (int) doubleValue();
    }

    @Override // Mengimplementasikan metode abstrak floatValue di Number
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override // Mengimplementasikan metode doubleValue di Number
    public double doubleValue() {
        return numerator * 1.0 / denominator;
    }

    @Override // Mengimplementasikan metode abstrak longValue di Number
    public long longValue() {
        return (long) doubleValue();
    }

    @Override // Mengimplementasikan metode compareTo di Comparable
    public int compareTo(exampleRational o) {
        if (this.subtract(o).getNumerator() > 0)
            return 1;
        else if (this.subtract(o).getNumerator() < 0)
            return -1;
        else
            return 0;
    }

    public static void main(String[] args) {
        Rational r1 = new Rational(-2, 6);
        System.out.println(r1.getNumerator());
        System.out.println(r1.getDenominator());
        System.out.println(r1.intValue());
        System.out.println(r1.doubleValue());
    }
}

class Rational extends exampleRational {
    public Rational() {
        super();
    }

    public Rational(long numerator, long denominator) {
        super(numerator, denominator);
    }
}