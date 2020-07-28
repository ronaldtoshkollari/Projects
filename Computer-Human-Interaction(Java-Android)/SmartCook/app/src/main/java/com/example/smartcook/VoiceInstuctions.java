package com.example.smartcook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

public class VoiceInstuctions extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;

    //Voice instructions
    private final String vPower = "Please select power between low, defrost, medium, high";
    private final String vTime = "Please select cook time max time is one hour and thirty minutes";
    private final String vSubmit = "You said ";
    private final String vSetting = "You have successfully set the microwave." ;
    private final String vDone = "Your meal is ready, enjoy!";
    private final String vGuide = "Tap everywhere on the screen to start cooking and if you want to exit say exit.";
    private final String vClock = "The clock just started";
    private final String vCorrect = "Is that correct?";


    //microwave variables
    private int hours = 0;
    private int minutes = 0;
    private String power;
    private TextToSpeech mTTS;

    //XML elements
    private ImageButton imgBtn;
    private int temp;
    private TextView vTimer;

    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_instuctions);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        temp = 0;
        imgBtn = findViewById(R.id.imgBtn);
        vTimer = findViewById(R.id.vTimer);
        initializeTextToSpeech(vGuide);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp++;
                if(temp%2 == 1){
                    if(temp < 2){
                        initializeTextToSpeech(vPower);
                    }else if(temp < 4) {
                        initializeTextToSpeech(vTime);
                    }else if(temp < 6){
                        initializeTextToSpeech(vSetting);
                    }else{
                        finish();
                    }
                }else{
                    if(temp < 6){
                        talk();
                    }else{

                        initializeTextToSpeech(vClock);
                        mTimeLeftInMillis = ((hours * 60) + minutes)*60000;
                        imgBtn.setVisibility(View.INVISIBLE);
                        vTimer.setVisibility(View.VISIBLE);

                        startTimer();

                    }
                }
            }

        });



    }//end of onCreate();



    private void initializeTextToSpeech(final String text) {


        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(mTTS.getEngines().size() == 0){
                    Toast.makeText(VoiceInstuctions.this, "TTS is not supported!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{

                    mTTS.setLanguage(Locale.ENGLISH);
                    speak(text);

                }
            }
        });
    }//end of initializeTextToSpeech()

    private void speak(String text) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        else
            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }//speak()

    private void talk() {
        //try to show STT
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1000);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Welcome to Smart Cook.");

        //start intent
        try{
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }//end of try/catch

    } //end of speak()


    //After the google voice activity is done we save the result on an ArrayList
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT: {
                if(resultCode == RESULT_OK && data != null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    if(temp < 3){
                        if(result.get(0).toLowerCase().contains("exit")){
                            finish();
                            return;
                        }else if(result.get(0).toLowerCase().contains("low")){
                            initializeTextToSpeech(vSubmit + " low" + vCorrect);
                            temp--;
                            power = "low";
                        }else if(result.get(0).toLowerCase().contains("defrost")){
                            initializeTextToSpeech(vSubmit + " defrost" + vCorrect);
                            temp--;
                            power = "defrost";
                        }else if(result.get(0).toLowerCase().contains("medium")){
                            initializeTextToSpeech(vSubmit + " medium" + vCorrect);
                            temp--;
                            power = "medium";
                        }else if(result.get(0).toLowerCase().contains("high")){
                            initializeTextToSpeech(vSubmit + " high" + vCorrect);
                            temp--;
                            power = "high";
                        }else if(result.get(0).toLowerCase().contains("yes")){
                            //Toast.makeText(VoiceInstructions.this, "YES", Toast.LENGTH_SHORT).show();
                        }else if(result.get(0).toLowerCase().contains("no")){
                            //Toast.makeText(VoiceInstructions.this, "NO", Toast.LENGTH_SHORT).show();
                            temp -= 2;
                        }else{
                            initializeTextToSpeech("Didn't get that please repeat");
                            temp--;
                        }

                    }else if(temp > 3){
                        if(result.get(0).toLowerCase().contains("exit")){
                            finish();
                            return;
                        }else if(result.get(0).toLowerCase().contains("yes")){
                            //Toast.makeText(VoiceInstructions.this, "YES", Toast.LENGTH_SHORT).show();
                        }else if(result.get(0).toLowerCase().contains("no")){
                            //Toast.makeText(VoiceInstructions.this, "NO", Toast.LENGTH_SHORT).show();
                            temp -= 2;
                        }else{
                            StringTokenizer str_token = new StringTokenizer(result.get(0));

                            while(str_token.hasMoreTokens()){


                                //it has only minutes if user command contains lower than two words
                                if(str_token.countTokens() <= 2){

                                    String str = str_token.nextToken();

                                    if(isNumber(str) || str.equalsIgnoreCase("one")){//make a txt file which will contain all the numbers between 1 and 90 in text.

                                        if(str.equalsIgnoreCase("one")) str = "1";

                                        if(Integer.parseInt(str) > 90)
                                            break;

                                        if(Integer.parseInt(str) >= 60){
                                            hours = 1;
                                            minutes = Integer.parseInt(str) - 60;
                                        }else{

                                            if((result.get(0).toLowerCase().contains("hour") || result.get(0).toLowerCase().contains("hours")) && Integer.parseInt(str) == 1)
                                                hours = Integer.parseInt(str);
                                            else if(result.get(0).toLowerCase().contains("minutes") || result.get(0).toLowerCase().contains("minute"))
                                                minutes = Integer.parseInt(str);
                                            else
                                                break;
                                        }//end if
                                    }//end if
                                }else{
                                    String str = str_token.nextToken();
                                    if(isNumber(str) || str.equalsIgnoreCase("zero")){//make a txt file which will contain all the numbers between 1 and 90 in text.

                                        if(str.equalsIgnoreCase("zero")) str = "0";

                                        if(Integer.parseInt(str) > 1) break;
                                        else hours = Integer.parseInt(str);

                                    }//end if
                                }//end if
                            }//end while

                            if((hours == 0 && minutes == 0) || (hours == 1 && minutes > 30) || hours > 1) initializeTextToSpeech("Didn't get that please repeat");
                            else{
                                if(minutes == 1) initializeTextToSpeech(vSubmit + " " + hours + "hours and " + minutes + " minute" + vCorrect);
                                else initializeTextToSpeech(vSubmit + " " + hours + "hours and " + minutes + " minutes" + vCorrect);
                            }//end if
                            temp--;

                        }//end if

                    }//end if


                }
                break;
            }

        }
    }//end of onActivityResult()

    private boolean isNumber(String word){
        boolean isNumber = false;

        try{
            Integer.parseInt(word);
            isNumber = true;
        }catch (NumberFormatException e){
            isNumber = false;
        }
        return isNumber;

    }//end of isNumber

    //CountDown Methods
    public void startTimer(){
        mCountDownTimer = new CountDownTimer(((hours * 60) + minutes)*60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                initializeTextToSpeech(vDone + "Tap to go to home screen");
                imgBtn.setVisibility(View.VISIBLE);
                vTimer.setVisibility(View.INVISIBLE);
            }
        }.start();

    }//end of startTimer()

    //update timer TextView
    private void updateCountDownText(){

        int hours = (int) (mTimeLeftInMillis/1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted;

        if(hours > 0){
            timeLeftFormatted  = String.format(Locale.getDefault(),"%d:%02d:%02d",hours, minutes, seconds);
        }else{
            timeLeftFormatted  = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        }

        vTimer.setText(timeLeftFormatted);
    }//end of updateCountDownText()


    @Override
    protected void onPause() {
        super.onPause();
        mTTS.shutdown();
    }//end of onPause()

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTTS.shutdown();
    }//end of onDestroy()

}
