package com.example.quiz.ViewModels.view.challenges;

import com.example.quiz.dao.Initializer;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.QuizChallenge;
import com.example.quiz.memorydao.MemoryInitializer;
import com.example.quiz.memorydao.PlayerDAOMemory;
import com.example.quiz.memorydao.QuestionDAOMemory;
import com.example.quiz.memorydao.QuizChallengeDAOMemory;
import com.example.quiz.memorydao.QuizDAOMemory;
import com.example.quiz.view.challenges.ChallengesPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ChallengesPresenterTest {

    private Initializer dataHelper;
    private ChallengesPresenter presenter;
    private ChallengesViewStub view;
    private QuestionServiceStub serviceStub;


    /**
     * Δημιουργία δεδομένων για τα τέστ
     */
    @Before
    public void setUp() {

        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new ChallengesViewStub();
        serviceStub = new QuestionServiceStub();

        presenter = new ChallengesPresenter(view, new QuizChallengeDAOMemory(), new QuizDAOMemory(), new QuestionDAOMemory(), new PlayerDAOMemory(), serviceStub);

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
     * Έλεγχος get/set μεθόδων για το clock
     */
    @Test
    public void clockTest() {
        Assert.assertNull(view.getClock());
        view.setClock("test");
        Assert.assertEquals("test", view.getClock());
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
     * Έλεγχος για σωστή δημιουργία της λίστας με τις προκλήσης που έχω κάνει
     */
    @Test
    public void myChallengesTest() {
        view.setUsername("roni");
        presenter.initLists();

        Assert.assertEquals(1, view.getMyChallengesList().size());

    }

    /**
     * Έλεγχος για σωστή δημιουργία της λίστας με τις προκλήσης που μου έχουν κάνει
     */
    @Test
    public void incomingChallengesTest() {
        view.setUsername("roni");
        presenter.initLists();

        Assert.assertEquals(0, view.getIncomingChallengesList().size());

    }

    /**
     * Ελέγχει έαν γίνεται σωστά η διαδικασία της αποδοχής της πρόκλησης
     */
    @Test
    public void acceptChallengeTest() {

        QuizChallenge quizChallenge = dataHelper.getQuizChallengeDAO().find(1);
        presenter.acceptChallenge(1);
        ArrayList<Question> questions = new ArrayList<>();
        for(int i = 1; i <= 20; i++)
            questions.add(dataHelper.getQuestionDAO().find(i));

        serviceStub.setQuestions(questions);


        Assert.assertEquals("ERROR on creating quiz", view.getToastMessage());
        presenter.acceptChallenge(1);
        Assert.assertEquals("Quiz Created", view.getToastMessage());
    }

    /**
     * Ελέγχει εάν αρχίζει το dual activity σωστά
     */
    @Test
    public void startDualActivityClickTest() {
        presenter.startDual(1);
        Assert.assertEquals(1, view.getStartDualActivityClick());

    }

    /**
     * Ελέγχει εάν αλλάζει σωστά το όνομα χρήστη
     */
    @Test
    public void usernameChangedTest() {
        view.setUsername();
        Assert.assertTrue(view.getUsernameChanged());
    }

    /**
     * Ελέγχει get/set για την λίστα των δικών μου προκλήσεων
     */
    @Test
    public void myChallengesListViewTest() {
        view.setMyChallengesListView();
        Assert.assertNull(view.getMyChallenges());

    }

    /**
     * Ελέγχει get/set για την λίστα των προκλήσεων που μου έχουν κάνει
     */
    @Test
    public void incomingChallengesListViewTest() {
        view.setIncomingChallengesListView();
        Assert.assertNull(view.getIncomingChallenges());

    }

}
