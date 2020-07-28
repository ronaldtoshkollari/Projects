package com.example.quiz.view.login;

import android.widget.TextView;

public interface LoginView {

    /**
     * Επιστρέφει το όνομα χρήστη.
     * @return Το όνομα χρήστη
     */
    String getUsername();

    /**
     * Επιστρέφει τον κωδικό πρόσβασης.
     * @return Τον κωδικό πρόσβασης
     */
    String getPassword();

    /**
     * Επιστρέφει ένα μήνυμα σφάλαμτος για το πεδίο όνομα χρήστη.
     * @return Μήνυμα σφάλαμτος για το πεδίο όνομα χρήστη
     */
    String getUsernameRequiredError();

    /**
     * Επιστρέφει ένα μήνυμα σφάλαμτος για το πεδίο κωδικό πρόσβασης.
     * @return Μήνυμα σφάλαμτος για το πεδίο κωδικό πρόσβασης
     */
    String getPasswordRequiredError();

    /**
     * Επιστρέφει ένα μήνυμα σφάλαμτος για το πεδίο όνομα χρήστη.
     * @param errorMsg Μήνυμα σφάλαμτος για το πεδίο όνομα χρήστη
     */
    void setUsernameRequiredError(String errorMsg);

    /**
     * Επιστρέφει ένα μήνυμα σφάλαμτος για το πεδίο κωδικό πρόσβασης.
     * @param errorMsg Μήνυμα σφάλαμτος για το πεδίο κωδικό πρόσβασης
     */
    void setPasswordRequiredError(String errorMsg);

    /**
     * Θέτει το μήνυμα σφάλματος για το πεδίο όνομα χρήστη ορατό
     */
    void setUsernameRequiredTextViewVisible();

    /**
     * Θέτει το μήνυμα σφάλματος για το πεδίο κωδικός πρόσβασης ορατό.
     */
    void setPasswordRequiredTextViewVisible();

    /**
     * Θέτει το μήνυμα σφάλματος για το πεδίο όνομα χρήστη μη ορατό
     */
    void setUsernameRequiredTextViewInvisible();

    /**
     * Θέτει το μήνυμα σφάλματος για το πεδίο κωδικός πρόσβασης μη ορατό.
     */
    void setPasswordRequiredTextViewInvisible();

    /**
     * Εμφανίζει ένα Toast.
     * @param message Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String message);

    /**
     * Μεταφέρει τον χρήστη στο activity RegisterActivity
     * όταν γίνει click πάνω στο κείμενο Εγγραφή.
     */
    void startRegisterActivity();

    /**
     * Μεταφέρει τον χρήστη στο activity HomeActivity
     * όταν γίνει click πάνω στο κουμπί ΕΙΣΟΔΟΣ.
     */
    void startHomeActivity();

}
