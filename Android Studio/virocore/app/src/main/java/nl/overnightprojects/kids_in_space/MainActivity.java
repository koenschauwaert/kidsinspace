package nl.overnightprojects.kids_in_space;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button studentButton, teacherButton, demoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        studentButton = findViewById(R.id.btnStudent);
        teacherButton = findViewById(R.id.btnTeacher);
        //demoButton = findViewById(R.id.btnDemo);

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStudentLogin();
            }
        });

        teacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTeacherLogin();
            }
        });

         /*
        demoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
    }

    private void goToStudentLogin(){
        Intent i = new Intent(this, StudentLoginActivity.class);
        startActivity(i);
    }

    private void goToTeacherLogin(){
        Intent i = new Intent(this, TeacherLoginActivity.class);
        startActivity(i);
    }
}
