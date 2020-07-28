package com.example.quiz.view.dual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.R;
import com.example.quiz.memorydao.AnswerDAOMemory;
import com.example.quiz.memorydao.PlayerDAOMemory;
import com.example.quiz.memorydao.QuestionDAOMemory;
import com.example.quiz.memorydao.QuizChallengeDAOMemory;
import com.example.quiz.memorydao.QuizDAOMemory;
import com.example.quiz.memorydao.QuizParticipationDAOMemory;

public class DualActivity extends AppCompatActivity implements DualView {

    private TextView myUserName;
    private TextView question;
    private TextView clock;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private Button submit;
    private DualPresenter presenter;
    private int answerIndex;
    private int count;

    /**
     * Δημιουργεί το layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual);

        setUsername();

        answerIndex = 0;
        count = 0;

        presenter = new DualPresenter(this, new PlayerDAOMemory(), new QuestionDAOMemory(), new QuizChallengeDAOMemory(), new QuizDAOMemory(), new QuizParticipationDAOMemory(), new AnswerDAOMemory());
        presenter.initDual();
        presenter.getNextQuestion(count);

    }

    /**
     * Αποθηκευει την απάντηση ενός χρήστη
     * @param view
     */
    public void onSubmit(View view) {
        int time = Integer.parseInt(getClock());
        presenter.addTotalTime(time);
        presenter.cancelTimer();
        presenter.submitQuestion(getCount(), answerIndex);

        if (getCount() == 20) {
            Button btn = findViewById(R.id.submitBtn);
            btn.setEnabled(false);
            presenter.calculateScore();
            presenter.getWinner();
//            finish();
        } else {
            addCount();
            presenter.getNextQuestion(getCount());
        }

    }

