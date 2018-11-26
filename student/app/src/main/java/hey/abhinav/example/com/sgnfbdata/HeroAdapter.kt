package hey.abhinav.example.com.sgnfbdata

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HeroAdapter (val mCtx: Context, val layoutResId: Int, val heroList: List<Hero>)
    : ArrayAdapter<Hero>(mCtx,layoutResId,heroList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var layoutInfliter : LayoutInflater = LayoutInflater.from(mCtx);
        var view: View = layoutInfliter.inflate(layoutResId,null)


        val textViewName = view.findViewById<TextView>(R.id.textViewName);
        //  val textViewtitle = view.findViewById<TextView>(R.id.textViewtitle);

        val hero = heroList[position]

        textViewName.text = hero.subject
        // textViewtitle.text = hero.tit
        //textViewtitle.text = "sdfsd"

        textViewName.setOnClickListener {

            showupdateDialog(hero)
        }

        return view;

    }


    fun showupdateDialog(hero: Hero) {
        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Update")


        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.layout_update_hero,null)
        val edittexts = view.findViewById<TextView>(R.id.subject)
        val edittextt = view.findViewById<TextView>(R.id.title)
        val edittextd = view.findViewById<TextView>(R.id.desc)

        //edittexts.setText(hero.subject)
        edittexts.text = hero.subject
        // edittextt.setText(hero.tit)
        edittextt.text = hero.tit
        // edittextd.setText(hero.desc)
        edittextd.text = hero.desc

        builder.setView(view)
        builder.setPositiveButton("Close",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {


            }

        })


        val alert = builder.create()
        alert.show()

    }

}