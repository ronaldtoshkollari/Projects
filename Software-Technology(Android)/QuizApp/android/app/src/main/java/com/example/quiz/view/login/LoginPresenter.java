package com.example.quiz.view.login;

import com.example.quiz.dao.PlayerDao;
import com.example.quiz.domain.Player;

public class LoginPresenter {

    private LoginView loginView;
    private PlayerDao playerDao;


    /**
     * Φτιάχνει το Presenter
     * @param loginView
     * @param playerDao
     */
    public LoginPresenter(LoginView loginView, PlayerDao playerDao) {

        this.loginView = loginView;
        this.playerDao = playerDao;

    }


    /**
     * Γίνεται η σύνδεση εφόσον ο παίκτης υπάρχει
     */
    public void logIn() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();

        if(username.trim().length() == 0){
            loginView.setUsernameRequiredError("Το πεδίο αυτό είναι υποχρεωτικό");
            loginView.setUsernameRequiredTextViewVisible();
            return;
        }else{
            loginView.setUsernameRequiredTextViewInvisible();
        }

        if(password.trim().length() == 0){
            loginView.setPasswordRequiredError("Το πεδίο αυτό είναι υποχρεωτικό");
            loginView.setPasswordRequiredTextViewVisible();
            return;
        }else {
            loginView.setPasswordRequiredTextViewInvisible();
        }

        Player player = playerDao.find(username);
        if(player == null){
            loginView.setUsernameRequiredError("Ο χρήστης δεν υπάρχει");
            loginView.setUsernameRequiredTextViewVisible();
            return;
        }else{
            loginView.setUsernameRequiredTextViewInvisible();


            //if player exists
            if (player.getPassword().equals(password)){
                loginView.setPasswordRequiredTextViewInvisible();

                loginView.showToast("Logged In");
                //arxizei to activity toy main home
                loginView.startHomeActivity();
            }else{
                loginView.setPasswordRequiredError("Λανθασμένος κωδικός πρόσβασης");
                loginView.setPasswordRequiredTextViewVisible();
                return;
            }


        }

    }


    /**
     * Ξεκινάει το Activity για την εγγραφή
     */
    public void startRegisterActivity(){
        loginView.startRegisterActivity();
    }

}
