package com.m.papijames.studybuddy

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

   private  var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        notificationManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        createNotificationChannel("NotificationId", "StudyBuddy", "Testing notifications")


        fabnotify.setOnClickListener { view ->sendNotification(view)
        }

    }



    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(id:String, name:String, description:String){



            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id, name, importance).apply {
                description
                enableLights(true)
                enableVibration(true)
                vibrationPattern=longArrayOf(100,200,300,400,500,400,300,200,400)


            }
            notificationManager?.createNotificationChannel(channel)
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun sendNotification(view: View){
        val notificcationid = 101
        val CHANNEL_ID = "NotificationId"
        var notibuilder = Notification.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle("Study Notification").setContentText("This is the notification text for you!").setSmallIcon(R.drawable.notification_bg) .setPriority(
                NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true).setChannelId(CHANNEL_ID).build()

        notificationManager?.notify(notificcationid,notibuilder)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}
