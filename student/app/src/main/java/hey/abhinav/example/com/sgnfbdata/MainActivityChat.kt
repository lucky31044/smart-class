package hey.abhinav.example.com.sgnfbdata

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.*
import java.io.*

class MainActivityChat : AppCompatActivity() {

    lateinit var sub: EditText
    lateinit var but: ImageButton
    lateinit var listView: ListView
    //lateinit var text1:String
    lateinit var text2:String

    lateinit var heroList: MutableList<HeroFB2Chat>
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_chat)

        heroList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("chat")

        sub = findViewById(R.id.update)
        but = findViewById(R.id.submit)
        listView = findViewById(R.id.listview)

       // save()
        load()


        but.setOnClickListener {

            saveHero()
        }


        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {

                if (p0!!.exists()) {
                    heroList.clear()

                    for (h in p0.children) {

                        val hero = h.getValue(HeroFB2Chat::class.java)

                        heroList.add(hero!!)
                    }

                    val adapter = HeroAdapterChat(this@MainActivityChat, R.layout.heroes_chat, heroList)
                    listView.adapter = adapter

                }

            }


        });

    }



//    fun save() {
//        //  val text = mEditText.text.toString()+" "+mEditText1.text.toString()
//        var text1 = "Hi"
//        var fos: FileOutputStream? = null
//
//        try {
//            val fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
//
//            fos.write(text1.toByteArray());
//            Toast.makeText(this, "Saved to $text1", Toast.LENGTH_LONG).show()
//            // mEditText.text.clear()
//            Toast.makeText(this, "Saved to $filesDir/$FILE_NAME",
//                    Toast.LENGTH_LONG).show()
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//
//            }
//        }
//    }

    fun load() {
        var fis: FileInputStream? = null

        try {
            fis = openFileInput(FILE_NAME)
            val isr = InputStreamReader(fis!!)
            val br = BufferedReader(isr)
            val sb = StringBuilder()

            var text1: String?
            do{
                text1=br.readLine()
                if(text1==null)
                    break
                sb.append(text1).append("\n")

            }while(true)

            // mEditText.setText(sb.toString())
            text2=sb.toString()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fis != null) {
                try {
                    fis.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    companion object {
        private val FILE_NAME = "example.txt"
    }



    private fun saveHero() {
        var subf = sub.text.toString().trim()
        // var sub2:String?="Abhay"
        var sub2f = text2.toString().trim()

        if (subf.isEmpty()) {
            sub.error = "Please enter Subject Name"
            return

        }

        val heroid = ref.push().key
        val hero = HeroFB2Chat(heroid, subf,sub2f)

        ref.child(heroid).setValue(hero).addOnCompleteListener {

            //Toast.makeText(applicationContext, "Subject save success", Toast.LENGTH_LONG).show()
        }
    }
}
