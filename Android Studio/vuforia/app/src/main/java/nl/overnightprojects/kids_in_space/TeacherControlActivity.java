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
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

import static nl.overnightprojects.kids_in_space.MainActivity.musicVolumeDivider;
import static nl.overnightprojects.kids_in_space.MainActivity.timeExtra;
import static nl.overnightprojects.kids_in_space.MainActivity.timeNormal;
import static nl.overnightprojects.kids_in_space.MainActivity.timeNow;
import static nl.overnightprojects.kids_in_space.MainActivity.timeShort;
import static nl.overnightprojects.kids_in_space.MainActivity.vibeLength;

public class TeacherControlActivity extends AppCompatActivity {

    private boolean connectToDataBase = true;

    private String classToLaunch, classToLaunchPackage;

    private TextView teacher_control_title, room_number, current_marker;
    private Button marker_00, marker_01, marker_02, marker_03, marker_04, marker_05, marker_06, marker_07;

    private MediaPlayer click_mediaPlayer;
    private AudioManager audioManager;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_control);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        prepSound();

        fadeOutAllPlanetsAndButtons(timeNow);

        teacher_control_title = findViewById(R.id.tv_teacher_control_title);
        room_number = findViewById(R.id.tv_room_number);
        current_marker = findViewById(R.id.tv_current_marker);
        marker_00 = findViewById(R.id.btn_marker_00);
        marker_01 = findViewById(R.id.btn_marker_01);
        marker_02 = findViewById(R.id.btn_marker_02);
        marker_03 = findViewById(R.id.btn_marker_03);
        marker_04 = findViewById(R.id.btn_marker_04);
        marker_05 = findViewById(R.id.btn_marker_05);
        marker_06 = findViewById(R.id.btn_marker_06);
        marker_07 = findViewById(R.id.btn_marker_07);

        marker_00.setText("-- Disable --"); // markerID 0
        marker_01.setText("1. Moonbase (AR)"); // markerID 1
        marker_02.setText("2. Ellion (GIF)"); // markerID 2
        marker_03.setText("3. Balloon (AR)"); // markerID 3
        marker_04.setText("4. Textbook (AR)"); // markerID 4
        marker_05.setText("5. Planets (AR)"); // markerID 5
        marker_06.setText("6. Rocket (GIF)"); // markerID 6
        marker_07.setText("7. Mentimeter (URL)"); // markerID 7

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/ralewayblack.ttf");
        teacher_control_title.setTypeface(custom_font);
        room_number.setTypeface(custom_font);
        current_marker.setTypeface(custom_font);
        marker_00.setTypeface(custom_font);
        marker_01.setTypeface(custom_font);
        marker_02.setTypeface(custom_font);
        marker_03.setTypeface(custom_font);
        marker_04.setTypeface(custom_font);
        marker_05.setTypeface(custom_font);
        marker_06.setTypeface(custom_font);
        marker_07.setTypeface(custom_font);

        classToLaunchPackage = getPackageName();
        classToLaunch = classToLaunchPackage + "."
                + "ImageTargets.ImageTargets";

        fadeInAllPlanetsAndButtons();

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            connectToDataBase = extras.getBoolean("connectToDataBase");
        }
        if(connectToDataBase) {
            initFireBase();
            getCodeFromFireBase();
            getCurrentMarkerFromFireBase();
        }
        else{
            marker_00.setVisibility(View.GONE);
            room_number.setVisibility(View.GONE);
        }

        // FOR DEMO: NO NEED TO DISABLE THE MARKERS/AR/IMAGES
        if(connectToDataBase) {
            marker_00.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (click_mediaPlayer.isPlaying()) {
                        click_mediaPlayer.seekTo(0);
                    }
                    click_mediaPlayer.start();
                    vibe.vibrate(vibeLength);
                    wobbleButton(R.id.btn_marker_00);
                    writeMarkerToFireBase(0);
                }
            });
        }

        marker_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click_mediaPlayer.isPlaying()){
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.btn_marker_01);
                if(connectToDataBase) {
                    writeMarkerToFireBase(1);
                }
                else{
                    goToARActivity(1);
                }
            }
        });

        marker_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click_mediaPlayer.isPlaying()){
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.btn_marker_02);
                if(connectToDataBase) {
                    writeMarkerToFireBase(2);
                }
                else{
                    goToIMGActivity(2);
                }
            }
        });

        marker_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click_mediaPlayer.isPlaying()){
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.btn_marker_03);
                if(connectToDataBase) {
                    writeMarkerToFireBase(3);
                }
                else{
                    goToARActivity(3);
                }
            }
        });

        marker_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click_mediaPlayer.isPlaying()){
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.btn_marker_04);
                if(connectToDataBase) {
                    writeMarkerToFireBase(4);
                }
                else{
                    goToARActivity(4);
                }
            }
        });

        marker_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click_mediaPlayer.isPlaying()){
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.btn_marker_05);
                if(connectToDataBase) {
                    writeMarkerToFireBase(5);
                }
                else{
                    goToARActivity(5);
                }
            }
        });

        marker_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click_mediaPlayer.isPlaying()){
                    click_mediaPlayer.seekTo(0);
                }
                click_mediaPlayer.start();
                vibe.vibrate(vibeLength);
                wobbleButton(R.id.btn_marker_06);
                if(connectToDataBase) {
                    writeMarkerToFireBase(6);
                }
                else{
                    goToIMGActivity(6);
                }
            }
        });

        // FOR DEMO: NO NEED TO FILL IN MENTI.COM
        if(connectToDataBase) {
            marker_07.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (click_mediaPlayer.isPlaying()) {
                        click_mediaPlayer.seekTo(0);
                    }
                    click_mediaPlayer.start();
                    vibe.vibrate(vibeLength);
                    wobbleButton(R.id.btn_marker_07);
                    writeMarkerToFireBase(7);
                }
            });
        }
        else{
            marker_07.setVisibility(View.GONE);
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
                .playOn(findViewById(R.id.i_satellite_background_no_parallax));

        YoYo.with(Techniques.TakingOff)
                .duration(timer)
                .repeat(0)
                .playOn(findViewById(R.id.i_asteroids_background_no_parallax));
    }

    private void fadeInAllPlanetsAndButtons(){
        YoYo.with(Techniques.FadeIn)
                .duration(timeNormal)
                .repeat(0)
                .playOn(findViewById(R.id.i_planets_background_still));

        YoYo.with(Techniques.SlideInDown)
                .duration(timeNormal)
                .repeat(0)
                .playOn(findViewById(R.id.i_satellite_background_no_parallax));

        YoYo.with(Techniques.Landing)
                .duration(timeNormal)
                .repeat(0)
                .playOn(findViewById(R.id.i_asteroids_background_no_parallax));
    }

    private void initFireBase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    private void getCodeFromFireBase(){
        databaseReference = firebaseDatabase.getReference("Code");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String room = dataSnapshot.child("Room").getValue(String.class);
                room_number.setText("CODE " + room);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getCurrentMarkerFromFireBase(){
        databaseReference = firebaseDatabase.getReference("CurrentMarker");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String curmarker = dataSnapshot.child("NR").getValue(String.class);
                current_marker.setText("ID " + curmarker);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void writeMarkerToFireBase(final int id){
        databaseReference = firebaseDatabase.getReference("CurrentMarker");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String id_string = Integer.toString(id);
                current_marker.setText("ID " + id_string);

                databaseReference.child("NR").setValue(id_string);

                showToast("Marker ID '" + id_string + "' written to Database", "success");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void goToIMGActivity(int id){
        String markerID = Integer.toString(id);
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("cameFromDemo", true);
        i.putExtra("imgID", markerID);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void goToARActivity(int id){
        String markerID = Integer.toString(id);
        Intent i = new Intent();
        i.setClassName(classToLaunchPackage, classToLaunch);
        i.putExtra("cameFromDemo", true);
        i.putExtra("markerID", markerID);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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

    public void onResume(){
        fadeInAllPlanetsAndButtons();
        super.onResume();
    }

    public void onPause(){
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
                finish();
            }
        }, timeExtra);
    }
}