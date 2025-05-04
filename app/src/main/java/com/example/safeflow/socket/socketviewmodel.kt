package com.example.safeflow.socket

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import java.net.URISyntaxException

/**
 * SocketManager - Singleton to handle Socket.io operations with correct event listeners
 */
object SocketManager {
    private const val TAG = "SocketManager"
    private var socket: Socket? = null

    // State values that can be observed in composables
    val isConnected = mutableStateOf(false)
    val hasNewAlert = mutableStateOf(false)
    val alertMessage = mutableStateOf("")

    // Retry configuration
    private const val MAX_RECONNECTION_ATTEMPTS = 5
    private const val RECONNECTION_DELAY = 5000L  // 5 seconds

    /**
     * Initialize socket connection with improved options
     */
    fun init() {
        try {
            val options = IO.Options().apply {
                transports = arrayOf("websocket", "polling") // Try both transport methods
                reconnection = true
                reconnectionAttempts = MAX_RECONNECTION_ATTEMPTS
                reconnectionDelay = RECONNECTION_DELAY
                timeout = 10000 // 10 seconds connection timeout
            }

            // Use 10.0.2.2 for Android emulator, change to your server IP for real device
            val serverUrl = "https://safeflow-backend.onrender.com"
            Log.d(TAG, "Connecting to server: $serverUrl")

            socket = IO.socket(serverUrl, options)
            setupSocketListeners()
            connect()
        } catch (e: URISyntaxException) {
            Log.e(TAG, "Socket initialization error", e)
        }
    }

    /**
     * Setup socket event listeners with proper 'sendSOS' event
     */
    private fun setupSocketListeners() {
        socket?.let { socket ->
            socket.on(Socket.EVENT_CONNECT) {
                Log.d(TAG, "✅ Connected to socket server")
                isConnected.value = true
            }

            socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
                val error = if (args.isNotEmpty()) args[0].toString() else "Unknown error"
                Log.e(TAG, "❌ Connect error: $error")
                isConnected.value = false
            }

            socket.on(Socket.EVENT_DISCONNECT) {
                Log.d(TAG, "🔌 Disconnected from socket server")
                isConnected.value = false
            }

            // Listen for "sendSOS" event - THIS IS THE KEY CHANGE!
            socket.on("sendSOS") { args ->
                Log.d(TAG, "🚨 SOS Alert received!")
                // Set default message or extract from args if provided
                val message = if (args.isNotEmpty()) args[0].toString() else "Emergency SOS Alert!"
                alertMessage.value = message
                hasNewAlert.value = true
            }

            // Also keep listening for "alert" event as a backup
            socket.on("alert") { args ->
                if (args.isNotEmpty()) {
                    val message = args[0].toString()
                    Log.d(TAG, "🚨 Alert received: $message")
                    alertMessage.value = message
                    hasNewAlert.value = true
                }
            }
        }
    }

    /**
     * Connect to socket server with error handling
     */
    fun connect() {
        try {
            Log.d(TAG, "Attempting to connect to socket server...")
            socket?.connect()
        } catch (e: Exception) {
            Log.e(TAG, "Error connecting to socket", e)
        }
    }

    /**
     * Disconnect from socket server with error handling
     */
    fun disconnect() {
        try {
            Log.d(TAG, "Disconnecting from socket server...")
            socket?.disconnect()
        } catch (e: Exception) {
            Log.e(TAG, "Error disconnecting from socket", e)
        }
    }

    /**
     * Reset alert state after handling
     */
    fun resetAlert() {
        hasNewAlert.value = false
        alertMessage.value = ""
    }

    /**
     * Check if socket is initialized
     */
    fun isSocketInitialized(): Boolean = socket != null
}