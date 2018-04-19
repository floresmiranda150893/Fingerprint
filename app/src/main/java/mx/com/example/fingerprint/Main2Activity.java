package mx.com.example.fingerprint;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private Activity context;

    private Fingerprint fingerprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        context = this;

        fingerprint = Fingerprint.getInstance(this, new IFingerprint() {
            @Override
            public boolean showAlert() {
                return false;
            }

            @Override
            public void onAuthenticationStart() {
                Toast.makeText(context,"Fingerprint", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded() {
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                Toast.makeText(context,"Fingerprint Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationHelp(String string) {
                Toast.makeText(context,"Fingerprint " + string, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();

        fingerprint.startListening();

    }

    @Override
    public void onPause(){
        super.onPause();

        fingerprint.stopListening();

    }

}
