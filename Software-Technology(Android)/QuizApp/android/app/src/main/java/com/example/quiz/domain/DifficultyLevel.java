package com.example.quiz.domain;

public class DifficultyLevel {

    private String difficultyLevel;

    public static String[] difficultyLevels = new String[]{"Easy","Medium","Hard"};

    /**
     * Κατασκευαστής για το επίπεδο δυσκολίας
     */
    public DifficultyLevel() {
        difficultyLevel = null;
    }


    /**
     * Κατασκευαστής για ένα συγκεκριμένο επίπεδο δυσκολίας
     * @param difficultyLevel Το επίπεδο δυσκολιας
     */
    public DifficultyLevel(String difficultyLevel) {
        if (!this.setDifficultyLevel(difficultyLevel))
            this.difficultyLevel = null;
    }


    /**
     * Επιστρέφει το επίπεδο δυσκολιας
     * @return Το επίπεδο δυσκολιας
     */
    public String getDifficultyLevel() {
        return difficultyLevel;
    }


    /**
     * Ορίζει το επίπεδο δυσκολίας
     * @param difficultyLevel Το επίπεδο δυσκολιας
     * @return True ή False
     */
    public boolean setDifficultyLevel(String difficultyLevel) {

        for (String level: difficultyLevels)
            if (level.equals(difficultyLevel)) {
                this.difficultyLevel = difficultyLevel;
                return true;
            }

        return false;
    }
}
