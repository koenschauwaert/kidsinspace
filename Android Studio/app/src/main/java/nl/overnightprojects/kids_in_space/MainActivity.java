package nl.overnightprojects.kids_in_space;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String classToLaunch;
    private String classToLaunchPackage;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classToLaunchPackage = getPackageName();
        classToLaunch = classToLaunchPackage + "."
                + "ImageTargets.ImageTargets";

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity();
            }
        });
    }

    private void startNewActivity(){
        Intent i = new Intent();
        i.setClassName(classToLaunchPackage, classToLaunch);
        startActivity(i);
    }
}
