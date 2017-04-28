package com.ylofanclub.hylow.hylowdata;

import android.content.Context;
import android.widget.Toast;

import com.ylofanclub.hylow.hylowdata.MainActivity;

/**
 * Created by hylow on 21/04/2017.
 */

public class Utils{
    public void toastShow2(String message, Context context) {
        int duration = Toast.LENGTH_SHORT;
        final Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }


}