    /**
     * Ενημερώνει την διεπαφή για τον κουμπί που πάτησε
     * @param view
     */
    public void onAnswerClick(View view) {

        if (view.getId() == R.id.answer1Btn) {

            answer1 = findViewById(R.id.answer1Btn);
            answer1.setBackgroundColor(Color.GREEN);
            answer2 = findViewById(R.id.answer2Btn);
            answer2.setBackgroundColor(Color.GRAY);
            answer3 = findViewById(R.id.answer3Btn);
            answer3.setBackgroundColor(Color.GRAY);
            answer4 = findViewById(R.id.answer4Btn);
            answer4.setBackgroundColor(Color.GRAY);
            answerIndex = 1;

        } else if (view.getId() == R.id.answer2Btn) {

            answer1 = findViewById(R.id.answer1Btn);
            answer1.setBackgroundColor(Color.GRAY);
            answer2 = findViewById(R.id.answer2Btn);
            answer2.setBackgroundColor(Color.GREEN);
            answer3 = findViewById(R.id.answer3Btn);
            answer3.setBackgroundColor(Color.GRAY);
            answer4 = findViewById(R.id.answer4Btn);
            answer4.setBackgroundColor(Color.GRAY);
            answerIndex = 2;

        } else if (view.getId() == R.id.answer3Btn) {

            answer1 = findViewById(R.id.answer1Btn);
            answer1.setBackgroundColor(Color.GRAY);
            answer2 = findViewById(R.id.answer2Btn);
            answer2.setBackgroundColor(Color.GRAY);
            answer3 = findViewById(R.id.answer3Btn);
            answer3.setBackgroundColor(Color.GREEN);
            answer4 = findViewById(R.id.answer4Btn);
            answer4.setBackgroundColor(Color.GRAY);
            answerIndex = 3;

        } else {

            answer1 = findViewById(R.id.answer1Btn);
            answer1.setBackgroundColor(Color.GRAY);
            answer2 = findViewById(R.id.answer2Btn);
            answer2.setBackgroundColor(Color.GRAY);
            answer3 = findViewById(R.id.answer3Btn);
            answer3.setBackgroundColor(Color.GRAY);
            answer4 = findViewById(R.id.answer4Btn);
            answer4.setBackgroundColor(Color.GREEN);
            answerIndex = 4;

        }

        submit = findViewById(R.id.submitBtn);
        submit.setClickable(true);

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
     * Επιστρέφει την ερώτηση.
     * @return Την ερώτηση
     */
    @Override
    public String getQuestion() {
        question = findViewById(R.id.question);
        return question.getText().toString();
    }

    /**
     * Επιστρέφει την πρώτη διαθέσιμη απάντηση.
     * @return Την πρώτη διαθέσιμη απάντηση
     */
    @Override
    public String getAnswer1() {
        answer1 = findViewById(R.id.answer1Btn);
        return answer1.getText().toString();
    }

    /**
     * Επιστρέφει την δεύτερη διαθέσιμη απάντηση.
     * @return Την δεύτερη διαθέσιμη απάντηση
     */
    @Override
    public String getAnswer2() {
        answer2 = findViewById(R.id.answer2Btn);
        return answer2.getText().toString();
    }

    /**
     * Επιστρέφει την τρίτη διαθέσιμη απάντηση.
     * @return Την τρίτη διαθέσιμη απάντηση
     */
    @Override
    public String getAnswer3() {
        answer3 = findViewById(R.id.answer3Btn);
        return answer3.getText().toString();
    }

    /**
     * Επιστρέφει την τέταρτη διαθέσιμη απάντηση.
     * @return Την τέταρτη διαθέσιμη απάντηση
     */
    @Override
    public String getAnswer4() {
        answer4 = findViewById(R.id.answer4Btn);
        return answer4.getText().toString();
    }

    /**
     * Επιστρέφει τον εναπομείναντα χρόνο του χρονομέτρου.
     * @return Ο εναπομείναντας χρόνος
     */
    @Override
    public String getClock(){
        clock = findViewById(R.id.counter);
        return clock.getText().toString();
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
     * Επιστρέφει το id της πρόκλησης στο quiz.
     * @return Το id της πρόκλησης στο quiz
     */
    @Override
    public int getQuizChallengeId() {
        Intent intent = getIntent();
        return intent.getIntExtra("QuizChallengeId", 0);
    }

    /**
     * Επιστρέφει το πλήθος των ερωτήσεων που έχουν απαντήθεί.
     * @return Το πλήθος των ερωτήσεων που έχουν απαντήθεί
     */
    @Override
    public int getCount() {
        return count;
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
     * Θέτει την ερώτηση
     * @param q Η ερώτηση
     */
    @Override
    public void setQuestion(String q) {
        question = findViewById(R.id.question);
        question.setText(q);
    }

    /**
     * Θέτει την πρώτη διαθέσιμη απάντηση.
     * @param a1 Η πρώτη διαθέσιμη απάντηση
     */
    @Override
    public void setAnswer1(String a1) {
        answer1 = findViewById(R.id.answer1Btn);
        answer1.setText(a1);

    }

    /**
     * Θέτει την δεύτερη διαθέσιμη απάντηση.
     * @param a2 Η δεύτερη διαθέσιμη απάντηση
     */
    @Override
    public void setAnswer2(String a2) {
        answer2 = findViewById(R.id.answer2Btn);
        answer2.setText(a2);

    }

    /**
     * Θέτει την τρίτη διαθέσιμη απάντηση.
     * @param a3 Η τρίτη διαθέσιμη απάντηση
     */
    @Override
    public void setAnswer3(String a3) {
        answer3 = findViewById(R.id.answer3Btn);
        answer3.setText(a3);

    }

    /**
     * Θέτει την τέταρτη διαθέσιμη απάντηση.
     * @param a4 Η τέταρτη διαθέσιμη απάντηση
     */
    @Override
    public void setAnswer4(String a4) {
        answer4 = findViewById(R.id.answer4Btn);
        answer4.setText(a4);

    }

    /**
     * Θέτει το χρονόμετρο.
     * @param time Ο χρόνος που ορίζει
     */
    @Override
    public void setClock(String time){
        clock = findViewById(R.id.counter);
        clock.setText(time);
    }

    /**
     * Αυξάνει το πλήθος των ερωτήσεων που έχουν απαντήθεί κατά 1.
     */
    @Override
    public void addCount() {
        count++;
    }
}
