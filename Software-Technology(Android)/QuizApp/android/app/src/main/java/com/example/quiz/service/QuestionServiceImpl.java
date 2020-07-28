package com.example.quiz.service;

import android.os.AsyncTask;

import com.example.quiz.domain.DifficultyLevel;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.QuizCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class QuestionServiceImpl implements QuestionService {


    String url;
    int questionId;
    String category;
    String difficultyLvl;
    ArrayList<Question> questions;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficultyLvl() {
        return difficultyLvl;
    }

    @Override
    public void setDifficultyLvl(String difficultyLvl) {
        this.difficultyLvl = difficultyLvl;
    }

    @Override
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /**
     * Λαμβάνει από το API τις ερωτήσεις για το quiz
     */
    @Override
    public void questionReader() {

        AsyncQuestionReader asyncQuestionReader = new AsyncQuestionReader();
        try {
            setQuestions(asyncQuestionReader.execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    /**
     * Async class που πάει και παίρνει τις ερωτήσεις απο το REST API
     */
    private class AsyncQuestionReader extends AsyncTask<Void, Void, ArrayList<Question>> {


        @Override
        protected ArrayList doInBackground(Void... voids) {

            ArrayList<Question> questions = new ArrayList<>();
            url = "https://opentdb.com/api.php?amount=20&category=" + category + "&difficulty=" + difficultyLvl + "&type=multiple";

            if(url.trim().length() == 0)
                return null;

            try {

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                System.out.println("\nSending 'GET' request to URL : " + url);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }

                bufferedReader.close();

                JSONObject myResponse = new JSONObject(response.toString());
                JSONArray json = myResponse.getJSONArray("results");

                String category = json.getJSONObject(0).getString("category");
                String level = json.getJSONObject(0).getString("difficulty");

                QuizCategory quizCategory = new QuizCategory(category);
                DifficultyLevel difficultyLevel = new DifficultyLevel(level);

                for (int i = 0; i < json.length(); i++) {
                    JSONObject questionObj = json.getJSONObject(i);

                    String question = questionObj.getString("question");
                    String correctAnswer = questionObj.getString("correct_answer");
                    JSONArray incorrectAnswers = questionObj.getJSONArray("incorrect_answers");
                    ArrayList<String> incorrectAnswersArrayList = new ArrayList<>();

                    for(int j = 0; j < incorrectAnswers.length(); j++){
                        incorrectAnswersArrayList.add(incorrectAnswers.getString(j));
                    }

                    Question q = new Question(questionId+i, quizCategory, difficultyLevel, question, correctAnswer, incorrectAnswersArrayList);

                    questions.add(q);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return questions;
        }
    }
}
