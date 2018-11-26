package hey.abhinav.example.com.sgnfbdata

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.sql.Time
import java.util.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Timer().schedule(object :TimerTask(){
            override fun run() {
                val intent=Intent(this@SplashScreen,MenuActivity::class.java)
                startActivity(intent)
                finish()
            }

        },1200L)
    }
}
