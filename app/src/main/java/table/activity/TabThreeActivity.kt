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

class TabThreeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        setContentView(R.layout.activity_tab_three)

        findViewById<ImageButton>(R.id.act_close).setOnClickListener { _->
            super.finish()
            overridePendingTransition(0, 0);
        }
        findViewById<ImageButton>(R.id.tip_close).setOnClickListener { _->
            super.findViewById<LinearLayout>(R.id.tips_layout).tag = ""
            super.findViewById<LinearLayout>(R.id.tips_layout).visibility = View.GONE
        }


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
        findViewById<TextView>(R.id.tipTextView).text = tip

    }
}