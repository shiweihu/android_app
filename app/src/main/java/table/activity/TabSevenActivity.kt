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

class TabSevenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_seven)

        findViewById<ImageButton>(R.id.act_close).setOnClickListener { _ ->
            this@TabSevenActivity.finish()
            overridePendingTransition(0, 0)
        }

    }

    override fun onStart() {
        super.onStart()

        var myApp =  this.application as MyApplication
        val tab = myApp.menuListL?.get(6) as menuItem


        var title = tab.title
        var content = tab.content
        var question1 = tab.getDropDownBoxByIndex(0).content
        var question2 = tab.getDropDownBoxByIndex(1).content
        var question3 = tab.getDropDownBoxByIndex(2).content
        var question4 = tab.getDropDownBoxByIndex(3).content
        var question5 = tab.getDropDownBoxByIndex(4).content
        var question6 = tab.getDropDownBoxByIndex(5).content
        var question7 = tab.getDropDownBoxByIndex(6).content
        var other = tab.getDropDownBoxByIndex(7).content

        findViewById<TextView>(R.id.tab_title).text = title

        findViewById<TextView>(R.id.tab_content).text = Tool.get().functionText(this,content)


        findViewById<TextView>(R.id.tab_content).movementMethod = LinkMovementMethod.getInstance()

        question1=question1?.replace("\n", "")
        question2=question2?.replace("\n", "")
        question3=question3?.replace("\n", "")
        question4=question4?.replace("\n", "")
        question5=question5?.replace("\n", "")
        question6=question6?.replace("\n", "")
        question7=question7?.replace("\n", "")

        findViewById<TextView>(R.id.question1_content).text = question1
        findViewById<TextView>(R.id.question2_content).text = question2
        findViewById<TextView>(R.id.question3_content).text = question3
        findViewById<TextView>(R.id.question4_content).text = question4
        findViewById<TextView>(R.id.question5_content).text = question5
        findViewById<TextView>(R.id.question6_content).text = question6
        findViewById<TextView>(R.id.question7_content).text = question7
        findViewById<TextView>(R.id.other_content).text = Tool.get().functionText(this,other)
        findViewById<TextView>(R.id.other_content).movementMethod = LinkMovementMethod.getInstance()

    }


}