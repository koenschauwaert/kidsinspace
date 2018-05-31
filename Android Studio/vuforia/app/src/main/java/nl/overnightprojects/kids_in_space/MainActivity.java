package nl.overnightprojects.kids_in_space;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nvanbenschoten.motion.ParallaxImageView;

import java.io.IOException;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private Boolean restartAR = false, showSplash = true, firstStart = true;
    private int markerID;
    private String classToLaunch;
    private String classToLaunchPackage;
    private Button teacher, student, demo;

    private ParallaxImageView background, asteroids;
    private GifImageView mercury, venus, moon, earth, mars;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        settings = getSharedPreferences("preferences",
                Context.MODE_PRIVATE);

        //Bitmap background_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spacefog);

        mercury = findViewById(R.id.giv_mercury);
        venus = findViewById(R.id.giv_venus);
        moon = findViewById(R.id.giv_moon);
        earth = findViewById(R.id.giv_earth);
        mars = findViewById(R.id.giv_mars);

        background = findViewById(R.id.iv_background);
        background.registerSensorManager();
        background.setImageResource(R.drawable.spacefog);

        asteroids = findViewById(R.id.iv_asteroids);
        asteroids.registerSensorManager();
        asteroids.setImageResource(R.drawable.asteroids);

        teacher = findViewById(R.id.btn_teacher);
        student = findViewById(R.id.btn_student);
        demo = findViewById(R.id.btn_demo);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/ralewayblack.ttf");
        teacher.setTypeface(custom_font);
        student.setTypeface(custom_font);
        demo.setTypeface(custom_font);

        initFireBase();

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            showSplash = extras.getBoolean("showSplash");
        }

        if(showSplash) {
            showSplashScreen();
        }

        if(!showSplash) {
            getPermissions();
        }

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTeacherActivity();
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStudentActivity();
            }
        });

        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDemoActivity();
            }
        });

        mars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Shake)
                        .duration(1800)
                        .repeat(0)
                        .playOn(findViewById(R.id.giv_mars));
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

    private void goToTeacherActivity(){
        Intent i = new Intent(this, TeacherLoginActivity.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void goToStudentActivity(){
        Intent i = new Intent(this, StudentLoginActivity.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void goToDemoActivity(){
        Intent i = new Intent(this, DemoActivity.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void goToSplashScreenActivity(){
        Intent i = new Intent(this, SplashScreenActivity.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    private void initFireBase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }
}
