package com.example.quiz.view.register;
import com.example.quiz.dao.PlayerDao;
import com.example.quiz.domain.Player;
import com.example.quiz.domain.QuizCategory;

public class RegisterPresenter {

    private RegisterView registerView;
    private PlayerDao playerDao;


    /**
     * Φτιάχνουμε τον Presenter
     * @param registerView
     * @param playerDao
     */
    public RegisterPresenter(RegisterView registerView, PlayerDao playerDao){
        this.registerView = registerView;
        this.playerDao = playerDao;
    }


    /**
     * Κάνει την εγγραφή του χρήστη στπ σύστημα
     * μονο εφόσον βάλει όλα τα υποχρεωτικά πεδία
     */
    public void register(){
        String username = registerView.getUsername();
        String password = registerView.getPassword();
        String email = registerView.getEmail();
        String firstName = registerView.getFirstName();
        String lastName = registerView.getLastName();
        String favouriteCategory = registerView.getFavouriteCategory();
        String gender = registerView.getGender();

        if(username.trim().length() == 0){
            registerView.showToast("Username must be filled");
            return;
        }

        if(password.trim().length() <= 5){
            registerView.showToast("Not valid password");
            return;
        }

        if(email.trim().length() == 0){
            registerView.showToast("Email must be filled");
            return;
        }

        Player player = playerDao.find(username);

        if(player != null){
            registerView.showToast("Username already exists");
            return;
        }else {
            player = new Player(playerDao.nextId(), username, password, email, firstName, lastName ,gender, new QuizCategory(favouriteCategory));
            playerDao.save(player);
            registerView.startHomeActivity();
        }

    }

    /**
     * Ξεκινάει το Activity για την σύνδεση
     */
    public void startLoginActivity(){
        registerView.startLogin();
    }

}
