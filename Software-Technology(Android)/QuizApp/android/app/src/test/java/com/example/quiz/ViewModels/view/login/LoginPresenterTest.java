package com.example.quiz.ViewModels.view.login;

import com.example.quiz.dao.Initializer;
import com.example.quiz.memorydao.MemoryInitializer;
import com.example.quiz.memorydao.PlayerDAOMemory;
import com.example.quiz.view.login.LoginPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginPresenterTest {

    Initializer initializer;
    LoginViewStub view;
    LoginPresenter presenter;

    /**
     * Δημιουργία δεδομένων για τα τέστ
     */
    @Before
    public void setUp(){
        initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new LoginViewStub();
        presenter = new LoginPresenter(view, new PlayerDAOMemory());
    }

    /**
     * Έλεγχος get/set μεθόδων για το username
     */
    @Test
    public void usernameTest(){
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
     * Έλεγχος για το μήνυμα που θα εμφανιστεί στην οθόνη
     */
    @Test
    public void showToast(){
        Assert.assertNull(view.getToastMessage());
        view.showToast("test");
        Assert.assertEquals("test", view.getToastMessage());
    }

    @Test
    public void usernameErrorTest(){
        Assert.assertNull(view.getUsernameErrorMsg());
        view.setUsernameError("Must fill this area");
        Assert.assertEquals("Must fill this area", view.getUsernameErrorMsg());
    }

    @Test
    public void passwordErrorTest(){
        Assert.assertNull(view.getPasswordErrorMsg());
        view.setPasswordError("Must fill this area");
        Assert.assertEquals("Must fill this area", view.getPasswordErrorMsg());
    }

    @Test
    public void startRegisterActivityClickTest() {
        presenter.startRegisterActivity();
        Assert.assertEquals(1, view.getStartRegisterActivityClick());
    }

    @Test
    public void loginTest() {
        //Empty username
        view.setUsername("");
        presenter.logIn();
        Assert.assertEquals("Το πεδίο αυτό είναι υποχρεωτικό", view.getUsernameRequiredError());

        //Empty Password
        view.setUsername("roni");
        view.setPassword("");
        Assert.assertEquals("Το πεδίο αυτό είναι υποχρεωτικό", view.getPasswordRequiredError());

        //Used does not exist
        view.setUsername("no_user");
        view.setPassword("1233456");
        presenter.logIn();
        Assert.assertEquals("Ο χρήστης δεν υπάρχει", view.getUsernameRequiredError());

        //Wrong password
        view.setUsername("roni");
        view.setPassword("1233456");
        presenter.logIn();
        Assert.assertEquals("Λανθασμένος κωδικός πρόσβασης", view.getPasswordRequiredError());

        //login
        view.setUsername("roni");
        view.setPassword("roni");
        presenter.logIn();
        Assert.assertEquals("Logged In", view.getToastMessage());
        Assert.assertEquals(1, view.getStartHomeActivityClick());


    }

}
