package com.example.quiz.view.dual;

import android.os.CountDownTimer;
import android.widget.Button;

import com.example.quiz.dao.AnswerDAO;
import com.example.quiz.dao.PlayerDao;
import com.example.quiz.dao.QuestionDAO;
import com.example.quiz.dao.QuizChallengeDAO;
import com.example.quiz.dao.QuizDAO;
import com.example.quiz.dao.QuizParticipationDAO;
import com.example.quiz.domain.Answer;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.Quiz;
import com.example.quiz.domain.QuizChallenge;
import com.example.quiz.domain.QuizParticipation;

import java.util.ArrayList;

public class DualPresenter {

    private DualView dualView;
    private PlayerDao playerDao;
    private QuizChallengeDAO quizChallengeDAO;
    private QuizDAO quizDAO;
    private QuestionDAO questionDAO;
    private AnswerDAO answerDAO;
    private QuizParticipationDAO quizParticipationDAO;
    private ArrayList<Question> questions;
    private ArrayList<Answer> answers;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis;
    private int totalTime;
    private int score;
    private int totalCorrect;
    QuizParticipation quizParticipation;


    /**
     * Φτιάχνει τον Presenter για αλλαγές αργότερα
     * @param dualView
     * @param playerDao
     * @param questionDAO
     * @param quizChallengeDAO
     * @param quizDAO
     * @param quizParticipationDAO
     * @param answerDAO
     */
    public DualPresenter(DualView dualView, PlayerDao playerDao, QuestionDAO questionDAO, QuizChallengeDAO quizChallengeDAO, QuizDAO quizDAO, QuizParticipationDAO quizParticipationDAO, AnswerDAO answerDAO) {
        this.dualView = dualView;
        this.playerDao = playerDao;
        this.questionDAO = questionDAO;
        this.quizChallengeDAO = quizChallengeDAO;
        this.quizDAO = quizDAO;
        this.quizParticipationDAO = quizParticipationDAO;
        this.answerDAO = answerDAO;
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        totalTime = 0;
        score = 0;
        totalCorrect = 0;
    }


    /**
     * Αρχικοποιεί μια μάχη Quiz, δηλαδή τις ερωτήσεις
     */
    public void initDual() {


        QuizChallenge quizChallenge = quizChallengeDAO.find(dualView.getQuizChallengeId());
        Quiz quiz = quizChallenge.getQuiz();

        questions = (ArrayList<Question>) quiz.getQuestions();

    }


    /**
     * Αρχικοποιεί την επόμενη ερώτηση
     * @param questionNum Ο αριθμός της επόμενης ερώτησης
     */
    public void getNextQuestion(int questionNum) {

        if(questionNum == 20) return;
        String question = questions.get(questionNum).getQuestion();
        String correctAnswer = questions.get(questionNum).getCorrectAnswer();
        ArrayList<String> wrongAnswers = questions.get(questionNum).getWrongAnswers();

        dualView.setQuestion(question);
        dualView.setAnswer1(correctAnswer);
        dualView.setAnswer2(wrongAnswers.get(0));
        dualView.setAnswer3(wrongAnswers.get(1));
        dualView.setAnswer4(wrongAnswers.get(2));

        countdown();


    }

    /**
     * Αποθηκεύει την απάντηση μια ερώτησης
     * @param questionIndex Ο αριθμός της ερώτησης μας
     * @param answerIndex Ο αριθμός που θα πάρει η απάντηση
     */
    public void submitQuestion(int questionIndex, int answerIndex) {

        if(questionIndex == 20) return;
        Question question = questions.get(questionIndex);
        String selectedAnswer;
        Answer answer;

        if (answerIndex == 0) selectedAnswer = "";
        else if (answerIndex == 1) selectedAnswer = dualView.getAnswer1();
        else if (answerIndex == 2) selectedAnswer = dualView.getAnswer2();
        else if (answerIndex == 3) selectedAnswer = dualView.getAnswer3();
        else selectedAnswer = dualView.getAnswer4();

        if (question.getCorrectAnswer().equals(selectedAnswer)) {
            answer = new Answer(answerDAO.nextId(), question, selectedAnswer, true);
            totalCorrect++;
        } else {
            answer = new Answer(answerDAO.nextId(), question, selectedAnswer, false);
        }

        answerDAO.save(answer);

    }


