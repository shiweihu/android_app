package table.activity

import android.app.Activity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.*
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import functions.Tool
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
        findViewById<ImageButton>(R.id.tip_close).setOnClickListener { _->
            super.findViewById<LinearLayout>(R.id.tips_layout).tag = ""
            super.findViewById<LinearLayout>(R.id.tips_layout).visibility = View.GONE
        }
        findViewById<LinearLayout>(R.id.background_layout).background.alpha = 100
    }
    override fun onStart()
    {
        super.onStart()
        var myApp =  this.application as MyApplication
        val TabTwo = myApp.menuListL?.get(1) as menuItem
        var tip =TabTwo?.tips
        var content =TabTwo?.content
        var title = TabTwo?.title
        title=title?.replace("\n", "")



        var item1 =  TabTwo.getDropDownBoxByIndex(0).title
        var item2 = TabTwo.getDropDownBoxByIndex(1).title
        var item3 = TabTwo.getDropDownBoxByIndex(2).title
        val item1_content = TabTwo.getDropDownBoxByIndex(0).content
        val item2_content =TabTwo.getDropDownBoxByIndex(1).content
        val item3_content =TabTwo.getDropDownBoxByIndex(2).content

        findViewById<TextView>(R.id.tab_title).text = title
        findViewById<TextView>(R.id.tab2_content).text = Tool.get().functionText(this,content)
        findViewById<TextView>(R.id.tab2_content).movementMethod = LinkMovementMethod.getInstance()
        findViewById<TextView>(R.id.tipTextView).text = tip

        val DropDownBox_content = findViewById<TextView>(R.id.DropDownBox_content)
        val DropPinner = findViewById<Spinner>(R.id.tab2_DropDownBox)
        var itemList = listOf<String>(item1, item2, item3)
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        DropPinner.adapter = adapter
        DropPinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var context:String = ""
                if(position == 0 ) {
                   context = item1_content

                }
                if(position == 1 ) {
                    context = item2_content

                }
                if(position == 2 ) {
                    context = item3_content
                }
                DropDownBox_content.text = Tool.get().functionText(this@TabTwoActivity,context)
                DropDownBox_content.movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }


}