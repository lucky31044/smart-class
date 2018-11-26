package hey.abhinav.example.com.sgnfbdata

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_menu)

            mAuth = FirebaseAuth.getInstance()
            signOut.setOnClickListener {
                view: View? -> mAuth.signOut()
                startActivity(Intent(this, PhoneAuthentication::class.java))
                Toast.makeText(this, "Logged out Successfully :)", Toast.LENGTH_LONG).show()
            }

            ass.setOnClickListener {
                val intent2: Intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent2)
            }
            abt.setOnClickListener {
                val intent3: Intent = Intent(applicationContext, AboutSection::class.java)
                startActivity(intent3)
            }
            ntc.setOnClickListener {
                val intent4: Intent = Intent(applicationContext, MainActivity_FBupdate::class.java)
                startActivity(intent4)
            }
            attc.setOnClickListener {
                val intent4: Intent = Intent(applicationContext, ViewUploadsActivity::class.java)
                startActivity(intent4)
            }
            gc.setOnClickListener {
                val intent4: Intent = Intent(applicationContext, MainActivityChat::class.java)
                startActivity(intent4)
            }
            abt.setOnClickListener {
                val intent4: Intent = Intent(applicationContext, Aboutvid::class.java)
                startActivity(intent4)
            }


        }

    lateinit var mAuth: FirebaseAuth




    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser == null) {
            startActivity(Intent(this, PhoneAuthentication::class.java))
        }else {
          //  Toast.makeText(this, "Already Signed in :)", Toast.LENGTH_LONG).show()
        }
    }

}
