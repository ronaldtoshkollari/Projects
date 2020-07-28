package com.example.quiz.view.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.R;
import com.example.quiz.view.challenges.ChallengesActivity;
import com.example.quiz.view.create_quiz.CreateQuizChallengeActivity;

public class HomeActivity extends AppCompatActivity implements HomeView{

    private TextView userName;
    private HomePresenter presenter;

    /**
     * Δημιουργεί το layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUsername();
        presenter = new HomePresenter(this);
    }

    //events

    /**
     * Χειρίζεται το κλικ στο κουμπί δημιουργίας πρόκλησης
     * @param view
     */
    public void onClickCreateQuiz(View view){
        presenter.createQuiz();
    }

    /**
     * Χειρίζεται το κλικ στο κουμπί απάντηση πρόκλησης
     * @param view
     */
    public void onClickReplyChallenge(View view){
        presenter.replyChallenge();
    }

    /**
     * Χειρίζεται το κλικ στο κουμπί προβολή κατάταξης
     * @param view
     */
    public void onClickShowLeaderboard(View view){
        presenter.showLeaderboard();
    }

    /**
     * Επιστρέφει το όνομα χρήστη.
     * @return Το όνομα χρήστη
     */
    @Override
    public String getUsername() {
        userName = findViewById(R.id.playerUsername);
        return userName.getText().toString();
    }

    /**
     * Θέτει το όνομα χρήστη.
     */
    @Override
    public void setUsername() {
        userName = findViewById(R.id.playerUsername);
        Intent intent = getIntent();
        String text = intent.getStringExtra("USERNAME");
        userName.setText(text);
    }

    /**
     * Μεταφέρει τον χρήστη στο activity CreateQuizActivity
     * όταν γίνει click πάνω στο κουμπί ΔΗΜΙΟΥΡΓΙΑ QUIZ.
     */
    @Override
    public void startCreateQuizActivity() {
        Intent intent = new Intent(this, CreateQuizChallengeActivity.class);
        intent.putExtra("USERNAME", getUsername());
        startActivity(intent);
    }

    /**
     * Μεταφέρει τον χρήστη στο activity ChallengesActivity
     * όταν γίνει click πάνω στο κουμπί ΑΠΑΝΤΗΣΗ ΠΡΟΚΛΗΣΗΣ.
     */
    @Override
    public void startChallengesActivity() {
        Intent intent = new Intent(this, ChallengesActivity.class);
        intent.putExtra("USERNAME", getUsername());
        startActivity(intent);
    }

    /**
     * Μεταφέρει τον χρήστη στο activity ShowRankActivity
     * όταν γίνει click πάνω στο κουμπί ΚΑΤΑΤΑΞΕΙΣ.
     */
    @Override
    public void startShowRankActivity() {
        //TODO add rank activity
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param message Το περιεχόμενο που θα εμφανιστεί
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
