package com.example.quiz.ViewModels.view.login;

import android.widget.TextView;

import com.example.quiz.view.login.LoginView;

public class LoginViewStub implements LoginView {

    private String username;
    private String password;
    private String toastMessage;
    private String usernameError;
    private String passwordError;
    private String errorMsg;
    private boolean usernameTextViewVisabilty;
    private boolean passwordTextViewVisabilty;
    private int startRegisterActivityClick;
    private int startHomeActivityClick;

    /**
     * Επιστέφει το όνομα χρήστη
     * @return Το όνομα χρήστη
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Επιστέφει τον κωδικό πρόσβασης του χρήστη
     * @return Τον κωδικό πρόσβασης του χρήστη
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Επιστέφει ένα μήνυμα σφάλματος για το πεδίο όνομα χρήστη
     * @return Ένα μήνυμα σφάλματος για το πεδίο όνομα χρήστη
     */
    @Override
    public String getUsernameRequiredError() {
        return errorMsg;
    }

    /**
     * Επιστέφει ένα μήνυμα σφάλματος για το πεδίο κωδικός πρόσβασης
     * @return Ένα μήνυμα σφάλματος για το πεδίο κωδικός πρόσβασης
     */
    @Override
    public String getPasswordRequiredError() {
        return errorMsg;
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param message Το περιεχόμενο που θα εμφανιστεί
     */
    @Override
    public void showToast(String message) {
        this.toastMessage = message;
    }

    /**
     * Αυξάνει τα click για το activity RegisterActivity κατά ένα
     */
    @Override
    public void startRegisterActivity() {
        startRegisterActivityClick++;
    }

    /**
     * Επιστρέφει το πλήθος των clicks για το activity RegisterActivity
     */
    public int getStartRegisterActivityClick() {
        return startRegisterActivityClick;
    }

    /**
     * Αυξάνει τα click για το activity HomeActivity κατά ένα
     */
    @Override
    public void startHomeActivity() {
        startHomeActivityClick++;
    }

    /**
     * Επιστρέφει το πλήθος των clicks για το activity HomeActivity
     */
    public int getStartHomeActivityClick() {
        return startHomeActivityClick;
    }

    /**
     * Θέτει το όνομα χρήστη
     * @param username Το όνομα χρήστη
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Θέτει τον κωδικό πρόσβασης
     * @param password Τον κωδικό πρόσβασης
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Θέτει ένα μήνυμα σφάλματος για το πεδίο όνομα χρήστη
     * @param usernameError Ένα μήνυμα σφάλματος για το πεδίο όνομα χρήστη
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Θέτει ένα μήνυμα σφάλματος για το πεδίο κωδικός πρόσβασης
     * @param passwordError Ένα μήνυμα σφάλματος για το πεδίο κωδικός πρόσβασης
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * Επιστέφει ένα μήνυμα σφάλματος για το πεδίο όνομα χρήστη
     * @return Ένα μήνυμα σφάλματος για το πεδίο όνομα χρήστη
     */
    public String getUsernameErrorMsg(){
        return usernameError;
    }

    public String getPasswordErrorMsg(){
        return passwordError;
    }

    public String getToastMessage() {
        return toastMessage;
    }

    @Override
    public void setUsernameRequiredError(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public void setPasswordRequiredError(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public void setUsernameRequiredTextViewVisible() {
        this.usernameTextViewVisabilty = true;
    }

    @Override
    public void setPasswordRequiredTextViewVisible() {
        this.passwordTextViewVisabilty = true;
    }

    @Override
    public void setUsernameRequiredTextViewInvisible() {
        this.usernameTextViewVisabilty = false;
    }

    @Override
    public void setPasswordRequiredTextViewInvisible() {
        this.passwordTextViewVisabilty = true;
    }

    public boolean isUsernameTextViewVisabilty() {
        return usernameTextViewVisabilty;
    }

    public boolean isPasswordTextViewVisabilty() {
        return passwordTextViewVisabilty;
    }


}
