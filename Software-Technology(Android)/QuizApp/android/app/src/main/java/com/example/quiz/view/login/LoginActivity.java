package com.example.quiz.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.R;
import com.example.quiz.dao.PlayerDao;
import com.example.quiz.domain.Player;
import com.example.quiz.domain.QuizCategory;
import com.example.quiz.memorydao.PlayerDAOMemory;
import com.example.quiz.view.home.HomeActivity;
import com.example.quiz.view.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private LoginPresenter presenter;
    private EditText username;
    private EditText password;
    private TextView userNameRequired;
    private TextView passwordRequired;

    /**
     * Δημιουργεί το layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        PlayerDao playerDao = new PlayerDAOMemory();
        playerDao.save(new Player(1, "roni", "roni", "roni@gmail.com", "Ronaldo", "Toshkollari", "Male", new QuizCategory("Sports")));

        //presenter = new LoginPresenter(this, new PlayerDAOMemory());
        presenter = new LoginPresenter(this, playerDao);

    }

    /**
     * Χειρίζεται το κλικ στο κουμπί εισόδου
     * @param view
     */
    public void onClickLogIn(View view) {
        presenter.logIn();
    }

    /**
     * Χειρίζεται το κλικ στο κουμπί εγγραφής
     * @param view
     */
    public void onClickStartRegisterActivity(View view){
        presenter.startRegisterActivity();
    }

    /**
     * Επιστρέφει το όνομα χρήστη.
     * @return Το όνομα χρήστη
     */
    @Override
    public String getUsername() {
        username = findViewById(R.id.playerUsername);
        return username.getText().toString();
    }

    /**
     * Επιστρέφει τον κωδικό πρόσβασης.
     * @return Τον κωδικό πρόσβασης
     */
    @Override
    public String getPassword() {
        password = findViewById(R.id.password);
        return password.getText().toString();
    }

    /**
     * Επιστρέφει ένα μήνυμα σφάλαμτος για το πεδίο όνομα χρήστη.
     * @return Μήνυμα σφάλαμτος για το πεδίο όνομα χρήστη
     */
    @Override
    public String getUsernameRequiredError() {
        userNameRequired = findViewById(R.id.username_required_txt);
        return userNameRequired.getText().toString();
    }

    /**
     * Επιστρέφει ένα μήνυμα σφάλαμτος για το πεδίο κωδικό πρόσβασης.
     * @return Μήνυμα σφάλαμτος για το πεδίο κωδικό πρόσβασης
     */
    @Override
    public String getPasswordRequiredError() {
        passwordRequired = findViewById(R.id.password_required_txt);
        return passwordRequired.getText().toString();
    }

    /**
     * Επιστρέφει ένα μήνυμα σφάλαμτος για το πεδίο όνομα χρήστη.
     * @param errorMsg Μήνυμα σφάλαμτος για το πεδίο όνομα χρήστη
     */
    @Override
    public void setUsernameRequiredError(String errorMsg) {
        userNameRequired = findViewById(R.id.username_required_txt);
       userNameRequired.setText(errorMsg);
    }

    /**
     * Επιστρέφει ένα μήνυμα σφάλαμτος για το πεδίο κωδικό πρόσβασης.
     * @param errorMsg Μήνυμα σφάλαμτος για το πεδίο κωδικό πρόσβασης
     */
    @Override
    public void setPasswordRequiredError(String errorMsg) {
        passwordRequired = findViewById(R.id.password_required_txt);
        passwordRequired.setText(errorMsg);
    }

    /**
     * Θέτει το μήνυμα σφάλματος για το πεδίο όνομα χρήστη ορατό
     */
    @Override
    public void setUsernameRequiredTextViewVisible() {
        userNameRequired = findViewById(R.id.username_required_txt);
        userNameRequired.setVisibility(View.VISIBLE);
    }

    /**
     * Θέτει το μήνυμα σφάλματος για το πεδίο κωδικός πρόσβασης ορατό.
     */
    @Override
    public void setPasswordRequiredTextViewVisible() {
        passwordRequired = findViewById(R.id.password_required_txt);
        passwordRequired.setVisibility(View.VISIBLE);
    }

    /**
     * Θέτει το μήνυμα σφάλματος για το πεδίο όνομα χρήστη μη ορατό
     */
    @Override
    public void setUsernameRequiredTextViewInvisible() {
        userNameRequired = findViewById(R.id.username_required_txt);
        userNameRequired.setVisibility(View.INVISIBLE);
    }

    /**
     * Θέτει το μήνυμα σφάλματος για το πεδίο κωδικός πρόσβασης μη ορατό.
     */
    @Override
    public void setPasswordRequiredTextViewInvisible() {
        passwordRequired = findViewById(R.id.password_required_txt);
        passwordRequired.setVisibility(View.INVISIBLE);
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param message Το περιεχόμενο που θα εμφανιστεί
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Μεταφέρει τον χρήστη στο activity RegisterActivity
     * όταν γίνει click πάνω στο κείμενο Εγγραφή.
     */
    @Override
    public void startRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Μεταφέρει τον χρήστη στο activity HomeActivity
     * όταν γίνει click πάνω στο κουμπί ΕΙΣΟΔΟΣ.
     */
    @Override
    public void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("USERNAME", getUsername());
        startActivity(intent);
//        finish();
    }
}
