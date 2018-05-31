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

public class TeacherLoginActivity extends AppCompatActivity {

    private TextView teacher_login_title;
    private EditText login_password, login_email;
    private Button teacher_connect;

    private ParallaxImageView background, asteroids;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initFireBase();

        background = findViewById(R.id.iv_background);
        background.registerSensorManager();
        background.setImageResource(R.drawable.spacefog);

        asteroids = findViewById(R.id.iv_asteroids);
        asteroids.registerSensorManager();
        asteroids.setImageResource(R.drawable.asteroids);

        teacher_login_title = findViewById(R.id.tv_teacher_login_title);
        login_email = findViewById(R.id.et_teacher_login_email);
        login_password = findViewById(R.id.et_teacher_login_password);
        teacher_connect = findViewById(R.id.btn_teacher_connect);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/ralewayblack.ttf");
        teacher_login_title.setTypeface(custom_font);
        login_email.setTypeface(custom_font);
        login_password.setTypeface(custom_font);
        teacher_connect.setTypeface(custom_font);

        teacher_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTeacherCodeWithFireBase();
            }
        });
    }

    private void initFireBase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Code");
    }

    private void checkTeacherCodeWithFireBase(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.child("TeacherEmail").getValue(String.class);
                String password = dataSnapshot.child("TeacherPassword").getValue(String.class);

                if((Objects.equals(login_email.getText().toString(), email)) && (Objects.equals(login_password.getText().toString(), password))){
                    goToTeacherActivity();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void goToTeacherActivity(){

    }
}
