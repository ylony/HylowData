package com.ylofanclub.hylow.hylowdata;

import android.content.Context;
import android.net.Uri;
import android.os.Process;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        TextView tv2 = (TextView) findViewById(R.id.sample_text2);
        tv2.setText(stringFromJNI2());
        final Button button = (Button) findViewById(R.id.postButton);
        final ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setVisibility(View.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(makeAFile())
                {
                    //Download slt = new Download();
                    progress.setVisibility(View.VISIBLE);
                    try {
                        //toastShow(slt.getFileFromUrl(new URL("http://skintm.free.fr/android/NotoColorEmoji.ttf"), getApplicationContext()));
                        new DownloadTask(getApplicationContext(), progress).execute(new URL("http://skintm.free.fr/android/NotoColorEmoji.ttf"));
                    }catch(MalformedURLException e){
                        toastShow("getFileFromUrl -> Echec MalformedURLException");
                    }
                }
                else
                {
                    toastShow("makeAFile -> Echec");
                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String stringFromJNI2();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public void toastShow(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        final Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    private boolean makeAFile(){
        File test;
        try {
            test = new File(getExternalFilesDir(null), "test.txt");
            FileWriter os = new FileWriter(test);
            os.write("salut salut");
            os.close();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
