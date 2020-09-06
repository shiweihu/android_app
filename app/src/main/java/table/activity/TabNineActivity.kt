package table.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.*
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import functions.Tool
import mode.menuItem

class TabNineActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_nine)

        findViewById<ImageButton>(R.id.act_close).setOnClickListener { _ ->
            this@TabNineActivity.finish()
            overridePendingTransition(0, 0)
        }
    }



    override fun onStart() {
        super.onStart()

        var myApp =  this.application as MyApplication
        val tab = myApp.menuListL?.get(8) as menuItem


        var title = tab.title


        findViewById<TextView>(R.id.tab_title).text = title



        var item1 =  tab.getDropDownBoxByIndex(0).title
        var item2 = tab.getDropDownBoxByIndex(1).title
        var item3 = tab.getDropDownBoxByIndex(2).title
        var item4 = tab.getDropDownBoxByIndex(3).title

        val item1_content = tab.getDropDownBoxByIndex(0).content
        val item2_content =tab.getDropDownBoxByIndex(1).content
        val item3_content =tab.getDropDownBoxByIndex(2).content
        val item4_content =tab.getDropDownBoxByIndex(3).content

        val DropDownBox_content = findViewById<TextView>(R.id.DropDownBox_content)
        val DropPinner = findViewById<Spinner>(R.id.tab_DropDownBox)
        var itemList = listOf<String>(item1, item2,item3,item4)
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
                if(position == 3 ) {
                    context = item4_content

                }

                DropDownBox_content.text = Tool.get().functionText(this@TabNineActivity,context)
                DropDownBox_content.movementMethod = LinkMovementMethod.getInstance()
            }
        }


    }



}