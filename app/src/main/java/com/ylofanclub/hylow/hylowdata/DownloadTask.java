package com.ylofanclub.hylow.hylowdata;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.net.URL;

/**
 * Created by hylow on 21/04/2017.
 */

public class DownloadTask extends AsyncTask<URL, Integer, Long> {
    // Do the long-running work in here
    private Context context;
    private ProgressBar progress;
    public DownloadTask(Context e, ProgressBar progress)
    {
        this.context = e;
        this.progress = progress;
    }
    protected Long doInBackground(URL... u) {
        long totalSize = 0;
        Download dl = new Download(progress);
        publishProgress(0);
        totalSize += dl.getFileFromUrl(u[0], context);
        return totalSize;
    }

    // This is called each time you call publishProgress()
    protected void onProgressUpdate(Integer... progress) {
        Utils u = new Utils();
        u.toastShow2("Downloaded file "+progress[0]+" / 1", context);
    }

    // This is called when doInBackground() is finished
    protected void onPostExecute(Long result) {
        Utils u = new Utils();
        if(result == -1) {
            u.toastShow2("Error : Remote file not found", context);
        }
        if(result == -2) {
            u.toastShow2("Error : MalformedUrlException", context);
        }
        if(result == -3) {
            u.toastShow2("Error : IOException", context);
        }
        if(result == -4) {
            u.toastShow2("Error : SecurityException", context);
        }
        else {
            u.toastShow2("Successfully downloaded " + result + " bytes", context);
        }
    }
}
