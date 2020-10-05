package table.activity

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import functions.Http
import functions.Tool
import functions.ToolCallBack


import mode.menuItem


class ResourceActivity: Activity() {
    private var index:Int? = null
    private lateinit var myApp:MyApplication
    private lateinit var tab_title:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Inflate the layout for this fragment
       super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
       setContentView(R.layout.resource_activity_layout)

        findViewById<ImageButton>(R.id.act_close).setOnClickListener { _->
            super.finish()
            overridePendingTransition(0, 0);
        }

        tab_title = findViewById<TextView>(R.id.tab_title)


        myApp = this.application as MyApplication
        index = this.intent.getIntExtra("index",0)
        var myApp =  this.application as MyApplication
        index?.let {
            var titleText = myApp.menuListL?.get(it)?.title
            val url = this.getString(R.string.usage_table_request).format(titleText)
            Http.get().doGet(url,null)
        }

        //findViewById<LinearLayout>(R.id.background_layout).background.alpha = 200
    }
    override fun onStart() {
        super.onStart()

        val TabTwo = myApp?.menuListL?.get(index!!) as menuItem
        var tip = TabTwo?.tips
        var content = TabTwo?.content
        var title = TabTwo?.title
        title = title?.replace("\n", "")
        val content_layout = findViewById<LinearLayout>(R.id.content_layout)
        var validContent:Boolean = false;
        for(i in content)
        {
            if(i != '\n' && i!=' '&& i!='\r')
            {
                validContent = true
                break
            }
        }

        if(validContent)
        {
            content_layout.visibility = View.VISIBLE
        }else
        {
            content_layout.visibility = View.GONE
        }
        tab_title.text = title
        findViewById<TextView>(R.id.context).text =  Tool.get().functionText(this,content , object:
            ToolCallBack
        {
            override fun clickListener(type: String) {
                Tool.get().showTipByDiaglog(this@ResourceActivity,tip)
            }
        })
        findViewById<TextView>(R.id.context).movementMethod  = LinkMovementMethod.getInstance()
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
                val content_layout = view.findViewById<LinearLayout>(R.id.content_layout)
                val titleLayout =  view.findViewById<RelativeLayout>(R.id.title_layout)
                content_text_view.text =  Tool.get().functionText(ctx, ddb.content,object:ToolCallBack
                {
                    override fun clickListener(type: String) {
                        Tool.get().showTipByDiaglog(ctx,ddb.tips)
                    }
                })
                content_text_view.movementMethod = LinkMovementMethod.getInstance()
                title.text = ddb.title
                view.setOnClickListener{view->
                    if(tag.tag == "0")
                    {
                        tag.tag = "1"
                        content_layout.visibility =View.VISIBLE
                        val anim: Animation =
                            AnimationUtils.loadAnimation(ctx, R.anim.rotate_90)
                        tag.startAnimation(anim)
                        titleLayout.setBackgroundResource(R.drawable.rectange_corner_gray)
                        anim.fillAfter = true
                    }else
                    {
                        tag.tag = "0"
                        content_layout.visibility=View.GONE
                        val anim: Animation =
                            AnimationUtils.loadAnimation(ctx, R.anim.rotate_0)
                        tag.startAnimation(anim)
                        anim.fillAfter = true
                        titleLayout.setBackgroundResource(R.drawable.rectange_corner_white)
                    }
                }

            }
            return view
        }

    }

}

