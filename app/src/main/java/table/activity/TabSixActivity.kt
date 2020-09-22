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
import functions.Tool
import mode.menuItem

class TabSixActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_tab_six)

        findViewById<ImageButton>(R.id.act_close).setOnClickListener { _ ->
            this@TabSixActivity.finish()
            overridePendingTransition(0, 0)
        }

    }



    override fun onStart() {
        super.onStart()

        var myApp =  this.application as MyApplication
        val tabsix = myApp.menuListL?.get(5) as menuItem


        var title = tabsix.title
        var content = tabsix.content


        findViewById<TextView>(R.id.tab_title).text = title

        findViewById<TextView>(R.id.tabsix_content).text = Tool.get().functionText(this,content)


        findViewById<TextView>(R.id.tabsix_content).movementMethod = LinkMovementMethod.getInstance()

    }

}