package nl.overnightprojects.kids_in_space;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nvanbenschoten.motion.ParallaxImageView;

import java.util.Objects;

public class StudentLoginActivity extends AppCompatActivity {

    private String classToLaunch, classToLaunchPackage;

    private TextView student_login_title;
    private EditText student_code;
    private Button student_connect;

    private ParallaxImageView background, asteroids;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initFireBase();

        background = findViewById(R.id.iv_background);
        background.registerSensorManager();
        background.setImageResource(R.drawable.spacefog);

        asteroids = findViewById(R.id.iv_asteroids);
        asteroids.registerSensorManager();
        asteroids.setImageResource(R.drawable.asteroids);

        student_login_title = findViewById(R.id.tv_student_login_title);
        student_code = findViewById(R.id.et_student_code);
        student_connect = findViewById(R.id.btn_student_connect);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/ralewayblack.ttf");
        student_login_title.setTypeface(custom_font);
        student_code.setTypeface(custom_font);
        student_connect.setTypeface(custom_font);

        classToLaunchPackage = getPackageName();
        classToLaunch = classToLaunchPackage + "."
                + "ImageTargets.ImageTargets";

        student_connect.setOnClickListener(new View.OnClickListener() {
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

                if(Objects.equals(student_code.getText().toString(), code)){
                    goToARActivity();
                }
                else if(student_code.getText().toString().length() != 5){
                    Toast.makeText(StudentLoginActivity.this, "The code is too short. Please enter 5 digits. Try again.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(StudentLoginActivity.this, "The code " + student_code.getText().toString() + " is incorrect. Try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void goToARActivity(){
        Intent i = new Intent();
        i.setClassName(classToLaunchPackage, classToLaunch);
        startActivity(i);
    }
}
