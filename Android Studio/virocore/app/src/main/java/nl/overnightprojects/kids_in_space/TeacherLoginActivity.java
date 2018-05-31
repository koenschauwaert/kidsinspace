package nl.overnightprojects.kids_in_space;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class TeacherLoginActivity extends Activity{

    private EditText codeTeacher, codeMarker;
    private Button connectButton;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initFireBase();

        codeTeacher = findViewById(R.id.edittext_teacher_code);
        codeMarker = findViewById(R.id.edittext_marker_code);
        connectButton = findViewById(R.id.button_teacher_code);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTeacherCodeWithFireBase();
            }
        });
    }

    private void initFireBase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Code");
    }

    private void checkTeacherCodeWithFireBase(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String code = dataSnapshot.child("TeacherCode").getValue(String.class);

                if(Objects.equals(codeTeacher.getText().toString(), code)){
                    writeMarkerCodeToFireBase();
                }
                else{
                    Toast.makeText(TeacherLoginActivity.this, "The teacher code is incorrect. Try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void writeMarkerCodeToFireBase(){
        int marker = Integer.valueOf(codeMarker.getText().toString());
        if(marker >= 0 && marker <= 4) {
            databaseReference.child("NR").setValue(marker);
        }
        else{
            Toast.makeText(this, "Marker code not accepted! Try 0, 1, 2, 3 or 4.", Toast.LENGTH_SHORT).show();
        }
    }
}