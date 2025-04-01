class User {
    private String userId, username, password, email, phoneNumber, securityQuestion, securityAnswer;
    private int age;
    private boolean isActive;

    public User(String userId, String username, String password, String email) {
        this.userId = userId;
        setUsername(username);
        setPassword(password);
        setEmail(email);
        this.isActive = true;
    }

    // Getters (Password returns masked value)
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return "********"; }
    public String getEmail() { return email; }
    public boolean isActive() { return isActive; }
    public String getSecurityQuestion() { return securityQuestion; }

    // Setters with Validation
    public void setUsername(String username) {
        if (username.length() >= 4) this.username = username;
        else throw new IllegalArgumentException("Username must be at least 4 characters.");
    }

    public void setPassword(String password) {
        if (password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*\\d.*"))
            this.password = password;
        else throw new IllegalArgumentException("Password must be 8+ characters, contain uppercase & number.");
    }

    public void setEmail(String email) {
        if (email.matches(".+@.+\\..+")) this.email = email;
        else throw new IllegalArgumentException("Invalid email format.");
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("\\d{10}")) this.phoneNumber = phoneNumber;
        else throw new IllegalArgumentException("Phone number must be 10 digits.");
    }

    public void setAge(int age) {
        if (age >= 18 && age <= 120) this.age = age;
        else throw new IllegalArgumentException("Age must be between 18 and 120.");
    }

    public void setSecurityQuestion(String question, String answer) {
        if (question != null && answer.length() >= 2) {
            this.securityQuestion = question;
            this.securityAnswer = answer;
        } else throw new IllegalArgumentException("Invalid security question or answer.");
    }

    // Security Methods
    public boolean verifyPassword(String attempt) { return this.password.equals(attempt); }
    public boolean verifySecurityAnswer(String answer) { return this.securityAnswer.equals(answer); }

    public void displayUserInfo() {
        System.out.println("User: " + username + " | Email: " + email + " | Status: " + (isActive ? "Active" : "Inactive"));
    }
}

public class SecureUserSystem {
    public static void main(String[] args) {
        try {
            User user = new User("U1001", "johnsmith", "Password123", "john@example.com");
            user.setPhoneNumber("1234567890");
            user.setAge(30);
            user.setSecurityQuestion("Pet's Name?", "Fluffy");

            user.displayUserInfo();

            // Test validations
            System.out.println("Password Verified: " + user.verifyPassword("Password123"));
            System.out.println("Wrong Password: " + user.verifyPassword("wrongpass"));
            System.out.println("Security Answer Verified: " + user.verifySecurityAnswer("Fluffy"));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
