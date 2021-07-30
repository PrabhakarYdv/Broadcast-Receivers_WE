package com.prabhakar.broadcastreceivers_we;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {
    private TextView message;
    private Button updateButton;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = findViewById(R.id.message);
        updateButton = findViewById(R.id.updateBtn);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("Action.Prabhakar.BroadcastReceiver");
                intent.putExtra("msg", "Hello Masai School");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
        registerLocalReceiver();
    }

    private void registerLocalReceiver() {
        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter("Action.Prabhakar.BroadcastReceiver");
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    private class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String receiveMessage = intent.getStringExtra("msg");
                message.setText(receiveMessage);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
}