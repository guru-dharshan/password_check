package com.example.registration_check;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextInputEditText pass,email;
    TextInputLayout pass1;
    Button sign_button;
  ImageView imgchar,imgcaps,imgspecial,imgnumber;
    private  boolean isStringUpperCase(String str){

        //convert String to char array
        char[] charArray = str.toCharArray();
        boolean value=false;
        for(int i=0; i < charArray.length; i++){

            //if the character is a letter
            if( Character.isLetter(charArray[i]) ){

                //if any character is not in upper case, return false
                if( Character.isUpperCase( charArray[i] )) {

                    value = true;
                    break;
                }

            }
        }

        return value;
    }
    public boolean checkuppercase(String s){
        if(isStringUpperCase(s)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean checknumber(String s){
        if (s.toString( ).matches(".*\\d.*"))
        {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean checkspecialcase(String s){
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        Matcher match = pattern.matcher(s);
        boolean val = match.find();
        if(val){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean checklenght(String s){
        if(s.length()>6){
            return true;
        }
        else{
            return false;
        }
    }
    public void sign(View view){
        boolean pass__check =false;
        boolean mail__check =false;
        String text_pass=pass.getText().toString();
        String text_mail=email.getText().toString();
            if(checklenght(text_pass) && checknumber(text_pass) && checkspecialcase(text_pass) && checkuppercase(text_pass)){
                pass__check=true;
            }
            else{
                pass1.setPasswordVisibilityToggleEnabled(false);
                pass.setError("Please satisfy all the condition");
            }
            if(text_mail.contains("@")){
                mail__check=true;
                email.setError(null);

            }
            else{
                email.setError("provie a valid mail");
            }
            if(mail__check && pass__check){
                Toast.makeText(this, "you signed up succesfully!", Toast.LENGTH_SHORT).show( );
            }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pass=findViewById(R.id.pass);
        imgchar=findViewById(R.id.imgcharacter);
        imgcaps=findViewById(R.id.imguppercase);
        imgspecial=findViewById(R.id.imgspecial);
        imgnumber=findViewById(R.id.imgnumber);
        email=findViewById(R.id.email);
        pass1=findViewById(R.id.pass1);
        sign_button=findViewById(R.id.button2);
        email.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    email.setError("Field can't be empty");
                }
                else{
                    email.setError(null);
                }

            }
        });
        pass.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    pass1.setPasswordVisibilityToggleEnabled(false);
                    pass.setError("Field can't be empty");
                }
                else{
                    pass1.setPasswordVisibilityToggleEnabled(true);
                    pass.setError(null);
                }
                if(checklenght(s.toString( ))){
                    imgchar.setImageResource(R.drawable.ic_baseline_check_circle_green);
                }
                else{
                    imgchar.setImageResource(R.drawable.ic_baseline_check_circle_24);
                }
                if(checknumber(s.toString( ))){
                    imgnumber.setImageResource(R.drawable.ic_baseline_check_circle_green);
                }
                else{
                    imgnumber.setImageResource(R.drawable.ic_baseline_check_circle_24);
                }
                if(checkspecialcase(s.toString( ))){
                    imgspecial.setImageResource(R.drawable.ic_baseline_check_circle_green);
                }
                else{
                    imgspecial.setImageResource(R.drawable.ic_baseline_check_circle_24);
                }
                if(checkuppercase(s.toString())){
                    imgcaps.setImageResource(R.drawable.ic_baseline_check_circle_green);
                }
                else{
                    imgcaps.setImageResource(R.drawable.ic_baseline_check_circle_24);
                }
            }
        });
    }


}