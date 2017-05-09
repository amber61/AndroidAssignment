/* File: SpritesApplication.java
 * Course Name: CST8277_300
 * Student Name: Zhe Huang, Shunbiao Lin, Yingmei Zhu, Fasheng Zhang
 * Professor: Stanley Pieda
 * Reference: revised based on the starter code provided by Stanley Pieda
 * Description: Entry of application includes REST request web information
 * Date: April 15, 2017
 */
package com.enterpriseandroid.restfulsprites;

import android.app.Application;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Entry of application includes REST request web information
 *
 * @author Modified by Zhe Huang created on 4/15/2017.
 * @version 1.0.0
 * @see android.app.Application
 * @see android.content.SharedPreferences
 * @see android.net.Uri
 * @see android.preference.PreferenceManager
 * @see android.util.Log
 * @since 1.8.0_73
 */
public class SpritesApplication extends Application
    implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private static final String TAG = "APP";
    // HTTP receive REST URI - server ip part
    private static final String EMULATOR_HOST_IP = "10.70.238.240"; // Server IP
    private static final String APPENGINE_HOST = "your_app_id_here.appspot.com";
    private static final String LOCAL_APPENGINE_HOST = EMULATOR_HOST_IP;
    // HTTP receive REST URI - resources part
    private static final String SPRING_SERVICE = "/SpriteEE-war/webresources";
    private static final String SPRING_SYNC_SERVICE = "/springSyncServiceSprites";
    private static final String AWS_SERVICE = "/awsServiceSprites";
    private static final String APP_ENGINE_SERVICE = ""; // no app context for appspot.com
    // Http port number
    private static final String TEST_PORT = "8080";
    private static final String HTTP_DEFAULT_PORT = ""; // will be 80
    // HTTP receive REST URI - enterprise bean part
    private static final String SPRITES = "/business.sprite";

    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    private static final String PROTOCOL = HTTP; // Protocol
    private static final String HOST = EMULATOR_HOST_IP;
    private static final String PORT = TEST_PORT;
    private static final String SERVICE = SPRING_SERVICE;


    // Warning: whatever value you use here will become *persistent*
    // on first run, when it gets saved as a preference below in
    // getApiUri.
    private static final String DEFAULT_API_ROOT =
            PROTOCOL + "://" + HOST + ":" + PORT + SERVICE + SPRITES;

    private String keyApiRoot;
    private Uri apiRootUri;

    public SpritesApplication() {
        /**
         * Disable the persistent connection since We cannot get it work using HttpURLConnection.
         */
        System.setProperty("http.keepAlive", "false");

    }

    
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) { Log.d(TAG, "Application up!"); }

        keyApiRoot = getString(R.string.prefs_url_key);

        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public synchronized void onSharedPreferenceChanged(
        SharedPreferences prefs,
        String key)
    {
        apiRootUri = null;
    }

    public Uri getApiUri() {
        synchronized (this) {
            if (null == apiRootUri) {
               // Using a preference allows setting configuration of this
               // value - but it also makes the value persistent.  Please
               // keep this in mind if you can the url using the variables
               // above.
               apiRootUri = Uri.parse(
                   PreferenceManager.getDefaultSharedPreferences(this)
                       .getString(keyApiRoot, DEFAULT_API_ROOT));
            }
            return apiRootUri;
        }
    }
}
