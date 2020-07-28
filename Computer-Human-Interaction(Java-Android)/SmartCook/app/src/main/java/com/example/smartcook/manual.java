package com.example.smartcook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class manual extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Our XML elements
    private Spinner customSpinner;
    private Button set_btn;
    private EditText clock;
    private TextView timer;
    private TextView msg;
    private Button home_screen_button;
    private TextView instruction_time;
    private TextView header_time;
    private TextView instruction_power;
    private TextView header_power;
    private TextView manual_settings;

    private CountDownTimer mCountDownTimer;

    private long mTimeLeftInMillis;


    //ArrayList to store customItems for Spinner
    private ArrayList<CustomItem> customList;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_manual);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        customSpinner = findViewById(R.id.power_spinner);
        customList = getCustomList();
        CustomAdapter adapter = new CustomAdapter(this, customList);
        customSpinner.setAdapter(adapter);
        customSpinner.setOnItemSelectedListener(this);

        if(customSpinner != null){
            customSpinner.setAdapter(adapter);
            customSpinner.setOnItemSelectedListener(this);
        }

        //Before set
        set_btn = findViewById(R.id.button_set);
        clock = findViewById(R.id.edit_text_input);
        instruction_time = findViewById(R.id.instruction_time);
        header_time = findViewById(R.id.header_time);
        instruction_power = findViewById(R.id.instuction_power);
        header_power = findViewById(R.id.header_power);
        manual_settings = findViewById(R.id.manual_settings);



        //after set
        timer = findViewById(R.id.timer);
        msg = findViewById(R.id.msg);
        home_screen_button = findViewById(R.id.home_screen_button);

        set_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    if(Integer.parseInt(clock.getText().toString()) > 0 && Integer.parseInt(clock.getText().toString()) <= 90){

                        mTimeLeftInMillis = Long.parseLong(clock.getText().toString()) * 60000;
                        closeKeyboard();

                        set_btn.setVisibility(View.INVISIBLE);
                        clock.setVisibility(View.INVISIBLE);
                        instruction_time.setVisibility(View.INVISIBLE);
                        header_time.setVisibility(View.INVISIBLE);
                        instruction_power.setVisibility(View.INVISIBLE);
                        header_power.setVisibility(View.INVISIBLE);
                        customSpinner.setVisibility(View.INVISIBLE);
                        manual_settings.setVisibility(View.INVISIBLE);

                        timer.setVisibility(View.VISIBLE);
                        startTimer();

                    }else{
                        Toast.makeText(manual.this,"Please enter a valid number", Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    Toast.makeText(manual.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }




            }
        });

        home_screen_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }//end of onCreate()


    //This method is used to close keyboard when Set button is pressed
    public void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imn = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }//end of closeKeyboard()


    //CountDown Methods
    public void startTimer(){
        mCountDownTimer = new CountDownTimer(Long.parseLong(clock.getText().toString())*60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timer.setVisibility(View.INVISIBLE);
                msg.setVisibility(View.VISIBLE);
                home_screen_button.setVisibility(View.VISIBLE);
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

        timer.setText(timeLeftFormatted);

    }//end of updateCountDownText()


    //In this method we can add items to the ArrayList so that the items we added can be shown in spinner
    private ArrayList<CustomItem> getCustomList() {

        customList = new ArrayList<>();
        customList.add(new CustomItem("Low", R.drawable.low));
        customList.add(new CustomItem("Defrost", R.drawable.defrost));
        customList.add(new CustomItem("Medium", R.drawable.medium));
        customList.add(new CustomItem("High", R.drawable.high));
        return customList;


    }//end of getCustomList()

    //We override this method. So every time we select an option from spinner the items will be selected and a toast message will be appear showing us a message with the option we selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        CustomItem item  = (CustomItem) parent.getSelectedItem();

    }//end of onItemSelected()

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }//end of onNothingSelected()

}//end of manual
