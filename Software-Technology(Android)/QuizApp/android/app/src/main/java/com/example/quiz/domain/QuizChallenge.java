package com.example.quiz.domain;


public class QuizChallenge {

    private int quizChallengeId;
    private Quiz quiz;
    private Player initiator;
    private Player participant;
    private boolean accepted;
    private DateTime date;
    private DifficultyLevel level;
    private QuizCategory category;

    /**
     * Κατασκευαστής
     */
    public QuizChallenge(){}

    /**
     * Κατασκευαστής
     * @param quizChallengeId Το id της πρόκλησης του quiz
     * @param quiz Το quiz
     * @param initiator Ο δημιουργός του quiz
     * @param participant Ο συμμετέχων του quiz
     * @param date Η ημερομηνία και ώρα δημιουργίας της πρόκλησης
     * @param level Το επίπεδο δυσκολίας των ερωτήσεων
     * @param category Η κατηγορία των ερωτήσεων
     */
    public QuizChallenge(int quizChallengeId, Quiz quiz, Player initiator, Player participant, DateTime date, DifficultyLevel level, QuizCategory category) {
        this.quizChallengeId = quizChallengeId;
        this.quiz = quiz;
        this.initiator = initiator;
        this.participant = participant;
        this.date = date;
        this.level = level;
        this.category = category;
    }

    /**
     * Κατασκευαστής
     * @param quizChallengeId Το id της πρόκλησης του quiz
     * @param quiz Το quiz
     * @param initiator Ο δημιουργός του quiz
     * @param participant Ο συμμετέχων του quiz
     * @param accepted Εάν η πρόκληση έγινε αποδεκτή
     * @param date Η ημερομηνία και ώρα δημιουργίας της πρόκλησης
     * @param level Το επίπεδο δυσκολίας των ερωτήσεων
     * @param category Η κατηγορία των ερωτήσεων
     */
    public QuizChallenge(int quizChallengeId, Quiz quiz, Player initiator, Player participant, boolean accepted, DateTime date, DifficultyLevel level, QuizCategory category) {
        this.quizChallengeId = quizChallengeId;
        this.quiz = quiz;
        this.initiator = initiator;
        this.participant = participant;
        this.accepted = accepted;
        this.date = date;
        this.level = level;
        this.category = category;
    }

    /**
     * Επιστρέφει το id της πρόκλησης του quiz
     * @return Το id της πρόκλησης του quiz
     */
    public int getQuizChallengeId() {
        return quizChallengeId;
    }

    /**
     * Θέτει το id της πρόκλησης του quiz
     * @param quizChallengeId Το id της πρόκλησης του quiz
     */
    public void setQuizChallengeId(int quizChallengeId) {
        this.quizChallengeId = quizChallengeId;
    }

    /**
     * Επιστρέφει το quiz
     * @return Το quiz
     */
    public Quiz getQuiz() {
        return quiz;
    }

    /**
     * Θέτει το quiz
     * @param quiz Το quiz
     */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     * Επιστρέφει τον δημιουργό του quiz
     * @return Τον δημιουργό του quiz
     */
    public Player getInitiator() {
        return initiator;
    }

    /**
     * Θέτει τον δημιουργό του quiz
     * @param initiator Ο δημιουργός του quiz
     */
    public void setInitiator(Player initiator) {
        this.initiator = initiator;
    }

    /**
     * Επιστρέφει τον συμμετέχων του quiz
     * @return Τον συμμετέχων του quiz
     */
    public Player getParticipant() {
        return participant;
    }

    /**
     * Θέτει τον δημιουργό του quiz
     * @param participant Ο συμμετέχων του quiz
     */
    public void setParticipant(Player participant) {
        this.participant = participant;
    }

    /**
     * Επιστρέφει εάν η προκληση έγινε αποδεκτή
     * @return Εάν η προκληση έγινε αποδεκτή
     */
    public boolean isAccepted() {
        return accepted;
    }

    /**
     * Θέτει εάν η προκληση έγινε αποδεκτή
     * @param accepted Εάν η προκληση έγινε αποδεκτή
     */
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    /**
     * Επιστρέφει την ημερομηνία και ώρα δημιουργίας της πρόκλησης
     * @return Την ημερομηνία και ώρα δημιουργίας της πρόκλησης
     */
    public DateTime getDate() {
        return date;
    }

    /**
     * Θέτει την ημερομηνία και ώρα δημιουργίας της πρόκλησης
     * @param date Η ημερομηνία και ώρα δημιουργίας της πρόκλησης
     */
    public void setDate(DateTime date) {
        this.date = date;
    }

    /**
     * Επιστρέφει το επίπεδο δυσκολίας
     * @return Το επίπεδο δυσκολίας
     */
    public DifficultyLevel getLevel() {
        return level;
    }

    /**
     * Θέτει το επίπεδο δυσκολίας
     * @param level Το επίπεδο δυσκολίας
     */
    public void setLevel(DifficultyLevel level) {
        this.level = level;
    }

    /**
     * Επιστρέφει την κατηγορία
     * @return Την κατηγορία
     */
    public QuizCategory getCategory() {
        return category;
    }

    /**
     * Θέτει την κατηγορία
     * @param category Την κατηγορία
     */
    public void setCategory(QuizCategory category) {
        this.category = category;
    }
}
