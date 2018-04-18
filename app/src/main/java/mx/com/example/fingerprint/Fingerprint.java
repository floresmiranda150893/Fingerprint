package mx.com.example.fingerprint;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;

/**
 * Created by Chris on 17/04/2018.
 */

class Fingerprint {

    private static final Fingerprint ourInstance = new Fingerprint();

    private Activity context;

    private IFingerprint iFingerprint;

    private FingerprintManager fingerprintManager;

    private CancellationSignal cancellationSignal;

    private FingerprintManager.AuthenticationCallback authenticationCallback;

    private boolean isAvailable;

    public static Fingerprint getInstance(Activity context, IFingerprint iFingerprint) {

        ourInstance.context = context;
        ourInstance.iFingerprint = iFingerprint;

        return ourInstance;
    }

    @SuppressLint("NewApi")
    public void startListening() {
        if (ourInstance.isAvailable())
            ourInstance.fingerprintManager.authenticate(null, ourInstance.cancellationSignal, 0 /* flags */, ourInstance.authenticationCallback, null);

    }

    public void stopListening() {
        if (isAvailable()) {
            try {
                ourInstance.cancellationSignal.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("NewApi")
    public boolean isAvailable() {

        try{

            ourInstance.fingerprintManager = ourInstance.context.getSystemService(FingerprintManager.class);

            ourInstance.authenticationCallback = new FingerprintManager.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                }

                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    super.onAuthenticationHelp(helpCode, helpString);
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    if (ourInstance.iFingerprint != null)
                        ourInstance.iFingerprint.onAuthenticationSucceeded();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    if (ourInstance.iFingerprint != null)
                        ourInstance.iFingerprint.onAuthenticationFailed();
                }
            };

            if (ourInstance.fingerprintManager == null){
                return (isAvailable = false);
            }


            if (!ourInstance.fingerprintManager.isHardwareDetected())
                return (isAvailable = false);

            if (!ourInstance.fingerprintManager.hasEnrolledFingerprints())
                return (isAvailable = false);

            return (isAvailable = true);


        }catch (Exception ex){
            ex.printStackTrace();
            return (isAvailable = false);
        }

    }

}
