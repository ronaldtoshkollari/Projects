package com.example.smartcook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    //XML elements
    private ImageButton mImageBtn;
    private Button mManualBtn;
    private TextView voice_instructions;


    private TextToSpeech mTTS;
    private float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //connect xml elements with java code
        mImageBtn = findViewById(R.id.mic_button);
        mManualBtn = findViewById(R.id.manual_button);
        voice_instructions = findViewById(R.id.voice_instructions);

        //show speech-to-text
        mImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                talk();
            }
        });

        //manual_button
        mManualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(); //when we click the button it opens a new activity, in this new activity we enter manual instructions
            }
        });
    }//end of onCreate()

    private void speak(){
        String text = voice_instructions.getText().toString();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        else
            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }//end of speak()



    private void talk() {
        //try to show STT
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
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

                    if(result.get(0).contains("open microwave")){
                        startActivity(new Intent(this, VoiceInstuctions.class));
                    }

                }
                break;
            }

        }
    }//end of onActivityResult()


    //we open manual Activity with this method
    public void openActivity(){

        Intent intent = new Intent(this, manual.class);
        startActivity(intent);

    }//end of openActivity()

    @Override
    protected void onPause() {
        if(mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }


        super.onPause();
    }//end of onPause()

    @Override
    protected void onDestroy() {

        if(mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }//end of onDestroy()


    @Override
    protected void onResume() {

        //--Start of Text to speech--
        //init text to speech
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status == TextToSpeech.SUCCESS){
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(MainActivity.this, "This language is not supported", Toast.LENGTH_SHORT).show();
                    }else{
                        mTTS.setPitch(0.6f);
                        mTTS.setSpeechRate(1.0f);
                        speak();
                    }
                }

            }
        });
        //end of Text to speech

        super.onResume();
    }//end of onResume()


    public boolean onTouchEvent(MotionEvent touchEvent){
        switch (touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 > x2){
                    talk();
                }
                break;
        }
        return false;
    }//end of onTouchEvent()


}//end of MainActivity class
