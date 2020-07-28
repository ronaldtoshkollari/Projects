package com.example.quiz.domain;

public class QuizCategory {

    private String category;

    public static String[] quizCategories = new String[]{"History","Sports","Geography","Politics"};

    /**
     * Κατασκευαστής για την κατηγορία
     */
    public QuizCategory () {
        category = null;
    }

    /**
     * Κατασκευαστής για μία συγκεκριμένη κατηγορία
     * @param category Η κατηγορία
     */
    public QuizCategory(String category) {
        if (!this.setCategory(category))
            this.category = null;
    }

    /**
     * Επιστρέφει την κατηγορία
     * @return Η κατηγορία
     */
    public String getCategory() {
        return category;
    }

    /**
     * Θέτει την κατηγορία
     * @param category Η κατηγορία
     */
    public boolean setCategory(String category) {
        for (String quizCategory: quizCategories)
            if (quizCategory.equals(category)) {
                this.category = category;
                return true;
            }

        return false;
    }
}
