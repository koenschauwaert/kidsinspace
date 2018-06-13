package nl.overnightprojects.kids_in_space;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nvanbenschoten.motion.ParallaxImageView;

import java.util.Random;

import es.dmoral.toasty.Toasty;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    public static int vibeLength = 10;
    public static int numberOfPlanets = 6; // planets + asteroid = 6
    public static int timeNow = 0;
    public static int timeShort = 150;
    public static int timeNormal = 300;
    public static int timeExtra = 400;
    public static int musicVolumeDivider = 2;
    // USE THIS DEVIDER TO DEVIDE 100% OF VOLUME TO (WHEN musicVolumeDivider == 2) 50%.
    // PLEASE TRY TO MAKE POSSIBLE TO DISABLE SOUND AS WELL. THIS WAS FEEDBACK WE RECEIVED

    private Boolean restartAR = false;
    private Boolean showSplash = true;
    private Boolean firstStart = true;
    private Boolean zoomBackIn = false;
    private int markerID;
    private int numberOfButtons = 3;
    private String classToLaunch;
    private String classToLaunchPackage;

    private Button teacher, student, demo;
    private ParallaxImageView background, asteroids, satellite;
    private GifImageView mercury, venus, moon, earth, mars;

    private MediaPlayer click_mediaPlayer;
    private AudioManager audioManager;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    private Vibrator vibe;

    // TODO: CLEAN UP THE UNUSED VALUES ABOVE AND IN OTHER ACTIVITIES AS WELL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        settings = getSharedPreferences("preferences",
                Context.MODE_PRIVATE);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        prepSound();

        mercury = findViewById(R.id.giv_mercury);
        venus = findViewById(R.id.giv_venus);
        moon = findViewById(R.id.giv_moon);
        earth = findViewById(R.id.giv_earth);
        mars = findViewById(R.id.giv_mars);

        fadeOutAllPlanetsAndButtons(timeNow);

        background = findViewById(R.id.iv_background);
        background.setImageResource(R.drawable.spacefog);
        asteroids = findViewById(R.id.iv_asteroids);
        asteroids.setImageResource(R.drawable.asteroids);
        satellite = findViewById(R.id.iv_satellite);
        satellite.setImageResource(R.drawable.satellite);

        registerParallax();

        teacher = findViewById(R.id.btn_teacher);
        student = findViewById(R.id.btn_student);
        demo = findViewById(R.id.btn_demo);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/ralewayblack.ttf");
        teacher.setTypeface(custom_font);
        student.setTypeface(custom_font);
        demo.setTypeface(custom_font);

        fadeInAllPlanetsAndButtons();

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
                if (click_mediaPlayer.isPlaying()) {
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.btn_teacher);
                final Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run(){
                        goToTeacherActivity();
                    }
                }, timeNormal);
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click_mediaPlayer.isPlaying()) {
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.btn_student);
                final Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run(){
                        goToStudentActivity();
                    }
                }, timeNormal);
            }
        });

        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click_mediaPlayer.isPlaying()) {
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.btn_demo);
                final Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run(){
                        goToDemoActivity();
                    }
                }, timeNormal);
            }
        });

        mercury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatePlanet(R.id.giv_mercury);
            }
        });

        venus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatePlanet(R.id.giv_venus);
            }
        });

        moon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatePlanet(R.id.giv_moon);
            }
        });

        earth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatePlanet(R.id.giv_earth);
            }
        });

        mars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatePlanet(R.id.giv_mars);
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

    private void prepSound(){
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        click_mediaPlayer = MediaPlayer.create(this, R.raw.pop);

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume/ musicVolumeDivider, 0);

        click_mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        click_mediaPlayer.setLooping(false);
    }

    private void animatePlanet(int id){
        String anim;

        Random r = new Random();
        int time = r.nextInt(1500) + 500;

        Random p = new Random();
        int index = p.nextInt(5);

        switch (index) {
            case 0:
                anim = "ZoomOut";
                break;
            case 1:
                anim = "Tada";
                break;
            case 2:
                anim = "Pulse";
                break;
            case 3:
                anim = "Bounce";
                break;
            case 4:
                anim = "Shake";
                break;
            default:
                anim = "Shake";
                break;
        }

        YoYo.with(Techniques.valueOf(anim))
                .duration(time)
                .repeat(0)
                .playOn(findViewById(id));
    }

    private void wobbleButton(int id){
        YoYo.with(Techniques.Bounce)
                .duration(timeShort)
                .repeat(0)
                .playOn(findViewById(id));
    }

    private void fadeOutAllPlanetsAndButtons(int timer){
        YoYo.with(Techniques.FadeOut)
                .duration(timer)
                .repeat(0)
                .playOn(findViewById(R.id.i_planets_background));

        YoYo.with(Techniques.SlideOutDown)
                .duration(timer)
                .repeat(0)
                .playOn(findViewById(R.id.i_satellite_background));

        YoYo.with(Techniques.TakingOff)
                .duration(timer)
                .repeat(0)
                .playOn(findViewById(R.id.i_asteroids_background));
    }

    private void fadeInAllPlanetsAndButtons(){
        YoYo.with(Techniques.FadeIn)
                .duration(timeNormal)
                .repeat(0)
                .playOn(findViewById(R.id.i_planets_background));

        YoYo.with(Techniques.SlideInDown)
                .duration(timeNormal)
                .repeat(0)
                .playOn(findViewById(R.id.i_satellite_background));

        YoYo.with(Techniques.Landing)
                .duration(timeNormal)
                .repeat(0)
                .playOn(findViewById(R.id.i_asteroids_background));
    }

    private void initFireBase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    private void goToTeacherActivity(){
        Intent i = new Intent(this, TeacherLoginActivity.class);
        unRegisterParallax();
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void goToStudentActivity(){
        Intent i = new Intent(this, StudentLoginActivity.class);
        unRegisterParallax();
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void goToDemoActivity(){
        Intent i = new Intent(this, DemoActivity.class);
        unRegisterParallax();
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void goToSplashScreenActivity(){
        Intent i = new Intent(this, SplashScreenActivity.class);
        unRegisterParallax();
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    private void showToast(String text, String message){
        if(message.equals("error")){
            Toasty.error(this, text).show();
        } else if(message.equals("success")){
            Toasty.success(this, text).show();
        } else if (message.equals("info")) {
            Toasty.info(this, text).show();
        } else if (message.equals("warning")){
            Toasty.warning(this, text).show();
        } else {
            Toasty.normal(this, text).show();
        }
    }

    private void registerParallax(){
        background.registerSensorManager();
        asteroids.registerSensorManager();
        satellite.registerSensorManager();
    }

    private void unRegisterParallax(){
        background.unregisterSensorManager();
        asteroids.unregisterSensorManager();
        satellite.unregisterSensorManager();
    }

    public void onResume(){
        registerParallax();
        fadeInAllPlanetsAndButtons();
        super.onResume();
    }

    public void onPause(){
        unRegisterParallax();
        fadeOutAllPlanetsAndButtons(timeNow);
        super.onPause();
    }

    public void onBackPressed(){
        click_mediaPlayer.start();
        fadeOutAllPlanetsAndButtons(timeNormal);
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run(){
                unRegisterParallax();
                finish();
            }
        }, timeExtra);
    }
}
