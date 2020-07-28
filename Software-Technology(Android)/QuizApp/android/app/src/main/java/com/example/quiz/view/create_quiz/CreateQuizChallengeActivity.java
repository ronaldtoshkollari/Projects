package com.example.quiz.view.create_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.R;
import com.example.quiz.memorydao.PlayerDAOMemory;
import com.example.quiz.memorydao.QuizChallengeDAOMemory;
import com.example.quiz.memorydao.QuizDAOMemory;
import com.example.quiz.view.challenges.ChallengesActivity;

public class CreateQuizChallengeActivity extends AppCompatActivity implements CreateQuizChallengeView {

    private CreateQuizChallengePresenter presenter;

    private RadioGroup difficultyLvlGroup;
    private RadioGroup categoryGroup;
    private RadioButton checkedBtn;
    private TextView opponentUsername;
    private TextView myUserName;

    /**
     * Δημιουργεί το layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        setUsername();

        presenter = new CreateQuizChallengePresenter(this, new QuizChallengeDAOMemory(), new QuizDAOMemory(), new PlayerDAOMemory());
    }

    /**
     * Αρχικοποιεί την πρόκληση του quiz και μεταφέρει τον χρήστη
     * στο activity ChallengesActivity όταν γίνει click πάνω στο κουμπι ΔΗΜΙΟΥΡΓΙΑ ΠΡΟΚΛΗΣΗΣ.
     * @param view
     */
    public void onCreateQuizChallengeClick(View view) {
        if (presenter.initializeQuizChallenge()) {
            presenter.startChallenges();
        }
    }

    /**
     * Επιστρέφει το όνομα χρήστη του αντιπάλου.
     * @return Το όνομα χρήστη του αντιπάλου
     */
    @Override
    public String getOpponent() {
        opponentUsername = findViewById(R.id.opponent);
        return opponentUsername.getText().toString();
    }

    /**
     * Επιστρέφει το επίπεδο δυσκολίας των ερωτήσεων του quiz.
     * @return Το επίπεδο δυσκολίας των ερωτήσεων του quiz
     */
    @Override
    public String getDifficultyLevel() {
        difficultyLvlGroup = findViewById(R.id.radioGroup);
        int selectedId = difficultyLvlGroup.getCheckedRadioButtonId();
        checkedBtn = (RadioButton) findViewById(selectedId);
        return checkedBtn.getText().toString();
    }

    /**
     * Επιστρέφει την κατηγορία των ερωτήσεων του quiz.
     * @return Την κατηγορία των ερωτήσεων του quiz
     */
    @Override
    public String getCategory() {
        categoryGroup = findViewById(R.id.categoryGroup);
        int selectedId = categoryGroup.getCheckedRadioButtonId();
        checkedBtn = (RadioButton) findViewById(selectedId);
        return checkedBtn.getText().toString();
    }

    /**
     * Θέτει το όνομα χρήστη του αντιπάλου
     * @param opponent Το όνομα χρήστη του αντιπάλου
     */
    @Override
    public void setOpponent(String opponent) {
        opponentUsername = findViewById(R.id.opponent);
        opponentUsername.setText(opponent);
    }

    /**
     * Θέτει το επίπεδο δυσκολίας των ερωτήσεων του quiz.
     * @param difficultyLevel Το επίπεδο δυσκολίας των ερωτήσεων του quiz
     */
    @Override
    public void setDifficultyLevel(String difficultyLevel) {
        RadioButton easyBtn = findViewById(R.id.easyRadioBtn);
        RadioButton mediumBtn = findViewById(R.id.mediumRadioBtn);
        RadioButton hardBtn = findViewById(R.id.hardRadioBtn);

        if(easyBtn.getText().toString().equalsIgnoreCase(difficultyLevel))
            easyBtn.setChecked(true);

        if(mediumBtn.getText().toString().equalsIgnoreCase(difficultyLevel))
            mediumBtn.setChecked(true);

        if(hardBtn.getText().toString().equalsIgnoreCase(difficultyLevel))
            hardBtn.setChecked(true);
    }

    /**
     * Θέτει την κατηγορία των ερωτήσεων του quiz.
     * @param category Η κατηγορία των ερωτήσεων του quiz
     */
    @Override
    public void setCategory(String category) {
        RadioButton sportsBtn = findViewById(R.id.sportsRadioBtn);
        RadioButton politicsBtn = findViewById(R.id.politicsRadioBtn);
        RadioButton geographyBtn = findViewById(R.id.geographyRadioBtn);
        RadioButton historyBtn = findViewById(R.id.historyRadioBtn);

        if(sportsBtn.getText().toString().equalsIgnoreCase(category))
            sportsBtn.setChecked(true);

        if(politicsBtn.getText().toString().equalsIgnoreCase(category))
            politicsBtn.setChecked(true);

        if(geographyBtn.getText().toString().equalsIgnoreCase(category))
            geographyBtn.setChecked(true);

        if(historyBtn.getText().toString().equalsIgnoreCase(category))
            historyBtn.setChecked(true);
    }

    /**
     * Επιστρέφει το όνομα χρήστη.
     * @return Το όνομα χρήστη
     */
    @Override
    public String getUsername() {
        myUserName = findViewById(R.id.playerUsername);
        return myUserName.getText().toString();
    }

    /**
     * Μεταφέρει τον χρήστη στο activity ChallengesActivity
     * όταν γίνει click πάνω στο κουμπι ΔΗΜΙΟΥΡΓΙΑ ΠΡΟΚΛΗΣΗΣ.
     */
    @Override
    public void startChallengesActivity() {
        Intent intent = new Intent(this, ChallengesActivity.class);
        intent.putExtra("USERNAME", getUsername());
        startActivity(intent);
        finish();
    }

    /**
     * Θέτει το όνομα χρήστη.
     */
    @Override
    public void setUsername() {
        myUserName = findViewById(R.id.playerUsername);
        Intent intent = getIntent();
        String text = intent.getStringExtra("USERNAME");
        myUserName.setText(text);
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    @Override
    public void showToast(String value) {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
}
