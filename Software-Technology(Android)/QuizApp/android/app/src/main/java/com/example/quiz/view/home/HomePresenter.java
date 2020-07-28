package com.example.quiz.view.home;

public class HomePresenter {

    private HomeView homeView;


    /**
     * Φτιάχνει το Presenter
     * @param homeView
     */
    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }


    /**
     * Ξεκινάει το Activity για την δημιουργία του Quiz
     */
    public void createQuiz(){
        homeView.startCreateQuizActivity();
    }


    /**
     * Ξεκινάει το Activity με τις προκλήσεις
     */
    public void replyChallenge(){
        homeView.startChallengesActivity();
    }


    /**
     * Ξεκινάει το Activity με τον Πίνακα Κατάταξης
     */
    public void showLeaderboard(){
        homeView.startShowRankActivity();
    }

}
