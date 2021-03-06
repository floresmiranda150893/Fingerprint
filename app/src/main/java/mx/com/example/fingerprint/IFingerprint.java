package mx.com.example.fingerprint;

/**
 * Created by Chris on 17/04/2018.
 */

public interface IFingerprint {

    boolean showAlert();

    void onAuthenticationStart();

    void onAuthenticationSucceeded();

    void onAuthenticationFailed();

    void onAuthenticationHelp(String message);

}
