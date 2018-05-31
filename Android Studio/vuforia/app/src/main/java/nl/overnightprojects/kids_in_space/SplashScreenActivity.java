package nl.overnightprojects.kids_in_space;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nvanbenschoten.motion.ParallaxImageView;

public class SplashScreenActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private TextView title;
    private ParallaxImageView background, asteroids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        prepSound();

        background = findViewById(R.id.iv_background);
        background.registerSensorManager();
        background.setImageResource(R.drawable.spacefog);

        asteroids = findViewById(R.id.iv_asteroids);
        asteroids.registerSensorManager();
        asteroids.setImageResource(R.drawable.asteroids);

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
                mediaPlayer.start();
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
                goToMainActivity();
            }
        }, 3000);
    }

    private void prepSound(){
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        mediaPlayer = MediaPlayer.create(this, R.raw.swoosh);

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume/2, 0);

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);
    }

    private void goToMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("showSplash", false);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
