package com.example.user.lbfood.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import static android.app.slice.Slice.EXTRA_RANGE_VALUE;
import static android.app.slice.Slice.EXTRA_TOGGLE_STATE;

public class MyBroadcastReceiver extends BroadcastReceiver {

    public static String EXTRA_MESSAGE = "message";
    public static int receivedCount = 0;
    public static int receivedRange = 0;
    private static Uri sliceCount = Uri.parse("content://com.example/count");
    private static Uri sliceRange = Uri.parse("content://com.example/range");

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra(EXTRA_TOGGLE_STATE)) {
            Toast.makeText(context, "Toggled:  " + intent.getBooleanExtra(
                    EXTRA_TOGGLE_STATE, false), Toast.LENGTH_LONG).show();
        }
        if (intent.hasExtra(EXTRA_MESSAGE)) {
            receivedCount++;
            Toast.makeText(context, intent.getStringExtra(EXTRA_MESSAGE), Toast.LENGTH_LONG).show();
            context.getContentResolver().notifyChange(sliceCount, null);
        }
        if (intent.hasExtra(EXTRA_RANGE_VALUE)) {
            receivedRange = intent.getIntExtra(EXTRA_RANGE_VALUE, 0);
            context.getContentResolver().notifyChange(sliceRange, null);
        }
    }
}
