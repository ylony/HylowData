package com.ylofanclub.hylow.hylowdata;

import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Download{
    private ProgressBar progress;
    public Download(ProgressBar progress)
    {
        this.progress = progress;
    }
    public float getFileFromUrl(URL remoteFile, Context context) {
        String fileName = remoteFile.getFile();
        fileName = fileName.substring(fileName.lastIndexOf('/') + 1);
        try {
            URLConnection remote = remoteFile.openConnection();
            float size = remote.getContentLength();
            int percent;
            float current = 0;
            InputStream input = remoteFile.openStream();
            File out = new File(context.getExternalFilesDir(null), fileName);
            OutputStream output = new FileOutputStream(out);
            byte[] buffer = new byte[1024];
            int i;
            while ((i = input.read(buffer)) > 0) {
                current += i;
                percent = (int) (( current / size)*100);
                progress.setProgress(percent); // API 23
               // progress.setProgress(percent, true); //API 24
                output.write(buffer, 0, i);
            }
            output.flush();
            output.close();
            input.close();
            return size;
        } catch (MalformedURLException mue) {
            return -2;
        } catch (IOException ioe) {
            return -3;
        } catch (SecurityException se) {
            return -4;
        }
    }
}