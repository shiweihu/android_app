package table.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import functions.Tool
import mode.DropDownBox
import mode.menuItem


class TabTwoActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Inflate the layout for this fragment
       super.onCreate(savedInstanceState)
       setContentView(R.layout.tab_2)

        findViewById<ImageButton>(R.id.act_close).setOnClickListener { _->
            super.finish()
            overridePendingTransition(0, 0);
        }

        //findViewById<LinearLayout>(R.id.background_layout).background.alpha = 200
    }
    override fun onStart() {
        super.onStart()
        var myApp = this.application as MyApplication
        val TabTwo = myApp.menuListL?.get(1) as menuItem
        var tip = TabTwo?.tips
        var content = TabTwo?.content
        var title = TabTwo?.title
        title = title?.replace("\n", "")

        findViewById<TextView>(R.id.tab_title).text = title

        val listView = findViewById<ListView>(R.id.list_view)

        listView.adapter =ListItemAdapt(this,TabTwo)

    }

    private class ListItemAdapt(val ctx: Context, val data:menuItem):BaseAdapter()
    {
        override fun getCount(): Int {
            return data.getList().size
        }

        override fun getItem(p0: Int): Any {
            return data.getDropDownBoxByIndex(p0)
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
            var view:View? = p1
            if(view== null)
            {
                val ddb = data.getDropDownBoxByIndex(position)
                view =  LayoutInflater.from(ctx).inflate(R.layout.carers_tips_item,null) as RelativeLayout
                val title = view.findViewById<TextView>(R.id.title_view)
                val tag = view.findViewById<ImageView>(R.id.showUp_tag)
                val content_text_view = view .findViewById<TextView>(R.id.content_text_view)
                tag.tag = 0
                //
                title.text = ddb.title
                view.setOnClickListener{view->
                    if(tag.tag == 0)
                    {
                        tag.tag = 1
                        view.findViewById<LinearLayout>(R.id.content_layout).visibility =View.VISIBLE
                        content_text_view.textSize = 20.0F
                        content_text_view.text =  Tool.get().functionText(ctx, ddb.content)
                        content_text_view.movementMethod = LinkMovementMethod.getInstance()
                        val anim: Animation =
                            AnimationUtils.loadAnimation(ctx, R.anim.rotate_90)
                        tag.startAnimation(anim)
                        val titleLayout =  view.findViewById<RelativeLayout>(R.id.title_layout)
                        titleLayout.setBackgroundResource(R.drawable.rectange_corner_gray)
                        anim.fillAfter = true
                    }else
                    {
                        tag.tag = 0
                        view.findViewById<LinearLayout>(R.id.content_layout).visibility=View.INVISIBLE
                        content_text_view.text = ""
                        content_text_view.textSize = .0F
                        val anim: Animation =
                            AnimationUtils.loadAnimation(ctx, R.anim.rotate_0)
                        tag.startAnimation(anim)
                        anim.fillAfter = true
                        val titleLayout =  view.findViewById<RelativeLayout>(R.id.title_layout)
                        titleLayout.setBackgroundResource(R.drawable.rectange_corner_white)
                    }
                }

            }
            return view
        }

    }

}
