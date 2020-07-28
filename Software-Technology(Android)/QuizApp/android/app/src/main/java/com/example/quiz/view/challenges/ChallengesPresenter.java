package com.example.quiz.view.challenges;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.view.View;

import com.example.quiz.dao.PlayerDao;
import com.example.quiz.dao.QuestionDAO;
import com.example.quiz.dao.QuizChallengeDAO;
import com.example.quiz.dao.QuizDAO;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.Quiz;
import com.example.quiz.domain.QuizChallenge;
import com.example.quiz.service.QuestionService;
import com.example.quiz.view.create_quiz.CreateQuizChallengeView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class ChallengesPresenter {

    private QuizChallengeDAO quizChallengeDAO;
    private QuizDAO quizDAO;
    private PlayerDao playerDao;
    private QuestionDAO questionDAO;
    private ChallengesView challengesView;
    private QuestionService service;
    private ArrayList<Question> questions;
    private String url;
    private String category;
    private String difficultyLvl;
    private Quiz quiz;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis;


    /**
     * Αρχικοποιεί τον Presenter για αλλαγές μεταγενέστερα
     * @param challengesView
     * @param quizChallengeDAO
     * @param quizDAO
     * @param questionDAO
     * @param playerDao
     * @param service
     */
    public ChallengesPresenter(ChallengesView challengesView, QuizChallengeDAO quizChallengeDAO, QuizDAO quizDAO, QuestionDAO questionDAO, PlayerDao playerDao, QuestionService service) {

        this.challengesView = challengesView;
        this.quizChallengeDAO = quizChallengeDAO;
        this.quizDAO = quizDAO;
        this.playerDao = playerDao;
        this.questionDAO = questionDAO;
        this.service = service;
    }


    /**
     * Αρχικοποιεί τις λίστες με τις προκλήσεις
     * που δέχεται και στέλνει
     */
    public void initLists() {
        ArrayList<QuizChallenge> myChallenges = new ArrayList<>();
        ArrayList<QuizChallenge> incomingChallenges = new ArrayList<>();

        for (QuizChallenge quizChallenge : quizChallengeDAO.findAll()) {
            if (quizChallenge.getInitiator().getUsername().equals(challengesView.getUsername())) {
                myChallenges.add(quizChallenge);
            } else if (quizChallenge.getParticipant().getUsername().equals(challengesView.getUsername())){
                incomingChallenges.add(quizChallenge);
            }
        }



        challengesView.setIncomingChallenges(incomingChallenges);
        challengesView.setMyChallenges(myChallenges);
    }


    /**
     * Αποδέχεται μια πρόκληση που έχει λάβει
     * και αρχικποιεί τις ερωτήσεις του Quiz
     * τις οποίες παίρνει απο το API μέσω AsyncTask
     * @param quizChallengeId Το μοναδικό id της πρόκλησης
     */
    public void acceptChallenge(int quizChallengeId) {

        QuizChallenge quizChallenge = quizChallengeDAO.find(quizChallengeId);
        quizChallenge.setAccepted(true);



        category = quizChallenge.getCategory().getCategory();
        difficultyLvl = quizChallenge.getLevel().getDifficultyLevel().toLowerCase();


        if(category.equalsIgnoreCase("sports")){
            category = "21";
        }else if(category.equalsIgnoreCase("geography")){
            category = "22";
        }else if(category.equalsIgnoreCase("history")){
            category = "23";
        }else {
            category = "24";
        }

        quiz = quizChallenge.getQuiz();
        service.setCategory(category);
        service.setDifficultyLvl(difficultyLvl);
        service.questionReader();
        questions = service.getQuestions();


        if (questions == null) {
            challengesView.showToast("ERROR on creating quiz");
        }else{
            challengesView.showToast("Quiz Created");
            quiz.setQuestions(questions);
            quizDAO.save(quiz);
        }
    }


    /**
     *  Ενημερώνει το χρονόμετρο που τρέχει
     *  και μετά το πέρας των 20 δευτερολέπτων διαγράφει
     *  τις προκλήσεις που του ήρθαν
     */
    public void countdown(){
        mCountDownTimer = new CountDownTimer(Long.parseLong(challengesView.getClock())*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                challengesView.setClock(millisUntilFinished/1000 + "");

                for(QuizChallenge quizChallenge: quizChallengeDAO.findAll()){
                    if(quizChallenge.isAccepted()){
                        cancelTimer();
                        challengesView.startDualActivity(quizChallenge.getQuizChallengeId());
                    }
                }
            }

            @Override
            public void onFinish() {
                challengesView.setClock("");

                for(QuizChallenge quizChallenge: quizChallengeDAO.findAll()){
                    if(quizChallenge.getInitiator().getUsername().equals(challengesView.getUsername()) || quizChallenge.getParticipant().getUsername().equals(challengesView.getUsername())){
//                        quizChallengeDAO.delete(quizChallenge);
                        challengesView.setMyChallenges(new ArrayList<QuizChallenge>());
                        challengesView.setIncomingChallenges(new ArrayList<QuizChallenge>());
                    }
                }

            }
        }.start();

    }//end of countdown()


    /**
     * Ακυρώνει το χρονόμετρο
     */
    public void cancelTimer(){
        if(mCountDownTimer != null){
            mCountDownTimer.cancel();
        }
    }


    /**
     *
     * Ξεκινάει το Activity για tην μονομαχία
     * @param challengeId Το μοναδικό id του challenge
     */
    public void startDual(int challengeId) {
        challengesView.startDualActivity(challengeId);
    }


}
