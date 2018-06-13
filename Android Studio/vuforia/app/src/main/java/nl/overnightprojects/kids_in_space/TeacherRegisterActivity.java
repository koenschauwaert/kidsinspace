package nl.overnightprojects.kids_in_space;

import android.content.Context;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

import static nl.overnightprojects.kids_in_space.MainActivity.musicVolumeDivider;
import static nl.overnightprojects.kids_in_space.MainActivity.timeExtra;
import static nl.overnightprojects.kids_in_space.MainActivity.timeNormal;
import static nl.overnightprojects.kids_in_space.MainActivity.timeNow;
import static nl.overnightprojects.kids_in_space.MainActivity.timeShort;
import static nl.overnightprojects.kids_in_space.MainActivity.vibeLength;

public class TeacherRegisterActivity extends AppCompatActivity {

    private TextView teacher_register_title;
    private EditText register_email, register_password, register_code;
    private Button teacher_register;

    private ParallaxImageView background, asteroids, satellite;

    private MediaPlayer error_mediaPlayer, click_mediaPlayer;
    private AudioManager audioManager;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

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

        teacher_register_title = findViewById(R.id.tv_teacher_register_title);
        register_email = findViewById(R.id.et_teacher_register_email);
        register_password = findViewById(R.id.et_teacher_register_password);
        register_code = findViewById(R.id.et_teacher_register_code);
        teacher_register = findViewById(R.id.btn_teacher_register);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/ralewayblack.ttf");
        teacher_register_title.setTypeface(custom_font);
        register_email.setTypeface(custom_font);
        register_password.setTypeface(custom_font);
        register_code.setTypeface(custom_font);
        teacher_register.setTypeface(custom_font);

        fadeInAllPlanetsAndButtons();

        initFireBase();

        teacher_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click_mediaPlayer.isPlaying()) {
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.btn_teacher_register);
                if(isEmailValid(register_email.getText().toString())) {
                    checkRegisterCodeWithFireBase();
                }
                else{
                    showToast("Please enter a valid email address", "error");
                }
            }
        });
    }

    private void prepSound() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        error_mediaPlayer = MediaPlayer.create(this, R.raw.errorsound);
        click_mediaPlayer = MediaPlayer.create(this, R.raw.pop);

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume / musicVolumeDivider, 0);

        error_mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        error_mediaPlayer.setLooping(false);
        click_mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        click_mediaPlayer.setLooping(false);
    }

    private void wobbleButton(int id) {
        YoYo.with(Techniques.Bounce)
                .duration(timeShort)
                .repeat(0)
                .playOn(findViewById(id));
    }

    private void fadeOutAllPlanetsAndButtons(int timer) {
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

    private void fadeInAllPlanetsAndButtons() {
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

    // CHECK IF EMAIL IS A VALID ONE WHEN REGISTERING
    private boolean isEmailValid(String email){
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    private void initFireBase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    private void checkRegisterCodeWithFireBase() {
        databaseReference = firebaseDatabase.getReference("Code");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String code = dataSnapshot.child("RegisterCode").getValue(String.class);
                String email = register_email.getText().toString();
                String password = register_password.getText().toString();
                String input_code = register_code.getText().toString();

                if (Objects.equals(input_code, code)) {
                    registerTeacherToFireBase(email, password, input_code);
                } else {
                    showToast("Code incorrect. Please try again", "error");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void registerTeacherToFireBase(final String email, final String password, final String input_code){
        databaseReference = firebaseDatabase.getReference("Teacher");
        databaseReference.child("TeacherEmail").setValue(email);
        databaseReference.child("TeacherPassword").setValue(password);

        deteleRegisterCodeFromFireBase();
    }

    private void deteleRegisterCodeFromFireBase(){
        databaseReference = firebaseDatabase.getReference("Code").child("RegisterCode");
        databaseReference.removeValue();

        showToast("Registered successfully", "success");

        onBackPressed();
    }

    private void showToast(String text, String message) {
        if (message.equals("error")) {
            error_mediaPlayer.start();
            Toasty.error(this, text).show();
        } else if (message.equals("success")) {
            Toasty.success(this, text).show();
        } else if (message.equals("info")) {
            Toasty.info(this, text).show();
        } else if (message.equals("warning")) {
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

    public void onResume() {
        registerParallax();
        fadeInAllPlanetsAndButtons();
        super.onResume();
    }

    public void onPause() {
        unRegisterParallax();
        fadeOutAllPlanetsAndButtons(timeNow);
        super.onPause();
    }

    public void onBackPressed() {
        click_mediaPlayer.start();
        fadeOutAllPlanetsAndButtons(timeNormal);
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                unRegisterParallax();
                finish();
            }
        }, timeExtra);
    }
}
