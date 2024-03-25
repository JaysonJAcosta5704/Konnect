package com.example.konnect;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GroupchatActivity extends AppCompatActivity implements WebSocketListener {

    private Button sendButton;
    private EditText messageEditText;
    private TextView chatTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);

        /* Initialize UI elements */
        sendButton = findViewById(R.id.sendButton);
        messageEditText = findViewById(R.id.messageEditText);
        chatTextView = findViewById(R.id.chatTextView);

        /* Set WebSocket listener */
        WebSocketManager.getInstance().setWebSocketListener(this);

        /* Set send button listener */
        sendButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString();
            WebSocketManager.getInstance().sendMessage(message);
        });
    }

    @Override
    public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            String currentText = chatTextView.getText().toString();
            chatTextView.setText(currentText + "\n" + message);
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        // Handle WebSocket closure
    }

    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {
        // Handle WebSocket opening
    }

    @Override
    public void onWebSocketError(Exception ex) {
        // Handle WebSocket error
    }
}
