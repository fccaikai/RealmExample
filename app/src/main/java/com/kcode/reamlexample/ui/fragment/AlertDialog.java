package com.kcode.reamlexample.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by caik on 2016/11/15.
 */

public class AlertDialog extends DialogFragment {
    public static AlertDialog newInstance() {
        
        Bundle args = new Bundle();
        
        AlertDialog fragment = new AlertDialog();
        fragment.setArguments(args);
        return fragment;
    }
}
