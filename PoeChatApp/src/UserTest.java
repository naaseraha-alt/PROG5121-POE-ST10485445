public class UserTest {
    @Test
    public void testValidUsername() {
        User user = new User("Yul_r", "Pass@123", "+27831234567");
        assertTrue(user.checkUserName());
    }

    private void assertTrue(boolean checkUserName) {

        throw new UnsupportedOperationException("Unimplemented method 'assertTrue'");
    }

    @Test
    public void testInvalidUsername() {
        User user = new User("Kyle!!!!!!!", "Pass@123", "+27831234567");
        assertFalse(user.checkUserName());
    }

    private void assertFalse(boolean checkUserName) {

        throw new UnsupportedOperationException("Unimplemented method 'assertFalse'");
    }

    @Test
    public void testValidPassword() {
        User user = new User("Yul_r", "Pass@123", "+27831234567");
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testInvalidPassword() {
        User user = new User("Yul_r", "Password", "+27831234567");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testValidCellNumber() {
        User user = new User("Yul_r", "Pass@123", "+27831234567");
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testLoginSuccess() {
        User user = new User("Yul_r", "Pass@123", "+27831234567");
        assertTrue(user.loginUser("Yul_r", "Pass@123"));
    }

    @Test
    public void testLoginFailure() {
        User user = new User("Yul_r", "Pass@123", "+27831234567");
        assertTrue(user.loginUser("Yul_r", "wrongPass"));
    }

}