package nl.overnightprojects.kids_in_space;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import pl.droidsonroids.gif.GifImageView;

import static nl.overnightprojects.kids_in_space.MainActivity.musicVolumeDivider;
import static nl.overnightprojects.kids_in_space.MainActivity.vibeLength;

public class ImageActivity extends AppCompatActivity {

    private boolean cameFromDemo;
    private int markerID;
    private String imgIDString;

    private ConstraintLayout root;
    private GifImageView show_image;

    private MediaPlayer ellion_speech, rocket_launch, click_mediaPlayer;
    private AudioManager audioManager;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private DisplayMetrics displayMetrics;

    private Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        displayMetrics = new DisplayMetrics();

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        prepSound();

        root = findViewById(R.id.cl_root);
        show_image = findViewById(R.id.giv_show_image);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            cameFromDemo = extras.getBoolean("cameFromDemo");
            imgIDString = extras.getString("imgID");
            if(cameFromDemo) {
                showRightImage(imgIDString);
            }
            else{
                initFireBase();
                getCodeContinuousFromFireBase(imgIDString);
            }
        }
        else{
            finish();
        }
    }

    private void prepSound(){
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        ellion_speech = MediaPlayer.create(this, R.raw.ellionspeech);
        rocket_launch = MediaPlayer.create(this, R.raw.swoosh);
        click_mediaPlayer = MediaPlayer.create(this, R.raw.pop);

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume/ musicVolumeDivider, 0);

        ellion_speech.setAudioStreamType(AudioManager.STREAM_MUSIC);
        ellion_speech.setLooping(false);
        rocket_launch.setAudioStreamType(AudioManager.STREAM_MUSIC);
        rocket_launch.setLooping(false);
        click_mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        click_mediaPlayer.setLooping(false);
    }

    private int getDisplayWidth(){
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

    private int getDisplayHeight(){
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        return height;
    }

    private void initFireBase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    private void getCodeContinuousFromFireBase(final String oldID){
        databaseReference = firebaseDatabase.getReference("CurrentMarker");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String id = dataSnapshot.child("NR").getValue(String.class);
                markerID = Integer.parseInt(id);
                //Toast.makeText(ImageActivity.this, "ID: " + markerID, Toast.LENGTH_SHORT).show();

                if(!Objects.equals(id, oldID)) {
                    finish();
                }
                else{
                    showRightImage(imgIDString);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showRightImage(String id){
        int index = Integer.valueOf(id);
        switch (index){
            case 0:
                // SHOWING NOTHING
                finish();
                break;
            case 1:
                // SHOWING AR INSTEAD OF IMG
                finish();
                break;
            case 2:
                showToast("Tap on me to hear me speak", "info");
                show_image.getLayoutParams().width = getDisplayWidth();
                show_image.getLayoutParams().height = getDisplayWidth();

                root.setBackgroundColor(getResources().getColor(R.color.background_ellion));
                show_image.setBackgroundResource(R.drawable.ellionoptimize);

                show_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ellion_speech.isPlaying()) {
                            showToast("Please let me finish", "warning");
                        }
                        else {
                            ellion_speech.start();
                        }
                    }
                });
                break;
            case 3:
                // SHOWING AR INSTEAD OF IMG
                finish();
                break;
            case 4:
                // SHOWING AR INSTEAD OF IMG
                finish();
                break;
            case 5:
                // SHOWING AR INSTEAD OF IMG
                finish();
                break;
            case 6:
                show_image.getLayoutParams().width = getDisplayWidth();
                show_image.getLayoutParams().height = getDisplayWidth();

                root.setBackgroundColor(getResources().getColor(R.color.background_rocket));
                show_image.setBackgroundResource(R.drawable.rocketoptimize);

                show_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rocket_launch.isPlaying()) {
                            showToast("Still taking off", "info");
                        }
                        else {
                            rocket_launch.start();
                        }
                    }
                });
                break;
            case 7:
                show_image.setBackgroundResource(R.drawable.gotomenti);
                show_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(click_mediaPlayer.isPlaying()){
                            click_mediaPlayer.seekTo(0);
                        }
                        click_mediaPlayer.start();
                        vibe.vibrate(vibeLength);
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.menti.com/8e567ae7"));
                        startActivity(i);
                    }
                });
                break;
        }
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
}
