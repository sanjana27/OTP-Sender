package com.example.hello.otpsender;

import android.icu.util.TimeUnit;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText e1,e2;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback;
    String verification_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        auth=FirebaseAuth.getInstance();

        mcallback =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verification_code=s;
                Toast.makeText(getApplicationContext(), "Code sent to number", Toast.LENGTH_SHORT).show();
            }
        };}


       public void send_sms(View v)
    {
        String number =e1.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,60, java.util.concurrent.TimeUnit.SECONDS,this,mcallback
        );
    }

    public void signInwithPhone(PhoneAuthCredential credential)
    {
        auth.signInWithCredential(credential);
        //Problems faced here
        //To give message to the user on signing inl
    }


    public void verify(View v)
    {
        String Input_code=e2.getText().toString();
        if(verification_code.equals(" ")){
            verifyphonenumber(verification_code,Input_code);
        }

    }
    public void verifyphonenumber(String verifycode, String inputcode){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verifycode,inputcode);
        signInwithPhone(credential);
    }
    }

