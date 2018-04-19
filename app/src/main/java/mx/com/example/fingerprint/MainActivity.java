package mx.com.example.fingerprint;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Activity context;

    private Fingerprint fingerprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        fingerprint = Fingerprint.getInstance(this, new IFingerprint() {
            @Override
            public boolean showAlert() {
                return true;
            }

            @Override
            public void onAuthenticationStart() {

            }

            @Override
            public void onAuthenticationSucceeded() {

                startActivity(new Intent(context, Main2Activity.class));
                finish();

            }

            @Override
            public void onAuthenticationFailed() {

            }

            @Override
            public void onAuthenticationHelp(String string) {

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
