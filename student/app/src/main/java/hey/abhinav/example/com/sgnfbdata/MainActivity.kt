package hey.abhinav.example.com.sgnfbdata

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var listView: ListView

    lateinit var heroList: MutableList<Hero>
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heroList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("heroes")


        listView = findViewById(R.id.listview)



        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {

                if(p0!!.exists()){
                    heroList.clear()

                    for(h in p0.children){

                        val hero = h.getValue(Hero::class.java)
                        heroList.add(hero!!)
                    }

                    val adapter = HeroAdapter(this@MainActivity,R.layout.heroes,heroList)
                    listView.adapter = adapter

                }

            }


        });
    }


}
