package com.murilocardoso.applicationnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    lateinit var notificationButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationButton = findViewById(R.id.button_send_notification)
        listeners()
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful){
                Log.w("oops","Fetching FCM registration token failed", task.exception)
            }

            val token = task.result
            val msg = token.toString()
            Log.d("Token", msg)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })
    }

    private fun listeners() {
        notificationButton.setOnClickListener {
            this.showNotification("1234", "Obrigado amigo", "você é um amigo")
        }
    }


}