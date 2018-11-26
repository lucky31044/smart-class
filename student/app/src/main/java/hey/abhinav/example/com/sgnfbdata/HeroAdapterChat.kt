package hey.abhinav.example.com.sgnfbdata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HeroAdapterChat(val mCtx: Context, val layoutResId: Int, val heroList: List<HeroFB2Chat>)
    : ArrayAdapter<HeroFB2Chat>(mCtx,layoutResId,heroList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var layoutInfliter : LayoutInflater = LayoutInflater.from(mCtx);
        var view: View = layoutInfliter.inflate(layoutResId,null)

        val textViewName = view.findViewById<TextView>(R.id.textViewName);
        val textViewName2= view.findViewById<TextView>(R.id.textViewName2);

        //val hero = heroList[position]
        val hero= getItem(position)
        textViewName.setText(hero.subject)
        textViewName2.setText(hero.name)

       // Toast.makeText(mCtx, hero.name, Toast.LENGTH_LONG).show()

        return view;


    }

}