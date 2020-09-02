package table.activity

import android.app.Activity

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.myapplication.MyApplication
import com.example.myapplication.R

class TabFiveActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_five)

       findViewById<ImageButton>(R.id.act_close).setOnClickListener {_ ->
           this@TabFiveActivity.finish()
           overridePendingTransition(0, 0)
       }


    }

    override fun onStart() {
        super.onStart()

        var myApp =  this.application as MyApplication
        //var tips = myApp.menuListL?.get(4)?.tips
        //var text =  myApp.menuListL?.get(4)?.content
        //var title = myApp.menuListL?.get(4)?.title
        var tips = "sasasdasdasdasdasdasd"
        var text = "asasdasdasdasd"
        var title="ni hao"

        findViewById<TextView>(R.id.tab_title).text = title;


    }

}