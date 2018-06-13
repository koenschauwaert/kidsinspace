package nl.overnightprojects.kids_in_space;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nvanbenschoten.motion.ParallaxImageView;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

import static nl.overnightprojects.kids_in_space.MainActivity.musicVolumeDivider;
import static nl.overnightprojects.kids_in_space.MainActivity.timeExtra;
import static nl.overnightprojects.kids_in_space.MainActivity.timeNormal;
import static nl.overnightprojects.kids_in_space.MainActivity.timeNow;
import static nl.overnightprojects.kids_in_space.MainActivity.timeShort;
import static nl.overnightprojects.kids_in_space.MainActivity.vibeLength;

public class TeacherLoginActivity extends AppCompatActivity {

    private TextView teacher_login_title, go_to_teacher_register;
    private EditText login_email, login_password;
    private Button teacher_connect;

    private ParallaxImageView background, asteroids, satellite;

    private MediaPlayer error_mediaPlayer, click_mediaPlayer;
    private AudioManager audioManager;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        prepSound();

        fadeOutAllPlanetsAndButtons(timeNow);

        background = findViewById(R.id.iv_background);
        background.setImageResource(R.drawable.spacefog);
        asteroids = findViewById(R.id.iv_asteroids);
        asteroids.setImageResource(R.drawable.asteroids);
        satellite = findViewById(R.id.iv_satellite);
        satellite.setImageResource(R.drawable.satellite);

        registerParallax();

        teacher_login_title = findViewById(R.id.tv_teacher_login_title);
        login_email = findViewById(R.id.et_teacher_login_email);
        login_password = findViewById(R.id.et_teacher_login_password);
        teacher_connect = findViewById(R.id.btn_teacher_connect);
        go_to_teacher_register = findViewById(R.id.tv_go_to_teacher_register);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/ralewayblack.ttf");
        teacher_login_title.setTypeface(custom_font);
        login_email.setTypeface(custom_font);
        login_password.setTypeface(custom_font);
        teacher_connect.setTypeface(custom_font);
        go_to_teacher_register.setTypeface(custom_font);

        fadeInAllPlanetsAndButtons();

        initFireBase();

        teacher_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click_mediaPlayer.isPlaying()){
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.btn_teacher_connect);
                checkTeacherCodeWithFireBase();
            }
        });

        go_to_teacher_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click_mediaPlayer.isPlaying()){
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.tv_go_to_teacher_register);
                goToTeacherRegisterActivity();
            }
        });
    }

    private void prepSound(){
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        error_mediaPlayer = MediaPlayer.create(this, R.raw.errorsound);
        click_mediaPlayer = MediaPlayer.create(this, R.raw.pop);

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume/ musicVolumeDivider, 0);

        error_mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        error_mediaPlayer.setLooping(false);
        click_mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        click_mediaPlayer.setLooping(false);
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
                .playOn(findViewById(R.id.i_planets_background_still));

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
                .playOn(findViewById(R.id.i_planets_background_still));

        YoYo.with(Techniques.SlideInDown)
                .duration(timeNormal)
                .repeat(0)
                .playOn(findViewById(R.id.i_satellite_background));

        YoYo.with(Techniques.Landing)
                .duration(timeNormal)
                .repeat(0)
                .playOn(findViewById(R.id.i_asteroids_background));
    }

    private void initFireBase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Teacher");
    }

    private void checkTeacherCodeWithFireBase(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.child("TeacherEmail").getValue(String.class);
                String password = dataSnapshot.child("TeacherPassword").getValue(String.class);

                if((Objects.equals(login_email.getText().toString(), email)) && (Objects.equals(login_password.getText().toString(), password))){
                    goToTeacherControlActivity();
                }
                else if(Objects.equals(login_email.getText().toString(), email)) {
                    showToast("Wrong password. Please try again", "error");
                }
                else{
                    showToast("Account not found. Please register", "warning");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void goToTeacherControlActivity(){
        Intent i = new Intent(this, TeacherControlActivity.class);
        unRegisterParallax();
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void goToTeacherRegisterActivity(){
        Intent i = new Intent(this, TeacherRegisterActivity.class);
        unRegisterParallax();
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void showToast(String text, String message){
        if(message.equals("error")){
            error_mediaPlayer.start();
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
