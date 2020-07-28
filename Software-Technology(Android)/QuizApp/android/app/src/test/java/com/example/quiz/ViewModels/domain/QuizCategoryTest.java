package com.example.quiz.ViewModels.domain;



import com.example.quiz.domain.QuizCategory;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuizCategoryTest {

    @Test
    public void testSetCategory() {
        QuizCategory quizCategory = new QuizCategory();

        assertTrue(quizCategory.setCategory("Sports"));
        assertFalse(quizCategory.setCategory("Art"));

        quizCategory = new QuizCategory("History");
        assertNotNull(quizCategory.getCategory());

        quizCategory = new QuizCategory("Economy");
        assertNull(quizCategory.getCategory());
    }
}
