package com.example.quiz.ViewModels.view.create_quiz;

import com.example.quiz.dao.Initializer;
import com.example.quiz.memorydao.MemoryInitializer;
import com.example.quiz.memorydao.PlayerDAOMemory;
import com.example.quiz.memorydao.QuizChallengeDAOMemory;
import com.example.quiz.memorydao.QuizDAOMemory;
import com.example.quiz.view.create_quiz.CreateQuizChallengePresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateQuizChallengePresenterTest {

    private Initializer dataHelper;
    private CreateQuizChallengePresenter presenter;
    private CreateQuizChallengeViewStub view;

    /**
     * Δημιουργία δεδομένων για τα τέστ
     */
    @Before
    public void setUp() {

        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new CreateQuizChallengeViewStub();

        presenter = new CreateQuizChallengePresenter(view, new QuizChallengeDAOMemory(), new QuizDAOMemory(), new PlayerDAOMemory());

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
     * Έλεγχος get/set μεθόδων για το όνομα χρήστη του αντιπάλου
     */
    @Test
    public void opponentTest() {
        Assert.assertNull(view.getOpponent());
        view.setOpponent("test");
        Assert.assertEquals("test", view.getOpponent());
    }

    /**
     * Έλεγχος get/set μεθόδων για το την κατηγορία των ερωτήσεων
     */
    @Test
    public void categoryTest() {
        Assert.assertNull(view.getCategory());
        view.setCategory("test");
        Assert.assertEquals("test", view.getCategory());
    }

    /**
     * Έλεγχος get/set μεθόδων για το την επίπεδο δυσκολίας των ερωτήσεων
     */
    @Test
    public void levelTest() {
        Assert.assertNull(view.getDifficultyLevel());
        view.setDifficultyLevel("test");
        Assert.assertEquals("test", view.getDifficultyLevel());
    }

    /**
     * Έλεγχος για το μήνυμα που θα εμφανιστεί στην οθόνη
     */
    @Test
    public void showToast() {
        Assert.assertNull(view.getToastMessage());
        view.showToast("Passed");
        Assert.assertEquals("Passed", view.getToastMessage());
    }

    /**
     * Ελέγχει αν δημιουργήθηκε μία νέα πρόκληση
     */
    @Test
    public void initializeQuizChallengeTest() {
        view.setUsername("roni");
        view.setOpponent("stelios");

        Assert.assertTrue(presenter.initializeQuizChallenge());

        view.setUsername("stelios");
        view.setOpponent("opponent");

        Assert.assertFalse(presenter.initializeQuizChallenge());
    }

    /**
     * Ελέγχει εάν αρχίζει το activity ChallengesActivity σωστά
     */
    @Test
    public void startChallengesActivityTest(){
        presenter.startChallenges();
        Assert.assertEquals(1, view.getStartChallengesActivityClick());
    }


}
