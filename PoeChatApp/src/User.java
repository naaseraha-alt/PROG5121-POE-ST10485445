public class User {
    private String username;
    private String password;
    private String cellNumber;

    public User(String username, String password, String cellNumber) {
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
    }

    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
    }

    public boolean checkCellPhoneNumber() {
        return cellNumber.matches("^\\+27\\d{9}$");
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters long.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
		if (!checkCellPhoneNumber()) {
			return "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
		}
		return "User registered successfully.";
    }

    public boolean loginUser(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

	public String returnLoginStatus(String inputUsername, String inputPassword, String firstName, String surname) {
		if (loginUser(inputUsername, inputPassword)) {
			return "Welcome " + firstName + ", " + surname + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

}
