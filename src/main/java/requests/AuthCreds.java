package requests;

public class AuthCreds {
    private final String email;
    private final String password;

    public AuthCreds(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
