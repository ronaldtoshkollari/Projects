package com.example.quiz.view.challenges;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.R;
import com.example.quiz.domain.QuizChallenge;
import com.example.quiz.memorydao.PlayerDAOMemory;
import com.example.quiz.memorydao.QuestionDAOMemory;
import com.example.quiz.memorydao.QuizChallengeDAOMemory;
import com.example.quiz.memorydao.QuizDAOMemory;
import com.example.quiz.service.QuestionServiceImpl;
import com.example.quiz.view.dual.DualActivity;

import java.util.ArrayList;

public class ChallengesActivity extends AppCompatActivity implements ChallengesView {

    private ChallengesPresenter presenter;

    private TextView myUserName;
    private ListView myChallenges;
    private ListView incomingChallenges;
    private ArrayAdapter arrayAdapter;
    private TextView clock;

//    private float x1,x2,y1,y2;

    /**
     * Δημιουργεί το layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        setUsername();

        presenter = new ChallengesPresenter(this, new QuizChallengeDAOMemory(), new QuizDAOMemory(), new QuestionDAOMemory(),
                new PlayerDAOMemory(), new QuestionServiceImpl());

        presenter.initLists();
        presenter.countdown();

        incomingChallenges.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                presenter.cancelTimer();
                presenter.acceptChallenge(position+1);
                presenter.startDual(position+1);
            }

        });

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
     * Επιστρέφει τη λίστα με τις προκλήσεις που έχει στείλει.
     * @return Η λίστα με τις προκλήσεις που έχει στείλει
     */
    @Override
    public ListView getMyChallenges() {
        myChallenges = findViewById(R.id.myList);
        return myChallenges;
    }

    /**
     * Επιστρέφει τη λίστα με τις προκλήσεις που έχει δεχτεί.
     * @return Η λίστα με τις προκλήσεις που έχει δεχτεί
     */
    @Override
    public ListView getIncomingChallenges() {
        incomingChallenges = findViewById(R.id.incomingList);
        return incomingChallenges;
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
     * Θέτει το χρονόμετρο.
     * @param time Ο χρόνος που ορίζει
     */
    @Override
    public void setClock(String time){
        clock = findViewById(R.id.counter);
        clock.setText(time);
    }

    /**
     * Θέτει τη λίστα με τις προκλήσεις που στέλνει.
     * @param myChallengesArray Η λίστα με τις προκλήσεις
     */
    @Override
    public void setMyChallenges(ArrayList<QuizChallenge> myChallengesArray) {

        myChallenges = findViewById(R.id.myList);
        ArrayList<String> temp = new ArrayList<>();

        for(QuizChallenge challenge: myChallengesArray){
            temp.add("Opponent: " + challenge.getParticipant().getUsername() + ", Category: " + challenge.getCategory().getCategory() + ", Level: " + challenge.getLevel().getDifficultyLevel());
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, temp);
        myChallenges.setAdapter(arrayAdapter);

    }

    /**
     * Θέτει τη λίστα με τις προκλήσεις που δέχεται.
     * @param incomingChallengesArray Η λίστα με τις προκλήσεις
     */
    @Override
    public void setIncomingChallenges(ArrayList<QuizChallenge> incomingChallengesArray) {

        incomingChallenges = findViewById(R.id.incomingList);
        ArrayList<String> temp = new ArrayList<>();

        for(QuizChallenge challenge : incomingChallengesArray){
            temp.add("Opponent: " + challenge.getParticipant().getUsername() + ", Category: " + challenge.getCategory().getCategory() + ", Level: " + challenge.getLevel().getDifficultyLevel());
        }


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, temp);
        incomingChallenges.setAdapter(arrayAdapter);

    }

    /**
     * Μεταφέρει τον χρήστη στο activity DualActivity
     * όταν γίνει click πάνω στην πρόκληση με id challengeId.
     * @param challengeId Το μοναδικό id της πρόκλησης
     */
    @Override
    public void startDualActivity(int challengeId) {
        Intent intent = new Intent(this, DualActivity.class);
        intent.putExtra("USERNAME", getUsername());
        intent.putExtra("QuizChallengeId", challengeId);
        startActivity(intent);
        finish();
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
