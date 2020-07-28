package com.example.quiz.ViewModels.domain;


import com.example.quiz.domain.DateTime;

import org.junit.Test;
import static org.junit.Assert.*;

public class DateTimeTest {

    @Test
    public void testCurrentDate() {
        DateTime dateTime = new DateTime();
        dateTime.setCurrentDate();

        assertNotEquals("23-3-2020-20-32-28",dateTime.getDateTime());
    }
}
