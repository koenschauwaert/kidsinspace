package nl.overnightprojects.kids_in_space;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Boolean showSplash = true;
    private String classToLaunch;
    private String classToLaunchPackage;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            showSplash = extras.getBoolean("showSplash");
        }

        if(showSplash) {
            showSplashScreen();
        }

        classToLaunchPackage = getPackageName();
        classToLaunch = classToLaunchPackage + "."
                + "ImageTargets.ImageTargets";

        if(!showSplash) {
            getPermissions();
        }

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToARActivity();
            }
        });
    }

    private void showSplashScreen(){
        goToSplashScreenActivity();
    }

    private void getPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
    }

    private void goToARActivity(){
        Intent i = new Intent();
        i.setClassName(classToLaunchPackage, classToLaunch);
        startActivity(i);
    }

    private void goToSplashScreenActivity(){
        Intent i = new Intent(this, SplashScreenActivity.class);
        startActivity(i);
        finish();
    }
}
