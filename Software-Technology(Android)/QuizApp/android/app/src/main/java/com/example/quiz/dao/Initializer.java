package com.example.quiz.dao;

import com.example.quiz.domain.Answer;
import com.example.quiz.domain.DateTime;
import com.example.quiz.domain.DifficultyLevel;
import com.example.quiz.domain.Player;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.Quiz;
import com.example.quiz.domain.QuizCategory;
import com.example.quiz.domain.QuizChallenge;
import com.example.quiz.domain.QuizParticipation;

import java.util.ArrayList;

/**
 * Κλάση που αναλαμβάνει να αρχικοποιήσει τα δεδομένα της βάσης δεδομένων<p>
 * Βοηθητική κλάση που παρέχει δεδομένα για τα παραδείγματα και τις δοκιμασίες ελέγχου
 */

public abstract class Initializer {

    protected abstract void eraseData();


    public void prepareData(){

        eraseData();


        //Adding some Players
        PlayerDao playerDao = getPlayerDAO();

        playerDao.save(new Player(playerDao.nextId(), "roni", "roni", "roni@gmail.com", "Ronaldo", "Toshkollari", "Male", new QuizCategory("Sports")));
        playerDao.save(new Player(playerDao.nextId(), "stelios", "stelios", "stelios@gmail.com", "Stelios", "Kokokyris", "Male", new QuizCategory("Politics")));
        playerDao.save(new Player(playerDao.nextId(), "dimitris", "dimitris", "dimitris@gmail.com", "Dimitris", "Bekiaris", "Male", new QuizCategory("History")));
        playerDao.save(new Player(playerDao.nextId(), "test", "test", "test@gmail.com", "Test", "Testt", "Female", new QuizCategory("Geography")));

        //Adding Some Questions
        ArrayList<String> wrongAnswers = new ArrayList<>();
        wrongAnswers.add("wrong1");
        wrongAnswers.add("wrong2");
        wrongAnswers.add("wrong3");
        wrongAnswers.add("wrong4");
        QuestionDAO questionDAO = getQuestionDAO();
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "Which is the best team in Greece?", "AEK", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "Which is the worst team in Greece?", "Olympiakos", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "Which English football club has the nickname The Foxes?", "Leicester City", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "How many soccer players should be on the field at the same time?", "22", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "In baseball, how many fouls are an out?", "0", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "The Rio 2016 Summer Olympics held it's closing ceremony on what date?", "August 21", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "Which country is hosting the 2018 FIFA World Cup?", "Russia", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "What was the final score of the Germany vs. Brazil 2014 FIFA World Cup match?", "7 - 1", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "Which city did the former NHL teamThe Nordiques originiate from?", "Quebec City", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "What year did the New Orleans Saints win the Super Bowl?", "2010", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "Which team has won the most Stanley Cups in the NHL?", "Montreal Canadians", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "When was the first official international game played?", "1872", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "What is the most common type of pitch thrown by pitchers in baseball?", "Fastball", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "What team did England beat to win in the 1966 World Cup final?", "West Germany", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "In bowling, what is the term used for getting three consecutive strikes?", "Turkey", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "Which player holds the NHL record of 2,857 points?", "Wayne Gretzky", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "What is the name of Manchester United's home stadium?", "Old Trafford", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "Who won the premier league title in the 2015-2016 season following a fairy tale run?", "Leicester City", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "Who did Steven Gerrard win the Champions League with?", "Liverpool", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Sports"), new DifficultyLevel("Easy"), "The Los Angeles Dodgers were originally from what U.S. city?", "Brooklyn", wrongAnswers));

        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "What is the largest city and commercial capital of Sri Lanka?", "Colombo", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "Which of these countries is NOT a part of the Asian continent?", "Suriname", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "Which of these is NOT a city in India?", "Islamabad", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "The prefix Sino- (As in Sino-American) is used to refer to what nationality?", "Chinese", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "Which of these is NOT a province in China? ", "Yangtze", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "What is the name of one of the Neo-Aramaic languages spoken by the Jewish population from Northwestern Iraq?", "Lishana Deni", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "In 2012 the German-speaking microstate Liechtenstein in Central Europe had a population of how many inhabitants? ", "36,600", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "Where is the Luxor Hotel Casino located? ", "Paradise, Nevada", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "What year is on the flag of the US state Wisconsin? ", "1848", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "The Hunua Ranges is located in... ", "New Zealand", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "What is the Finnish word for Finland? ", "Suomi", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "What is the name of rocky region that spans most of eastern Canada?", "Canadian Shield", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "What is the tallest mountain in Canada?", "Mount Logan", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "What is the capital city of Bermuda?", "Hamilton", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "What is the most common climbing route for the second highest mountain in the world, K2?", "Abruzzi Spur", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "The emblem on the flag of the Republic of Tajikistan features a sunrise over mountains below what symbol?", "Crown", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "Which country is the Taedong River in?", "North Korea", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "What is the capital of Wisconsin, USA?", "Madison", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "Which of these is NOT a real tectonic plate?", "Atlantic Plate", wrongAnswers));
        questionDAO.save(new Question(questionDAO.nextId(), new QuizCategory("Geography"), new DifficultyLevel("Hard"), "Which of these is NOT a located in Attica, Greece? ", "Kalamata", wrongAnswers));



        //Making Some Challenges
        QuizChallengeDAO quizChallengeDAO = getQuizChallengeDAO();
        quizChallengeDAO.save(new QuizChallenge(quizChallengeDAO.nextId(), new Quiz(), playerDao.find("roni"), playerDao.find("stelios"), false, new DateTime(), new DifficultyLevel("Easy"), new QuizCategory("Sports")));
        quizChallengeDAO.save(new QuizChallenge(quizChallengeDAO.nextId(), new Quiz(), playerDao.find("stelios"), playerDao.find("dimitris"), false, new DateTime(), new DifficultyLevel("Hard"), new QuizCategory("Geography")));

        //Making some Quiz
        QuizDAO quizDAO = getQuizDAO();
        quizDAO.save(new Quiz(quizDAO.nextId(), new DateTime(), 0, new ArrayList<Question>()));
        quizDAO.save(new Quiz(quizDAO.nextId(), new DateTime(), 0, new ArrayList<Question>()));

        //linking challenges with quiz
        quizChallengeDAO.find(1).setQuiz(quizDAO.find(1));
        quizChallengeDAO.find(2).setQuiz(quizDAO.find(2));

        //adding the questions in quiz
        int sportCounter = 0;
        for (Question question : questionDAO.findAll()){

            if(sportCounter < 20){
                quizDAO.find(1).getQuestions().add(question);
                sportCounter++;
            }else{
                quizDAO.find(2).getQuestions().add(question);
            }//end if
        }//end for

        //Creating Answer
        AnswerDAO answerDAO = getAnswerDAO();
        //Sports
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(1), "AEK", true));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(2), "PAO", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(3), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(4), "22", true));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(5), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(6), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(7), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(8), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(9), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(10), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(11), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(12), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(13), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(14), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(15), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(16), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(17), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(18), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(19), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(20), "AEK", false));

        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(1), "PAOK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(2), "PAO", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(3), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(4), "22", true));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(5), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(6), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(7), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(8), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(9), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(10), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(11), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(12), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(13), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(14), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(15), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(16), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(17), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(18), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(19), "AEK", false));
        answerDAO.save(new Answer(answerDAO.nextId(), questionDAO.find(20), "AEK", false));

        //linking questions with QuizParticipation
        QuizParticipationDAO quizParticipationDAO = getQuizParticipationDAO();
        quizParticipationDAO.save(new QuizParticipation(quizParticipationDAO.nextId(), quizDAO.find(1), playerDao.find("roni"), new ArrayList<Answer>(), 350, 1, true));
        quizParticipationDAO.save(new QuizParticipation(quizParticipationDAO.nextId(), quizDAO.find(1), playerDao.find("stelios"), new ArrayList<Answer>(), 350, 2, false));

        int count = 0;
        for (Answer answer : answerDAO.findAll()) {
            if (count <= 20) {
                quizParticipationDAO.find(1).getAnswers().add(answer);
                count++;
            } else {
                quizParticipationDAO.find(1).getAnswers().add(answer);
            }//end of if
        }//end of for

        //setting 2nd quizParticipation

    }//end of prepareData


    public abstract PlayerDao getPlayerDAO();

    public abstract AnswerDAO getAnswerDAO();

    public abstract QuizDAO getQuizDAO();

    public abstract QuestionDAO getQuestionDAO();

    public abstract  QuizChallengeDAO getQuizChallengeDAO();

    public abstract QuizParticipationDAO getQuizParticipationDAO();


}
