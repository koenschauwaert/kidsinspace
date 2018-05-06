package nl.overnightprojects.kids_in_space;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run(){
                goToMainActivity();
            }
        }, 1000);
    }

    private void goToMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("showSplash", false);
        startActivity(i);
        finish();
    }
}
