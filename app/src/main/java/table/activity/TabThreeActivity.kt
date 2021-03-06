package table.activity

import android.app.Activity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import functions.Http
import functions.Tool
import mode.menuItem

class TabThreeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // Inflate the layout for this fragment
        setContentView(R.layout.activity_tab_three)

        findViewById<ImageButton>(R.id.act_close).setOnClickListener { _->
            super.finish()
            overridePendingTransition(0, 0);
        }

        var myApp =  this.application as MyApplication
        var titleText = myApp.menuListL?.get(2)?.title
        val url = this.getString(R.string.usage_table_request).format(titleText)
        Http.get().doGet(url,null)


    }

    override fun onStart() {
        super.onStart()
        var myApp =  this.application as MyApplication
        var tip = myApp.menuListL?.get(2)?.tips
        var text =  myApp.menuListL?.get(2)?.content
        var title = myApp.menuListL?.get(2)?.title
        title = title?.replace("\n", "")

        findViewById<TextView>(R.id.tab_title).text = title
        findViewById<TextView>(R.id.tab3_content).text = text?.let {
            Tool.get().functionText(this,
                it
            )
        }
        findViewById<TextView>(R.id.tab3_content).movementMethod = LinkMovementMethod.getInstance()


    }
}