package hey.abhinav.example.com.sgnfbdata

import android.app.*
import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.widget.ListView
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_main__fbupdate.*

class MainActivity_FBupdate : Activity() {


    lateinit var listView: ListView

    lateinit var heroList: MutableList<Hero_FBupdate>
    lateinit var ref: DatabaseReference

    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder:Notification.Builder
    private val channelid="hey.abhinav.example.com.sgnfbdata"
    private val description="You have got an important notice"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main__fbupdate)

        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        heroList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("update")


        listView = findViewById(R.id.listview)



        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {

                if(p0!!.exists()){
                    heroList.clear()

                    for(h in p0.children){

                        val hero = h.getValue(Hero_FBupdate::class.java)
                        heroList.add(hero!!)
                        val intent= Intent(applicationContext, LauncherActivity::class.java)
                        val pendingIntent= PendingIntent.getActivity(applicationContext,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            notificationChannel= NotificationChannel(channelid,description, NotificationManager.IMPORTANCE_HIGH)
                            notificationChannel.enableLights(true)
                            notificationChannel.lightColor= Color.BLUE
                            notificationChannel.enableVibration(true)
                            notificationManager.createNotificationChannel(notificationChannel)

                            builder= Notification.Builder(applicationContext,channelid)
                                    .setContentTitle("Smart class")
                                    .setContentText("You have got an important notice")
                                    .setSmallIcon(R.drawable.c)
                                    .setLargeIcon(BitmapFactory.decodeResource(applicationContext.resources,R.drawable.c))
                                    .setContentIntent(pendingIntent)


                        }
                        else{
                            builder= Notification.Builder(applicationContext)
                                    .setContentTitle("Smart class")
                                    .setContentText("tester")
                                    .setSmallIcon(R.drawable.c)
                                    .setLargeIcon(BitmapFactory.decodeResource(applicationContext.resources,R.drawable.c))
                                    .setContentIntent(pendingIntent)
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            notificationManager.notify(1234,builder.build())
                        }
                    }

                    val adapter = HeroAdapterFBupdate(this@MainActivity_FBupdate,R.layout.heroes_fbupdate,heroList)
                    listView.adapter = adapter

                }

            }


        });
    }
}
