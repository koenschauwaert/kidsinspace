package nl.overnightprojects.kids_in_space;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class StudentLoginActivity extends Activity {

    private EditText codeInput;
    private Button connectButton;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initFireBase();

        codeInput = findViewById(R.id.edittext_connect_code);
        connectButton = findViewById(R.id.button_connect_code);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCodeWithFireBase();
            }
        });
    }

    private void initFireBase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Code");
    }

    private void checkCodeWithFireBase(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String code = dataSnapshot.child("Code").getValue(String.class);

                if(Objects.equals(codeInput.getText().toString(), code)){
                    goToVRActivity();
                }
                else if(codeInput.getText().toString().length() != 5){
                    Toast.makeText(StudentLoginActivity.this, "The code is too short. Please enter 5 digits. Try again.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(StudentLoginActivity.this, "The code " + codeInput.getText().toString() + " is incorrect. Try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void goToVRActivity(){
        Intent i = new Intent(this, ViroActivityAR.class);
        startActivity(i);
    }
}
