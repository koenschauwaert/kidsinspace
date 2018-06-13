package nl.overnightprojects.kids_in_space;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.nvanbenschoten.motion.ParallaxImageView;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

import static nl.overnightprojects.kids_in_space.MainActivity.musicVolumeDivider;
import static nl.overnightprojects.kids_in_space.MainActivity.vibeLength;

public class EmptyActivity extends AppCompatActivity {

    private boolean restartAR = false;
    private String classToLaunch, classToLaunchPackage, markerID;

    private ParallaxImageView background, asteroids;

    private MediaPlayer dialog_mediaPlayer, click_mediaPlayer;
    private AudioManager audioManager;

    private Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            restartAR = extras.getBoolean("restartAR");
            markerID = extras.getString("markerID");
        }

        classToLaunchPackage = getPackageName();
        classToLaunch = classToLaunchPackage + "."
                + "ImageTargets.ImageTargets";

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        prepSound();

        background = findViewById(R.id.iv_background);
        background.setImageResource(R.drawable.spacefog);
        asteroids = findViewById(R.id.iv_asteroids);
        asteroids.setImageResource(R.drawable.asteroids);

        registerParallax();

        if(restartAR){
            goToARActivity(markerID);
        }
        else {
            showShutAllAppsDialog();
        }
    }

    private void prepSound() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        dialog_mediaPlayer = MediaPlayer.create(this, R.raw.dialog);
        click_mediaPlayer = MediaPlayer.create(this, R.raw.pop);

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume/ musicVolumeDivider, 0);

        dialog_mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        dialog_mediaPlayer.setLooping(false);
        click_mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        click_mediaPlayer.setLooping(false);
    }

    private void showShutAllAppsDialog(){
        final CharSequence[] items = {" I understand and I did"};
        // arraylist to keep the selected items
        final ArrayList selectedItems = new ArrayList();

        AlertDialog dialog = new AlertDialog.Builder(this)
                // CANNOT CANCEL DIALOG BY CLICKING OUT OF DIALOG BOX
                .setCancelable(false)
                .setTitle("Please close all background apps now")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        playButtonClickSound();
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            selectedItems.add(indexSelected);
                        } else if (selectedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            selectedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                }).setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        playButtonClickSound();
                        if(!selectedItems.isEmpty()){
                            goToMainActivity();
                        }
                        else{
                            showToast("Please close all background apps and check 'I understand and I did' to continue", "error");
                            showShutAllAppsDialog();
                        }
                        //  Your code when user clicked on OK
                        //  You can write the code  to save the selected item here
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel
                        killApp();
                    }
                }).create();
        dialog.show();

        dialog_mediaPlayer.start();
    }

    private void playButtonClickSound(){
        if(click_mediaPlayer.isPlaying()){
            click_mediaPlayer.seekTo(0);
        }
        click_mediaPlayer.start();
        vibe.vibrate(vibeLength);
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
    }

    private void unRegisterParallax(){
        background.unregisterSensorManager();
        asteroids.unregisterSensorManager();
    }

    private void killApp(){
        unRegisterParallax();
        finish();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private void goToMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        unRegisterParallax();
        i.putExtra("showSplash", false);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    private void goToARActivity(String id) {
        Intent i = new Intent();
        unRegisterParallax();
        i.setClassName(classToLaunchPackage, classToLaunch);
        i.putExtra("markerID", id);
        startActivity(i);
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
        super.onBackPressed();
        killApp();
    }
}
