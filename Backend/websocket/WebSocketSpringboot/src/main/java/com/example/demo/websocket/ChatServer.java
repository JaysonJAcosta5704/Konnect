//package com.example.demo.websocket;
//
//import java.io.IOException;
//import java.util.Hashtable;
//import java.util.Map;
//
//import jakarta.websocket.OnClose;
//import jakarta.websocket.OnError;
//import jakarta.websocket.OnMessage;
//import jakarta.websocket.OnOpen;
//import jakarta.websocket.Session;
//import jakarta.websocket.server.PathParam;
//import jakarta.websocket.server.ServerEndpoint;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
///**
// * Represents a WebSocket chat server for handling real-time communication
// * between users. Each user connects to the server using their unique
// * username.
// *
// * This class is annotated with Spring's `@ServerEndpoint` and `@Component`
// * annotations, making it a WebSocket endpoint that can handle WebSocket
// * connections at the "/chat/{username}" endpoint.
// *
// * Example URL: ws://localhost:8080/chat/username
// *
// * The server provides functionality for broadcasting messages to all connected
// * users and sending messages to specific users.
// */
//@ServerEndpoint("/chat/{username}")
//@Component
//public class ChatServer {
//
//    // Store all socket session and their corresponding username
//    // Two maps for the ease of retrieval by key
//    private static Map < Session, String > sessionUsernameMap = new Hashtable < > ();
//    private static Map < String, Session > usernameSessionMap = new Hashtable < > ();
//
//    // server side logger
//    private final Logger logger = LoggerFactory.getLogger(ChatServer.class);
//
//    /**
//     * This method is called when a new WebSocket connection is established.
//     *
//     * @param session represents the WebSocket session for the connected user.
//     * @param username username specified in path parameter.
//     */
//    @OnOpen
//    public void onOpen(Session session, @PathParam("username") String username) throws IOException {
//
//        // server side log
//        logger.info("[onOpen] " + username);
//
//        // Handle the case of a duplicate username
//        if (usernameSessionMap.containsKey(username)) {
//            session.getBasicRemote().sendText("Username already exists");
//            session.close();
//        }
//        else {
//            // map current session with username
//            sessionUsernameMap.put(session, username);
//
//            // map current username with session
//            usernameSessionMap.put(username, session);
//
//            // send to the user joining in
//            sendMessageToPArticularUser(username, "Welcome to the chat server, "+username);
//
//            // send to everyone in the chat
//            broadcast("User: " + username + " has Joined the Chat");
//        }
//    }
//
//    /**
//     * Handles incoming WebSocket messages from a client.
//     *
//     * @param session The WebSocket session representing the client's connection.
//     * @param message The message received from the client.
//     */
//    @OnMessage
//    public void onMessage(Session session, String message) throws IOException {
//
//        // get the username by session
//        String username = sessionUsernameMap.get(session);
//
//        // server side log
//        logger.info("[onMessage] " + username + ": " + message);
//
//        // Direct message to a user using the format "@username <message>"
//        if (message.startsWith("@")) {
//
//            // split by space
//            String[] split_msg =  message.split("\\s+");
//
//            // Combine the rest of message
//            StringBuilder actualMessageBuilder = new StringBuilder();
//            for (int i = 1; i < split_msg.length; i++) {
//                actualMessageBuilder.append(split_msg[i]).append(" ");
//            }
//            String destUserName = split_msg[0].substring(1);    //@username and get rid of @
//            String actualMessage = actualMessageBuilder.toString();
//            sendMessageToPArticularUser(destUserName, "[DM from " + username + "]: " + actualMessage);
//            sendMessageToPArticularUser(username, "[DM from " + username + "]: " + actualMessage);
//        }
//        else { // Message to whole chat
//            broadcast(username + ": " + message);
//        }
//    }
//
//    /**
//     * Handles the closure of a WebSocket connection.
//     *
//     * @param session The WebSocket session that is being closed.
//     */
//    @OnClose
//    public void onClose(Session session) throws IOException {
//
//        // get the username from session-username mapping
//        String username = sessionUsernameMap.get(session);
//
//        // server side log
//        logger.info("[onClose] " + username);
//
//        // remove user from memory mappings
//        sessionUsernameMap.remove(session);
//        usernameSessionMap.remove(username);
//
//        // send the message to chat
//        broadcast(username + " disconnected");
//    }
//
//    /**
//     * Handles WebSocket errors that occur during the connection.
//     *
//     * @param session   The WebSocket session where the error occurred.
//     * @param throwable The Throwable representing the error condition.
//     */
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//
//        // get the username from session-username mapping
//        String username = sessionUsernameMap.get(session);
//
//        // do error handling here
//        logger.info("[onError]" + username + ": " + throwable.getMessage());
//    }
//
//    /**
//     * Sends a message to a specific user in the chat (DM).
//     *
//     * @param username The username of the recipient.
//     * @param message  The message to be sent.
//     */
//    private void sendMessageToPArticularUser(String username, String message) {
//        try {
//            usernameSessionMap.get(username).getBasicRemote().sendText(message);
//        } catch (IOException e) {
//            logger.info("[DM Exception] " + e.getMessage());
//        }
//    }
//
//    /**
//     * Broadcasts a message to all users in the chat.
//     *
//     * @param message The message to be broadcasted to all users.
//     */
//    private void broadcast(String message) {
//        sessionUsernameMap.forEach((session, username) -> {
//            try {
//                session.getBasicRemote().sendText(message);
//            } catch (IOException e) {
//                logger.info("[Broadcast Exception] " + e.getMessage());
//            }
//        });
//    }
//}
//package com.example.demo.websocket;
//
//import java.io.IOException;
//import java.util.Hashtable;
//import java.util.Map;
//
//import jakarta.websocket.OnClose;
//import jakarta.websocket.OnError;
//import jakarta.websocket.OnMessage;
//import jakarta.websocket.OnOpen;
//import jakarta.websocket.Session;
//import jakarta.websocket.server.PathParam;
//import jakarta.websocket.server.ServerEndpoint;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@ServerEndpoint("/chat/{username}")
//@Component
//public class ChatServer {
//
//    private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
//    private static Map<String, Session> usernameSessionMap = new Hashtable<>();
//    private final Logger logger = LoggerFactory.getLogger(ChatServer.class);
//
//    @OnOpen
//    public void onOpen(Session session, @PathParam("username") String username) throws IOException {
//        logger.info("[onOpen] " + username);
//        if (usernameSessionMap.containsKey(username)) {
//            session.getBasicRemote().sendText("Username already exists");
//            session.close();
//        } else {
//            sessionUsernameMap.put(session, username);
//            usernameSessionMap.put(username, session);
//            sendMessageToParticularUser(username, "Welcome to the chat server, " + username);
//            broadcast("User: " + username + " has Joined the Chat");
//            broadcastActiveUsers();
//        }
//    }
//
//    @OnMessage
//    public void onMessage(Session session, String message) throws IOException {
//        String username = sessionUsernameMap.get(session);
//        logger.info("[onMessage] " + username + ": " + message);
//
//        if (message.startsWith("@")) {
//            String destUsername = message.split("\\s")[0].substring(1); // Extract username without '@'
//            String actualMessage = message.substring(destUsername.length() + 2); // +2 for '@' and ' ' (space)
//            sendMessageToParticularUser(destUsername, "[DM from " + username + "]: " + actualMessage);
//        } else {
//            broadcast(username + ": " + message);
//        }
//    }
//
//    @OnClose
//    public void onClose(Session session) throws IOException {
//        String username = sessionUsernameMap.get(session);
//        logger.info("[onClose] " + username);
//        sessionUsernameMap.remove(session);
//        usernameSessionMap.remove(username);
//        broadcast("User " + username + " disconnected");
//        broadcastActiveUsers();
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        logger.error("[onError] " + throwable.getMessage());
//        // You might want to handle errors more gracefully here
//    }
//
//    private void sendMessageToParticularUser(String username, String message) {
//        try {
//            Session session = usernameSessionMap.get(username);
//            if (session != null) {
//                session.getBasicRemote().sendText(message);
//            }
//        } catch (IOException e) {
//            logger.error("Failed to send message to user " + username, e);
//        }
//    }
//
//    private void broadcast(String message) {
//        sessionUsernameMap.keySet().forEach(session -> {
//            try {
//                session.getBasicRemote().sendText(message);
//            } catch (IOException e) {
//                logger.error("Failed to broadcast message", e);
//            }
//        });
//    }
//
//    private void broadcastActiveUsers() {
//        String users = String.join(", ", usernameSessionMap.keySet());
//        broadcast("Active users: " + users);
//    }
//}

