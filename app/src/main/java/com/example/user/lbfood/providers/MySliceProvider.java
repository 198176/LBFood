package com.example.user.lbfood.providers;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.example.user.lbfood.MainActivity;
import com.example.user.lbfood.R;
import com.example.user.lbfood.SecondActivity;
import com.example.user.lbfood.receivers.MyBroadcastReceiver;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.SliceProvider;
import androidx.slice.builders.GridRowBuilder;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.ListBuilder.RowBuilder;
import androidx.slice.builders.SliceAction;

public class MySliceProvider extends SliceProvider {

    @Override
    public boolean onCreateSliceProvider() {
        return true;
    }

    /**
     * Converts URL to content URI (i.e. content://com.example.user.lbfood...)
     */
    @Override
    @NonNull
    public Uri onMapIntentToUri(@Nullable Intent intent) {
        // Note: implementing this is only required if you plan on catching URL requests.
        // This is an example solution.
        Uri.Builder uriBuilder = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT);
        if (intent == null) return uriBuilder.build();
        Uri data = intent.getData();
        if (data != null && data.getPath() != null) {
            String path = data.getPath().replace("/", "");
            uriBuilder = uriBuilder.path(path);
        }
        Context context = getContext();
        if (context != null) {
            uriBuilder = uriBuilder.authority("com.example");
        }
        return uriBuilder.build();
    }

    /**
     * Construct the Slice and bind data if available.
     */
    public Slice onBindSlice(Uri sliceUri) {
        if (getContext() == null || sliceUri == null) {
            return null;
        }
        switch (sliceUri.getPath()) {
            case "/hello":
                return createSliceHello(sliceUri);
            case "/toggle":
                return createSliceToggle(sliceUri);
            case "/count":
                return createSliceDynamicCount(sliceUri);
            case "/header":
                return createSliceHeader(sliceUri);
            case "/actions":
                return createSliceWithActionInHeader(sliceUri);
            case "/grid":
                return createSliceWithGridRow(sliceUri);
            case "/range":
                return createSliceRange(sliceUri);
            case "/more":
                return createSliceMore(sliceUri);
            default:
                return new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY)
                        .addRow(new RowBuilder().setTitle("URI not found.")
                                .setPrimaryAction(
                                        SliceAction.create(PendingIntent.getActivity(getContext(), 0, new Intent(Settings.ACTION_SETTINGS), 0),
                                                IconCompat.createWithResource(getContext(), R.drawable.ic_launcher_foreground),
                                                ListBuilder.ICON_IMAGE, "Open App"))).build();
        }
    }

    Slice createSliceHello(Uri sliceUri) {
        return new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY)
                .addRow(new RowBuilder()
                        .setTitle("Hello")
                        .setPrimaryAction(createActivityAction(MainActivity.class))
                ).build();
    }

    Slice createSliceToggle(Uri sliceUri) {
        SliceAction toggleAction = SliceAction.createToggle(
                PendingIntent.getBroadcast(getContext(), 0,
                        new Intent(getContext(), MyBroadcastReceiver.class), 0),
                "Toggle WiFi", true);
        return new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY)
                .addRow(new RowBuilder()
                        .setTitle("Wi-Fi")
                        .setSubtitle("Enable / disable wifi")
                        .setPrimaryAction(toggleAction)
                ).build();
    }

    Slice createSliceDynamicCount(Uri slideUri) {
        Intent intent = new Intent(getContext(), MyBroadcastReceiver.class)
                .putExtra(MyBroadcastReceiver.EXTRA_MESSAGE, "Item clicked");
        SliceAction toastAndIncrementAction = SliceAction.create(
                PendingIntent.getBroadcast(getContext(), 1, intent, 0),
                IconCompat.createWithResource(getContext(), R.drawable.ic_launcher_foreground),
                ListBuilder.ICON_IMAGE, "Increment.");
        return new ListBuilder(getContext(), slideUri, ListBuilder.INFINITY)
                .addRow(new RowBuilder()
                        .setPrimaryAction(toastAndIncrementAction)
                        .setTitle("Count: " + MyBroadcastReceiver.receivedCount)
                        .setSubtitle("Click me")
                ).build();
    }

    Slice createSliceHeader(Uri sliceUri) {
        return new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY)
                .setAccentColor(0xff0F9D58)
                .setHeader(new ListBuilder.HeaderBuilder()
                        .setTitle("Get a ride")
                        .setSubtitle("Ride in 4 min.")
                        .setSummary("Work in 1 hour 45 min | Home in 12 min."))
                .addRow(new RowBuilder()
                        .setTitle("Home")
                        .setSubtitle("12 min")
                        .setPrimaryAction(createActivityAction(MainActivity.class))
                        .addEndItem(IconCompat.createWithResource(getContext(), R.drawable.ic_home), ListBuilder.ICON_IMAGE))
                .addRow(new RowBuilder()
                        .setTitle("Work")
                        .setSubtitle("1hour 45 min")
                        .addEndItem(IconCompat.createWithResource(getContext(), R.drawable.ic_arrow), ListBuilder.ICON_IMAGE)
                        .setPrimaryAction(createActivityAction(SecondActivity.class))
                ).build();
    }

    Slice createSliceWithActionInHeader(Uri sliceUri) {
        SliceAction secondAction = SliceAction.create(
                PendingIntent.getActivity(getContext(), 0, new Intent(getContext(), SecondActivity.class), 0),
                IconCompat.createWithResource(getContext(), R.drawable.ic_home),
                ListBuilder.ICON_IMAGE, "Take second");
        SliceAction mainAction = SliceAction.create(
                PendingIntent.getActivity(getContext(), 0, new Intent(getContext(), MainActivity.class), 0),
                IconCompat.createWithResource(getContext(), R.drawable.ic_arrow),
                ListBuilder.ICON_IMAGE, "Take main");
        return new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY)
                .setAccentColor(0xfff4b400)
                .addRow(new RowBuilder()
                        .setTitle("Create new note")
                        .setSubtitle("Easily done with this note")
                        .setPrimaryAction(createActivityAction(SecondActivity.class)))
                .addAction(secondAction)
                .addAction(mainAction)
                .build();
    }

    Slice createSliceWithGridRow(Uri sliceUri) {
        return new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY)
                .setHeader(new ListBuilder.HeaderBuilder()
                        .setTitle("Coś na ząb"))
                .addGridRow(new GridRowBuilder()
                        .addCell(new GridRowBuilder.CellBuilder()
                                .addImage(IconCompat.createWithResource(getContext(), R.drawable.diavolo), ListBuilder.LARGE_IMAGE)
                                .addTitleText("Pizza")
                                .addText("Diavolo")
                                .setContentIntent(
                                        PendingIntent.getActivity(getContext(), 3, new Intent(getContext(), MainActivity.class), 0)))
                        .addCell(new GridRowBuilder.CellBuilder()
                                .addImage(IconCompat.createWithResource(getContext(), R.drawable.kurczak), ListBuilder.LARGE_IMAGE)
                                .addTitleText("Chińskie")
                                .addText("Kurczak")
                                .setContentIntent(
                                        PendingIntent.getActivity(getContext(), 4, new Intent(getContext(), MainActivity.class), 0)))
                        .addCell(new GridRowBuilder.CellBuilder()
                                .addImage(IconCompat.createWithResource(getContext(), R.drawable.rollo), ListBuilder.LARGE_IMAGE)
                                .addTitleText("Kebab")
                                .addText("Rollo")
                                .setContentIntent(
                                        PendingIntent.getActivity(getContext(), 5, new Intent(getContext(), MainActivity.class), 0)))
                        .setPrimaryAction(createActivityAction(MainActivity.class))
                ).build();
    }

    Slice createSliceRange(Uri sliceUri) {
        return new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY)
                .addInputRange(new ListBuilder.InputRangeBuilder()
                        .setTitle("Ring Volume")
                        .setSubtitle(MyBroadcastReceiver.receivedRange + "%")
                        .setInputAction(PendingIntent.getBroadcast(getContext(), 0, new Intent(getContext(), MyBroadcastReceiver.class), 0))
                        .setPrimaryAction(createActivityAction(MainActivity.class))
                        .setMax(100)
                        .setMin(0)
                        .setValue(MyBroadcastReceiver.receivedRange))
                .build();
    }

    Slice createSliceMore(Uri sliceUri) {
        return new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY)
                .setHeader(new ListBuilder.HeaderBuilder()
                        .setTitle("Header"))
                .addRow(new RowBuilder()
                        .setTitle("One")
                        .setPrimaryAction(createActivityAction(MainActivity.class)))
                .addRow(new RowBuilder()
                        .setTitle("Two"))
                .addRow(new RowBuilder()
                        .setTitle("Three")
                        .setPrimaryAction(createActivityAction(SecondActivity.class)))
                .addRow(new RowBuilder()
                        .setTitle("Four"))
                .addRow(new RowBuilder()
                        .setTitle("Five"))
                .addRow(new RowBuilder()
                        .setTitle("Six"))
                .setSeeMoreRow(new RowBuilder()
                        .setTitle("Add")
                        .addEndItem(IconCompat.createWithResource(getContext(), R.drawable.ic_home),
                                ListBuilder.ICON_IMAGE))
//                .setSeeMoreAction(PendingIntent.getActivity(getContext(), 9, new Intent(getContext(), MainActivity.class), 0))
                .build();
    }

    private SliceAction createActivityAction(Class toClass) {
        return SliceAction.create(PendingIntent.getActivity(getContext(), new Random().nextInt(), new Intent(getContext(), toClass), 0),
                IconCompat.createWithResource(getContext(), R.drawable.ic_launcher_foreground),
                ListBuilder.ICON_IMAGE, "Open App");
    }

}
