package nl.overnightprojects.kids_in_space;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nvanbenschoten.motion.ParallaxImageView;

import static nl.overnightprojects.kids_in_space.MainActivity.musicVolumeDivider;
import static nl.overnightprojects.kids_in_space.MainActivity.timeExtra;

public class SplashScreenActivity extends AppCompatActivity {

    private TextView title;
    private ParallaxImageView background, asteroids;

    private MediaPlayer intro_mediaPlayer;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        prepSound();

        background = findViewById(R.id.iv_background);
        background.setImageResource(R.drawable.spacefog);

        asteroids = findViewById(R.id.iv_asteroids);
        asteroids.setImageResource(R.drawable.asteroids);

        registerParallax();

        title = findViewById(R.id.tv_title);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/ralewayblack.ttf");
        title.setTypeface(custom_font);

        YoYo.with(Techniques.SlideInUp)
                .duration(1800)
                .repeat(0)
                .playOn(findViewById(R.id.iv_logo));

        final Handler h1 = new Handler();
        h1.postDelayed(new Runnable() {
            @Override
            public void run(){
                intro_mediaPlayer.start();
                title.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInUp)
                        .duration(2000)
                        .repeat(0)
                        .playOn(findViewById(R.id.tv_title));
            }
        }, 200);

        final Handler h2 = new Handler();
        h2.postDelayed(new Runnable() {
            @Override
            public void run(){
                goToEmptyActivity();
            }
        }, 3000);
    }

    private void prepSound() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        intro_mediaPlayer = MediaPlayer.create(this, R.raw.swoosh);

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume/ musicVolumeDivider, 0);

        intro_mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        intro_mediaPlayer.setLooping(false);
    }

    private void goToEmptyActivity(){
        Intent i = new Intent(this, EmptyActivity.class);
        unRegisterParallax();
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    private void registerParallax(){
        background.registerSensorManager();
        asteroids.registerSensorManager();
    }

    private void unRegisterParallax(){
        background.unregisterSensorManager();
        asteroids.unregisterSensorManager();
    }

    public void onResume(){
        registerParallax();
        super.onResume();
    }

    public void onPause(){
        unRegisterParallax();
        super.onPause();
    }

    public void onBackPressed(){
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
