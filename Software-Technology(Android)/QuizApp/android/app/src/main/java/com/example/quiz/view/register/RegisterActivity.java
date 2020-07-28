package com.example.quiz.view.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.R;
import com.example.quiz.memorydao.PlayerDAOMemory;
import com.example.quiz.view.home.HomeActivity;
import com.example.quiz.view.login.LoginActivity;

public class RegisterActivity  extends AppCompatActivity implements RegisterView{


    private EditText username;
    private EditText password;
    private EditText email;
    private EditText firstName;
    private EditText lastName;
    private RadioGroup categoryGroup;
    private RadioButton favouriteCategory;
    private RegisterPresenter presenter;
    private RadioGroup genderGroup;
    private RadioButton gender;

    /**
     * Δημιουργεί το layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        presenter = new RegisterPresenter(this, new PlayerDAOMemory());
    }

    //events

    /**
     * Χειρίζεται το κλικ στο κουμπί εγγραφής
     * @param view
     */
    public void onClickRegister(View view){
        presenter.register();
    }

    /**
     * Χειρίζεται το κλικ στο κουμπί εισόδου
     * @param view
     */
    public void onClickStartLoginActivity(View view){
        presenter.startLoginActivity();
    }

    /**
     * Επιστρέφει το όνομα χρήστη.
     * @return Το όνομα χρήστη
     */
    @Override
    public String getUsername() {

        username = findViewById(R.id.register_username);
        return username.getText().toString();

    }

    /**
     * Επιστρέφει τον κωδικό πρόσβασης.
     * @return Τον κωδικό πρόσβασης
     */
    @Override
    public String getPassword() {
        password = findViewById(R.id.register_password);
        return password.getText().toString();
    }

    /**
     * Επιστρέφει την διεύθυνση ηλεκτρονικού ταχυδρομείου.
     * @return Την διεύθυνση ηλεκτρονικού ταχυδρομείου
     */
    @Override
    public String getEmail() {
        email = findViewById(R.id.register_email);
        return email.getText().toString();
    }

    /**
     * Επιστρέφει το πρώτο όνομα του χρήστη.
     * @return Το πρώτο όνομα του χρήστη
     */
    @Override
    public String getFirstName() {
        firstName = findViewById(R.id.register_firstName);
        return firstName.getText().toString();
    }

    /**
     * Επιστρέφει το επώνυμο του χρήστη.
     * @return Το επώνυμο του χρήστη
     */
    @Override
    public String getLastName() {
        lastName = findViewById(R.id.register_lastName);
        return lastName.getText().toString();
    }

    /**
     * Επιστρέφει την αγαπημένη κατηγορία ερωτήσεων του χρήστη.
     * @return Την αγαπημένη κατηγορία ερωτήσεων του χρήστη
     */
    @Override
    public String getFavouriteCategory() {
        categoryGroup = findViewById(R.id.register_radioGroup);
        int selectedId = categoryGroup.getCheckedRadioButtonId();
        favouriteCategory = findViewById(selectedId);
        if(favouriteCategory == null)
            return "Sports";
        return favouriteCategory.getText().toString();
    }

    /**
     * Επιστρέφει το γένος του χρήστη.
     * @return Το γένος του χρήστη
     */
    @Override
    public String getGender() {
        genderGroup = findViewById(R.id.register_genderGroup);
        int selectedId = genderGroup.getCheckedRadioButtonId();
        gender = findViewById(selectedId);

        if(gender == null)
            return "";

        return gender.getText().toString();
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
     * Μεταφέρει τον χρήστη στο activity LoginActivity
     * όταν γίνει click πάνω στο κουμπί Είσοδος.
     */
    @Override
    public void startLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Μεταφέρει τον χρήστη στο activity HomeActivity
     * όταν γίνει click πάνω στο κουμπί ΕΓΓΡΑΦΗ.
     */
    @Override
    public void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("USERNAME", getUsername());
        startActivity(intent);
        //finish();
    }
}
