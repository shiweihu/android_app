package table.activity

import android.app.Activity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import functions.Tool
import mode.TabFour


class TabFourActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_four)
    }

    override fun onStart() {
        super.onStart()
        var myApp =  this.application as MyApplication
        val tabfour = myApp.menuListL?.get(3) as TabFour

        var title = findViewById<TextView>(R.id.tab_title)

        title.text = tabfour.title?.replace("\n", "")
        val subtitle = findViewById<TextView>(R.id.tab_subhead)
        subtitle.text = tabfour.getDropDownBoxByIndex(0).title
        val close_but = findViewById<ImageButton>(R.id.act_close)
        close_but.setOnClickListener{ _->
            super.finish()
            overridePendingTransition(0, 0);
        }
        val viewpage = findViewById<ViewPager>(R.id.vpGoods)
        viewpage.adapter = object : PagerAdapter() {
            override fun getCount(): Int {
                return tabfour.getList().size
            }

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view == `object`
            }
            override fun instantiateItem(container: ViewGroup, position: Int): Any {

                val dbox = tabfour.getDropDownBoxByIndex(position)
                val view = LayoutInflater.from(this@TabFourActivity).inflate(
                    R.layout.tab4_page_view,
                    null
                )
                view.findViewById<ImageButton>(R.id.tip_close).setOnClickListener{ _->
                    view.findViewById<LinearLayout>(R.id.tips_layout).tag = ""
                    view.findViewById<LinearLayout>(R.id.tips_layout).visibility = View.GONE
                }
                view.findViewById<TextView>(R.id.tipTextView).text = dbox.tips
                if(dbox.tips.isEmpty())
                {
                    view.findViewById<LinearLayout>(R.id.tips_layout).tag = ""
                    view.findViewById<LinearLayout>(R.id.tips_layout).visibility = View.GONE
                }
                view.findViewById<TextView>(R.id.tab4_content).text =  Tool.get().functionText(this@TabFourActivity,dbox.content)
                view.findViewById<TextView>(R.id.tab4_content).movementMethod = LinkMovementMethod.getInstance()
                container.addView(view)
                return view
            }
            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                val view: View? = `object` as View?
                container.removeView(view)
            }
        }


        viewpage.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                subtitle.text = tabfour.getDropDownBoxByIndex(position).title
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })



    }
}