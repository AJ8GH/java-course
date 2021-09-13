package section11.packages;

public class Password {
    public static void main(String[] args) {
        Password password = new Password(1234);

        int a = password.encryptDecrypt(9837453);
        System.out.println(a);

        int b = password.encryptDecrypt(a);
        System.out.println(b);
    }


    private static final int KEY = 78642543;
    private final int encryptedPassword;

    public Password(int password) {
        this.encryptedPassword = encryptDecrypt(password);
    }

    private int encryptDecrypt(int password) {
        return password ^ KEY;
    }

    public boolean authenticate(int password) {
        return encryptDecrypt(password) == encryptedPassword;
    }
}
