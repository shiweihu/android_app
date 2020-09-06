package table.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView

import com.example.myapplication.MyApplication
import com.example.myapplication.R
import functions.Tool
import mode.menuItem

class TabSixActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_six)

        findViewById<ImageButton>(R.id.act_close).setOnClickListener { _ ->
            this@TabSixActivity.finish()
            overridePendingTransition(0, 0)
        }
        findViewById<ImageButton>(R.id.tip_close).setOnClickListener { _->
            super.findViewById<LinearLayout>(R.id.tips_layout).tag = ""
            super.findViewById<LinearLayout>(R.id.tips_layout).visibility = View.GONE
        }
    }



    override fun onStart() {
        super.onStart()

        var myApp =  this.application as MyApplication
        val tabsix = myApp.menuListL?.get(5) as menuItem

        var tips = tabsix.tips
        var title = tabsix.title
        var content = tabsix.content


        findViewById<TextView>(R.id.tab_title).text = title
        findViewById<TextView>(R.id.tabsix_tips_content).text = Tool.get().functionText(this,tips)
        findViewById<TextView>(R.id.tabsix_content).text = Tool.get().functionText(this,content)

        findViewById<TextView>(R.id.tabsix_tips_content).movementMethod = LinkMovementMethod.getInstance()
        findViewById<TextView>(R.id.tabsix_content).movementMethod = LinkMovementMethod.getInstance()

    }

}