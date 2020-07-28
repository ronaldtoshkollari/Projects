package com.example.quiz.ViewModels.view.register;

import com.example.quiz.dao.Initializer;
import com.example.quiz.memorydao.MemoryInitializer;
import com.example.quiz.memorydao.PlayerDAOMemory;
import com.example.quiz.view.register.RegisterPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterPresenterTest {

    Initializer dataHelper;
    RegisterViewStub view;
    RegisterPresenter presenter;

    /**
     * Δημιουργία δεδομένων για τα τέστ
     */
    @Before
    public void setUp() {

        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new RegisterViewStub();

        presenter = new RegisterPresenter(view, new PlayerDAOMemory());

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
     * Έλεγχος get/set μεθόδων για το password
     */
    @Test
    public void passwordTest(){
        Assert.assertNull(view.getPassword());
        view.setPassword("test");
        Assert.assertEquals("test", view.getPassword());
    }

    /**
     * Έλεγχος get/set μεθόδων για το email
     */
    @Test
    public void emailTest(){
        Assert.assertNull(view.getEmail());
        view.setEmail("test");
        Assert.assertEquals("test", view.getEmail());
    }

    /**
     * Έλεγχος get/set μεθόδων για το firstName
     */
    @Test
    public void firstNameTest(){
        Assert.assertNull(view.getFirstName());
        view.setFirstName("test");
        Assert.assertEquals("test", view.getFirstName());
    }

    /**
     * Έλεγχος get/set μεθόδων για το lastName
     */
    @Test
    public void lastNameTest(){
        Assert.assertNull(view.getLastName());
        view.setLastName("test");
        Assert.assertEquals("test", view.getLastName());
    }

    /**
     * Έλεγχος get/set μεθόδων για το favouriteCategory
     */
    @Test
    public void favouriteCategoryTest(){
        Assert.assertNull(view.getFavouriteCategory());
        view.setFavouriteCategory("test");
        Assert.assertEquals("test", view.getFavouriteCategory());
    }

    /**
     * Έλεγχος get/set μεθόδων για το gender
     */
    @Test
    public void genderTest(){
        Assert.assertNull(view.getGender());
        view.setGender("test");
        Assert.assertEquals("test", view.getGender());
    }

    /**
     * Έλεγχος για το μήνυμα που θα εμφανιστεί στην οθόνη
     */
    @Test
    public void showToast(){
        Assert.assertNull(view.getToastMessage());
        view.showToast("test");
        Assert.assertEquals("test", view.getToastMessage());
    }

    /**
     * Ελέγχει εάν αρχίζει το activity LoginActivity σωστά
     */
    @Test
    public void startLoginActivityClickTest() {
        presenter.startLoginActivity();
        Assert.assertEquals(1, view.getStartLoginClick());
    }

    /**
     * Ελέγχει την εγκυρότηρα των στοιχείων πρόσβασης που ο χρήστης εισήγαγε και
     * εάν αρχίζει το activity HomeActivity σωστά
     */
    @Test
    public void startHomeActivityClickTest() {
        view.setUsername("");
        presenter.register();
        Assert.assertEquals("Username must be filled", view.getToastMessage());

        view.setUsername("test_username");
        view.setPassword("test");
        presenter.register();
        Assert.assertEquals("Not valid password", view.getToastMessage());

        view.setUsername("test_username");
        view.setPassword("test_password");
        view.setEmail("");
        presenter.register();
        Assert.assertEquals("Email must be filled", view.getToastMessage());

        view.setUsername("roni");
        view.setPassword("test_password");
        view.setEmail("test@gmail.com");
        presenter.register();
        Assert.assertEquals("Username already exists", view.getToastMessage());

        view.setUsername("test_username");
        view.setPassword("test_password");
        view.setEmail("test@gmail.com");
        presenter.register();
        Assert.assertEquals(1, view.getStartHomeActivityClick());

    }
}
