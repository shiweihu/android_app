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

class TabTenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_tab_ten)

        findViewById<ImageButton>(R.id.act_close).setOnClickListener { _ ->
            this@TabTenActivity.finish()
            overridePendingTransition(0, 0)
        }
        var myApp =  this.application as MyApplication
        var titleText = myApp.menuListL?.get(9)?.title
        val url = this.getString(R.string.usage_table_request).format(titleText)
        Http.get().doGet(url,null)

    }

    override fun onStart() {
        super.onStart()

        var myApp =  this.application as MyApplication
        val tab = myApp.menuListL?.get(9) as menuItem


        var title = tab.title
        var content = tab.content


        findViewById<TextView>(R.id.tab_title).text = title

        findViewById<TextView>(R.id.tab_content).text = Tool.get().functionText(this,content)


        findViewById<TextView>(R.id.tab_content).movementMethod = LinkMovementMethod.getInstance()

    }

}