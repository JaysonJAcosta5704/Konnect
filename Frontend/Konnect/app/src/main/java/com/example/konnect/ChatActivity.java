package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.java_websocket.handshake.ServerHandshake;

/**
 * This class represents the ChatActivity and handles the chat functionality.
 *
 * @author Jayson Acosta
 */
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

    /**
     * This method is called when the WebSocket connection is opened.
     *
     * @param handshakedata The handshake data for the WebSocket connection.
     */
    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {}

    /**
     * This method is called when a message is received from the WebSocket.
     *
     * @param message The message received from the WebSocket.
     */
    @Override
    public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            String s = chatReceive.getText().toString();
            chatReceive.setText(String.format("%s\n%s", s, message));
        });
    }

    /**
     * This method is called when the WebSocket connection is closed.
     *
     * @param code   The code for the reason the WebSocket connection was closed.
     * @param reason The reason for the WebSocket connection being closed.
     * @param remote A flag indicating whether the connection was closed by the remote server or locally.
     */
    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        String closedBy = remote ? "server" : "local";
        runOnUiThread(() -> {
            String s = chatReceive.getText().toString();
            chatReceive.setText(String.format("%s---\nconnection closed by %s\nreason: %s", s, closedBy, reason));
        });
    }

    /**
     * This method is called when an error occurs in the WebSocket connection.
     *
     * @param ex The exception that occurred.
     */
    @Override
    public void onWebSocketError(Exception ex) { Log.e("Websocket Error", ex.toString()); }
}
