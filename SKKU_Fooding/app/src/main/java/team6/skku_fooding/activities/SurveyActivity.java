package team6.skku_fooding.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import team6.skku_fooding.R;

public class SurveyActivity extends AppCompatActivity {


    Intent intent;
    Button next_button;
    RadioGroup radioGroup;
    RadioButton radioButton;
    int checked;
    String UID;

    @Override
    public void onBackPressed(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        Intent intent = getIntent();
        SharedPreferences loginPref;
        loginPref = getSharedPreferences("user_SP", this.MODE_PRIVATE);
        Button button_next =findViewById(R.id.taste_next_btn);
        radioGroup = findViewById(R.id.radio_group);


        String temp=loginPref.getString("UID",null);
        if (temp == null) temp = getIntent().getStringExtra("UID");
        if (temp == null) {
            Toast.makeText(this, "Wrong UID found. Re-login or Re-sign-up!", Toast.LENGTH_LONG).show();
            finish();
        }
        Log.d("sakfjlaskf",""+ temp);

        /* 죄송합니다....8ㅅ8
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width= dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.9),(int)(height*0.85));
        */

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checked = radioGroup.indexOfChild(findViewById(checkedId));
            }
        });
        String finalTemp = temp;
        button_next.setOnClickListener((v) -> {
            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton=findViewById(radioId);
            if(checked==0){
               UID= finalTemp;
                startSurvey();
            }
            if(checked==1){
               UID= finalTemp;
                startSurvey2();
            }
            if(checked==2){
              UID= finalTemp;
                startSurvey3();
            }
        });
    }
    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
    }
    private void startSurvey(){
        Intent intent =new Intent(SurveyActivity.this,SurveyActivity_Spicy.class);
        intent.putExtra("UID",UID);
        startActivity(intent);
        finish();
    }

    private void startSurvey2(){
        Intent intent =new Intent(SurveyActivity.this,SurveyActivity_Sweet.class);
        intent.putExtra("UID",UID);
        startActivity(intent);
        finish();
    }
    private void startSurvey3(){
        Intent intent =new Intent(SurveyActivity.this,SurveyActivity_Salty.class);
        intent.putExtra("UID",UID);
        startActivity(intent);
        finish();
    }

}

