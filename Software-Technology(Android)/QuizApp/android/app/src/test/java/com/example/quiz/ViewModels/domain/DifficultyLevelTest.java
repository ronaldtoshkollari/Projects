package com.example.quiz.ViewModels.domain;



import com.example.quiz.domain.DifficultyLevel;

import org.junit.Test;
import static org.junit.Assert.*;

public class DifficultyLevelTest {

    @Test
    public void testSetDifficultyLevel() {
        DifficultyLevel difficultyLevel = new DifficultyLevel();

        assertTrue(difficultyLevel.setDifficultyLevel("Easy"));
        assertFalse(difficultyLevel.setDifficultyLevel("Too Easy"));

        difficultyLevel = new DifficultyLevel("Medium");
        assertNotNull(difficultyLevel.getDifficultyLevel());

        difficultyLevel = new DifficultyLevel("Very Hard");
        assertNull(difficultyLevel.getDifficultyLevel());
    }
}