package com.example.demo.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@ServerEndpoint("/chat/{username}")
@Component
public class ChatServer {

    private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
    private static Map<String, Session> usernameSessionMap = new Hashtable<>();
    private final Logger logger = LoggerFactory.getLogger(ChatServer.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException {
        // Simulated Authentication
        if(username == null || username.trim().isEmpty() || "admin".equalsIgnoreCase(username)) {
            session.getBasicRemote().sendText("Error: Invalid username");
            session.close();
            return;
        }

        logger.info("[onOpen] " + username);
        if (usernameSessionMap.containsKey(username)) {
            session.getBasicRemote().sendText("Error: Username already exists");
            session.close();
        } else {
            sessionUsernameMap.put(session, username);
            usernameSessionMap.put(username, session);
            broadcast("User: " + username + " has joined the chat");
            broadcastActiveUsers();
        }
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        String username = sessionUsernameMap.get(session);
        logger.info("[onMessage] " + username + ": " + message);

        if (message.equalsIgnoreCase("/typing")) {
            broadcast(username + " is typing...");
            return;
        }

        if (message.startsWith("@")) {
            String destUsername = message.split("\\s")[0].substring(1);
            String actualMessage = message.substring(destUsername.length() + 2);
            sendMessageToParticularUser(destUsername, "[DM from " + username + "]: " + actualMessage);
        } else {
            broadcast(username + ": " + message);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        String username = sessionUsernameMap.get(session);
        logger.info("[onClose] " + username);
        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(username);
        broadcast("User " + username + " has disconnected");
        broadcastActiveUsers();
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("[onError] ", throwable);
    }

    private void sendMessageToParticularUser(String username, String message) {
        try {
            Session session = usernameSessionMap.get(username);
            if (session != null) {
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            logger.error("Failed to send message to user " + username, e);
        }
    }

    private void broadcast(String message) {
        sessionUsernameMap.keySet().forEach(session -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.error("Failed to broadcast message", e);
            }
        });
    }

    private void broadcastActiveUsers() {
        String users = String.join(", ", usernameSessionMap.keySet());
        broadcast("Active users: " + users);
    }
}
