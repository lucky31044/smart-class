package hey.abhinav.example.com.sgnfbdata

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_phone_authentication.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit

class PhoneAuthentication : AppCompatActivity() {

    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var mAuth: FirebaseAuth
    var verificationId = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_authentication)
        mAuth = FirebaseAuth.getInstance()
        veriBtn.setOnClickListener {
            view: View? -> progress.visibility = View.VISIBLE
            verify ()
            save()
        }
        authBtn.setOnClickListener {
            view: View? -> progress.visibility = View.VISIBLE
            authenticate()
        }
    }


    private fun verificationCallbacks () {
        mCallbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                progress.visibility = View.INVISIBLE
                signIn(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCodeSent(verfication: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(verfication, p1)
                verificationId = verfication.toString()
                progress.visibility = View.INVISIBLE
            }

        }
    }

    private fun verify () {

        verificationCallbacks()

        val phnNo = phnNoTxt.text.toString()

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phnNo,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks
        )
    }

    private fun signIn (credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener {
                    task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        toast("Logged in Successfully :)")
                        startActivity(Intent(this, MenuActivity::class.java))
                    }
                }
    }

    private fun authenticate () {

        val verifiNo = verifiTxt.text.toString()

        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, verifiNo)

        signIn(credential)

    }

    private fun toast (msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun save() {
        //  val text = mEditText.text.toString()+" "+mEditText1.text.toString()
        var text1 ="S-"+"(" +name.text.toString()+")"+"\n"+phnNoTxt.text.toString()
        var fos: FileOutputStream? = null

        try {
            val fos = openFileOutput(PhoneAuthentication.FILE_NAME, Context.MODE_PRIVATE)

            fos.write(text1.toByteArray());
           // Toast.makeText(this, "Saved to $text1", Toast.LENGTH_LONG).show()
            // mEditText.text.clear()
          //  Toast.makeText(this, "Saved to $filesDir/${PhoneAuthentication.FILE_NAME}",
             //       Toast.LENGTH_LONG).show()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    companion object {
        private val FILE_NAME = "example.txt"
    }



}