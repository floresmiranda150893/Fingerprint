package mx.com.example.fingerprint;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fingerprint fingerprint = Fingerprint.getInstance(this, new IFingerprint() {
            @Override
            public void onAuthenticationSucceeded() {

            }

            @Override
            public void onAuthenticationFailed() {

            }
        });

        Log.e("Fingerprint", "" + fingerprint.isAvailable());

        Alert();

    }

    public void Alert(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.layout_fingerprint);
        //dialog.getWindow().setDimAmount(0.0f);

        dialog.setCancelable(false);

        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();



        ((ViewGroup)dialog.getWindow().getDecorView()).getChildAt(0)
                .startAnimation(AnimationUtils.loadAnimation(this,R.anim.show_alert));
    }
}