    /**
     * Υπολογίζει το τελικό σκορ του παίκτη
     */
    public void calculateScore() {

        QuizChallenge quizChallenge = quizChallengeDAO.find(dualView.getQuizChallengeId());
        Quiz quiz = quizChallenge.getQuiz();

        questions = (ArrayList<Question>) quiz.getQuestions();
        score = 0;

        if (dualView.getUsername().equals(quizChallenge.getParticipant().getUsername())) {
            int i;
            if (dualView.getQuizChallengeId() == 1) i = 1;
            else i = dualView.getQuizChallengeId() * 40 / 2 + 1;

            for (Question question : questions) {
                if (answerDAO.find(i).isCorrect()) {
                    score += 2;
                    answers.add(answerDAO.find(i));
                }
                i++;
            }


        } else if (dualView.getUsername().equals(quizChallenge.getInitiator().getUsername())) {
            int i;

            if (dualView.getQuizChallengeId() == 1) i = 21;
            else i = dualView.getQuizChallengeId() * 40 / 2 + 21;

            for (Question question : questions) {

                if (answerDAO.find(i).isCorrect()) {
                    score += 2;
                    answers.add(answerDAO.find(i));
                }
                i++;
            }

        }

        quizParticipation = new QuizParticipation(quizParticipationDAO.nextId(), quiz, playerDao.find(dualView.getUsername()), answers, totalTime, totalCorrect, false);
        quizParticipationDAO.save(quizParticipation);

    }


    /**
     * Εμφανίζει στον χρήστη τον τελικό νικητή της μάχης
     */
    public void getWinner() {

        QuizChallenge quizChallenge = quizChallengeDAO.find(dualView.getQuizChallengeId());
        int quizId = quizChallenge.getQuiz().getQuizId();
        String username = dualView.getUsername();
        String opponent;
        int opponentScore = -1;
        int opponentsQuizParticipation = 0;
        ArrayList<QuizParticipation> opponentQuizParticipations = new ArrayList<>();
        QuizParticipation opponentQuizParticipation = new QuizParticipation();


        if (username.equals(quizChallenge.getInitiator().getUsername())) {

            opponent = quizChallenge.getParticipant().getUsername();
            for (QuizParticipation quizParticipation : quizParticipationDAO.findAll()) {

                if (quizParticipation.getPlayer().getUsername().equals(opponent)) {
                    opponentQuizParticipations.add(quizParticipation);
                }//end if if

            }//end of for

            for (int i = 0; i < opponentQuizParticipations.size(); i++) {

                if (opponentQuizParticipations.get(i).getQuiz().getQuizId() == quizId) {


                    opponentsQuizParticipation = opponentQuizParticipations.get(i).getSecondsPlayed();
                    opponentScore = opponentQuizParticipations.get(i).getCorrectAnswers() * 2;

                } else {
                    dualView.showToast("Opponent has not played yet");
                }

            }//end of for
        } else {


            opponent = quizChallenge.getInitiator().getUsername();
            for (QuizParticipation quizParticipation : quizParticipationDAO.findAll()) {

                if (quizParticipation.getPlayer().getUsername().equals(opponent)) {
                    opponentQuizParticipations.add(quizParticipation);
                }//end of if

            }//end of for

            for (int i = 0; i < opponentQuizParticipations.size(); i++) {

                if (opponentQuizParticipations.get(i).getQuiz().getQuizId() == quizId) {

                    opponentQuizParticipation = opponentQuizParticipations.get(i);
                    opponentsQuizParticipation = opponentQuizParticipations.get(i).getSecondsPlayed();
                    opponentScore = opponentQuizParticipations.get(i).getCorrectAnswers() * 2;

                } else {
                    dualView.showToast("Opponent has not played yet");
                }

            }//end of for

        }

        if (opponentScore >= 0) {


            if (score > opponentScore) {
                quizParticipation.setWinner(true);
                dualView.showToast("The winner is " + dualView.getUsername());
            } else if (score < opponentScore) {
                dualView.showToast("The winner is " + opponent);
            } else {
                if (totalTime < opponentsQuizParticipation) {
                    opponentQuizParticipation.setWinner(true);
                    dualView.showToast("The winner is " + dualView.getUsername());
                } else if (totalTime > opponentsQuizParticipation) {
                    dualView.showToast("The winner is " + opponent);
                } else {
                    dualView.showToast("It is a draw");
                }
            }
        } else {
            dualView.showToast("Opponent has not played yet");
        }

    }


    /**
     * Ξεκινάει και χειρίζεται το χρονόμετρο
     */
    public void countdown() {
        dualView.setClock("20");

        mCountDownTimer = new CountDownTimer(Long.parseLong(dualView.getClock()) * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                dualView.setClock(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                dualView.setClock("0");

                submitQuestion(dualView.getCount(), 0);
                addTotalTime(20);

                dualView.addCount();
                if (dualView.getCount() < 20) {
                    getNextQuestion(dualView.getCount());
                } else {
                    calculateScore();
                }
            }
        }.start();

    }//end of countdown()


    /**
     * Ακυρώνει το χρονόμετρο
     */
    public void cancelTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }


    /**
     * Αυξάνει τον συνολικό χρόνο
     * @param time Ο χρόνος μια ερώτησης
     */
    public void addTotalTime(int time) {
        this.totalTime += time;
    }


    /**
     * Επιστρέφει μια λίστα απο ερωτήσεις
     * @return Την λίστα με τις ερωτήσεις για ένα Quiz
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
