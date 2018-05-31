package nl.overnightprojects.kids_in_space;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nvanbenschoten.motion.ParallaxImageView;

public class DemoActivity extends AppCompatActivity {

    private String classToLaunch, classToLaunchPackage;

    private ParallaxImageView background, asteroids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        background = findViewById(R.id.iv_background);
        background.registerSensorManager();
        background.setImageResource(R.drawable.spacefog);

        asteroids = findViewById(R.id.iv_asteroids);
        asteroids.registerSensorManager();
        asteroids.setImageResource(R.drawable.asteroids);

        classToLaunchPackage = getPackageName();
        classToLaunch = classToLaunchPackage + "."
                + "ImageTargets.ImageTargets";




    }

    private void goToARActivity(){
        Intent i = new Intent();
        i.setClassName(classToLaunchPackage, classToLaunch);
        i.putExtra("cameFromDemo", true);
        startActivity(i);
    }
}
