package com.example.quiz.ViewModels.view.home;

import com.example.quiz.dao.Initializer;
import com.example.quiz.memorydao.MemoryInitializer;
import com.example.quiz.view.home.HomePresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HomePresenterTest {


    private Initializer dataHelper;
    private HomePresenter presenter;
    private HomeViewStub view;

    /**
     * Επιστέφει το όνομα χρήστη του αντιπάλου
     * @return Το όνομα χρήστη του αντιπάλου
     */
    @Before
    public void setUp(){

        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new HomeViewStub();

        presenter = new HomePresenter(view);

    }

    /**
     * Έλεγχος για το μήνυμα που θα εμφανιστεί στην οθόνη
     */
    @Test
    public void showToast(){
        Assert.assertNull(view.getToastMessage());
        view.showToast("Passed");
        Assert.assertEquals("Passed", view.getToastMessage());
    }

    /**
     * Έλεγχος get/set μεθόδων για το username
     */
    @Test
    public void usernameTest() {
        Assert.assertNull(view.getUsername());
        view.setUsername("test");
        Assert.assertEquals("test", view.getUsername());
    }

    /**
     * Ελέγχει εάν αρχίζει το activity ChallengesActivity σωστά
     */
    @Test
    public void startChallengesActivityTest(){
        presenter.replyChallenge();
        Assert.assertEquals(1, view.getStartChallengesActivityClick());
    }

    /**
     * Ελέγχει εάν αρχίζει το activity CreateQuizActivity σωστά
     */
    @Test
    public void startCreateQuizActivityTest(){
        presenter.createQuiz();
        Assert.assertEquals(1, view.getStartCreateQuizActivityClick());
    }

    /**
     * Ελέγχει εάν αρχίζει το activity ShowRankActivity σωστά
     */
    @Test
    public void startShowRankActivityTest(){
        presenter.showLeaderboard();
        Assert.assertEquals(1, view.getStartShowRankActivityClick());
    }

}
