package me.csed2.moneymanager.plaid;

public class Plaid {

    public static String CLIENT_ID = "5e70f54621b2b0012c0e233";
    public static String PUBLIC_KEY = "579a52d42c4886ab86c1cf0d3fa267";
    public static String SECRET_SANDBOX = "a22d1dfb79638faadf2a17a7007491";

    public Plaid() {
        plaidTest();
    }

    private void plaidTest() {

    }

    public static void main(String[] args) {
        new Plaid();
    }
}
