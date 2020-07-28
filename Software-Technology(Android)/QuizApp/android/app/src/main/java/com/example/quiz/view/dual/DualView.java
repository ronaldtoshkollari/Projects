package com.example.quiz.view.dual;

public interface DualView {

    /**
     * Επιστρέφει το όνομα χρήστη.
     * @return Το όνομα χρήστη
     */
    String getUsername();

    /**
     * Επιστρέφει το id της πρόκλησης στο quiz.
     * @return Το id της πρόκλησης στο quiz
     */
    int getQuizChallengeId();

    /**
     * Επιστρέφει την ερώτηση.
     * @return Την ερώτηση
     */
    String getQuestion();

    /**
     * Επιστρέφει την πρώτη διαθέσιμη απάντηση.
     * @return Την πρώτη διαθέσιμη απάντηση
     */
    String getAnswer1();

    /**
     * Επιστρέφει την δεύτερη διαθέσιμη απάντηση.
     * @return Την δεύτερη διαθέσιμη απάντηση
     */
    String getAnswer2();

    /**
     * Επιστρέφει την τρίτη διαθέσιμη απάντηση.
     * @return Την τρίτη διαθέσιμη απάντηση
     */
    String getAnswer3();

    /**
     * Επιστρέφει την τέταρτη διαθέσιμη απάντηση.
     * @return Την τέταρτη διαθέσιμη απάντηση
     */
    String getAnswer4();

    /**
     * Επιστρέφει τον εναπομείναντα χρόνο του χρονομέτρου.
     * @return Ο εναπομείναντας χρόνος
     */
    String getClock();

    /**
     * Επιστρέφει το πλήθος των ερωτήσεων που έχουν απαντήθεί.
     * @return Το πλήθος των ερωτήσεων που έχουν απαντήθεί
     */
    int getCount();

    /**
     * Θέτει το όνομα χρήστη.
     */
    void setUsername();

    /**
     * Θέτει την ερώτηση
     * @param q Η ερώτηση
     */
    void setQuestion(String q);

    /**
     * Θέτει την πρώτη διαθέσιμη απάντηση.
     * @param a1 Η πρώτη διαθέσιμη απάντηση
     */
    void setAnswer1(String a1);

    /**
     * Θέτει την δεύτερη διαθέσιμη απάντηση.
     * @param a2 Η δεύτερη διαθέσιμη απάντηση
     */
    void setAnswer2(String a2);

    /**
     * Θέτει την τρίτη διαθέσιμη απάντηση.
     * @param a3 Η τρίτη διαθέσιμη απάντηση
     */
    void setAnswer3(String a3);

    /**
     * Θέτει την τέταρτη διαθέσιμη απάντηση.
     * @param a4 Η τέταρτη διαθέσιμη απάντηση
     */
    void setAnswer4(String a4);

    /**
     * Εμφανίζει ένα Toast.
     * @param message Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String message);

    /**
     * Θέτει το χρονόμετρο.
     * @param time Ο χρόνος που ορίζει
     */
    void setClock(String time);

    /**
     * Αυξάνει το πλήθος των ερωτήσεων που έχουν απαντήθεί κατά 1.
     */
    void addCount();

}
