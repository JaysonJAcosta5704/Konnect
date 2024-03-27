package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.java_websocket.handshake.ServerHandshake;

public class ChatActivity extends AppCompatActivity implements WebSocketListener{

    TextView chatReceive;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Button homeButton = findViewById(R.id.Chat_Home_Button);
        homeButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), SettingsActivity.class)));

        Button sendButton = findViewById(R.id.Chat_Send_Button);

        EditText sendEditText = findViewById(R.id.Chat_Send_EditText);
        chatReceive = findViewById(R.id.Chat_Receive);

        sendButton.setOnClickListener(v -> {
            try { WebSocketManager.getInstance().sendMessage(sendEditText.getText().toString()); }
            catch (Exception e) { Log.d("ExceptionSendMessage:", e.toString()); }
        });

        String wsURL = "ws://10.0.2.2:8080/chat/" + User.getInstance().getUsername();
        WebSocketManager.getInstance().connectWebSocket(wsURL);
        WebSocketManager.getInstance().setWebSocketListener(this);
    }

    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {}

    @Override
    public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            String s = chatReceive.getText().toString();
            chatReceive.setText(String.format("%s\n%s", s, message));
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        String closedBy = remote ? "server" : "local";
        runOnUiThread(() -> {
            String s = chatReceive.getText().toString();
            chatReceive.setText(String.format("%s---\nconnection closed by %s\nreason: %s", s, closedBy, reason));
        });
    }

    @Override
    public void onWebSocketError(Exception ex) { Log.e("Websocket Error", ex.toString()); }
}
