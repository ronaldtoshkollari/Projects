package com.example.quiz.domain;

import java.util.Calendar;

public class DateTime implements Comparable<DateTime> {

    public int day;
    public int month;
    public int year;
    public int hour;
    public int minutes;
    public int seconds;

    /**
     * Κατασκευαστής που ορίζει την τωρινή ημερομηνία
     */
    public DateTime() {
        this.setCurrentDate();
    }


    /**
     * Κατασκεαστής για να οριστεί η ημερομηνία
     * @param day Η ημέρα
     * @param month Ο μήνας
     * @param year Το έτος
     * @param hour Η ώρα
     * @param minutes Τα λεπτά
     * @param seconds Τα δευτερόλεπτα
     */
    public DateTime(int day, int month, int year, int hour, int minutes, int seconds) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;
    }


    /**
     * Επιστρέφει την ημερομηνία
     * @return Την ημερομηνία
     */
    public String getDateTime() {
        return day + "-" + month + "-" + year + "-" + hour + "-" + minutes + "-" + seconds;
    }

    /**
     * Συγκρίνει 2 ημερομηνίες
     * @param o Η ημερομηνία
     * @return Αποτέλεσμα της compareTo
     */
    @Override
    public int compareTo(DateTime o) {
        return (year + "/" + month + "/" + day + "/" + hour + "/" + minutes + "/" + seconds).compareTo(o.year + "/" + o.month + "/" + o.day + o.hour + "/" + o.minutes + "/" + o.seconds);
    }


    /**
     * Ορίζει την τωρινή ημερομηνία
     */
    public void setCurrentDate() {
        Calendar calendar = Calendar.getInstance();

        this.day = calendar.get(Calendar.DATE);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.year = calendar.get(Calendar.YEAR);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minutes = calendar.get(Calendar.MINUTE);
        this.seconds = calendar.get(Calendar.SECOND);
    }
}
