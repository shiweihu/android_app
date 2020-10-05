package table.activity

import LOCAL.DB.DBManager
import LOCAL.DB.FeedReaderContract
import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import functions.Http
import functions.Tool
import mode.menuItem

class TabSevenActivity : Activity() {
    var dbHelper:DBManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_tab_seven)

        findViewById<ImageButton>(R.id.act_close).setOnClickListener { _ ->
            this@TabSevenActivity.finish()
            overridePendingTransition(0, 0)
        }

        dbHelper = DBManager(this)

    }

    override fun onStart() {
        super.onStart()

        val db = dbHelper?.readableDatabase
        val projection = null
        val selection = null
        val selectionArgs = null
        val sortOrder = ""
        val cursor = db?.query(
            FeedReaderContract.CRISIS_PLAN_ENTRAL.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        var string1:String = ""
        var string2:String = ""
        var string3:String = ""
        var string4:String = ""
        var string5:String = ""
        var string6:String = ""
        var string7:String = ""
        cursor?.let {
            while (it.moveToNext()) {
                string1 =
                    it.getString(it.getColumnIndexOrThrow(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_1))
                string2 =
                    it.getString(it.getColumnIndexOrThrow(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_2))
                string3 =
                    it.getString(it.getColumnIndexOrThrow(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_3))
                string4 =
                    it.getString(it.getColumnIndexOrThrow(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_4))
                string5 =
                    it.getString(it.getColumnIndexOrThrow(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_5))
                string6 =
                    it.getString(it.getColumnIndexOrThrow(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_6))
                string7 =
                    it.getString(it.getColumnIndexOrThrow(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_7))
            }
            it.close()
        }









        val array =  this.resources.getStringArray(R.array.crisis_plan_date)

        var title = array[0]
        var content = array[1]
        var question1 = array[2]
        var question2 = array[3]
        var question3 = array[4]
        var question4 = array[5]
        var question5 = array[6]
        var question6 = array[7]
        var question7 = array[8]
        var other = array[9]

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


        val edit_text1 = findViewById<EditText>(R.id.edit_text1)
        val edit_text2 = findViewById<EditText>(R.id.edit_text2)
        val edit_text3 = findViewById<EditText>(R.id.edit_text3)
        val edit_text4 = findViewById<EditText>(R.id.edit_text4)
        val edit_text5 = findViewById<EditText>(R.id.edit_text5)
        val edit_text6 = findViewById<EditText>(R.id.edit_text6)
        val edit_text7 = findViewById<EditText>(R.id.edit_text7)
        edit_text1.text  = SpannableStringBuilder(string1)
        edit_text2.text  = SpannableStringBuilder(string2)
        edit_text3.text  = SpannableStringBuilder(string3)
        edit_text4.text  = SpannableStringBuilder(string4)
        edit_text5.text  = SpannableStringBuilder(string5)
        edit_text6.text  = SpannableStringBuilder(string6)
        edit_text7.text  = SpannableStringBuilder(string7)



        val submit_but = findViewById<Button>(R.id.submit_but)

        submit_but.setOnClickListener{
            val db =  dbHelper?.writableDatabase
            val values = ContentValues().apply {
                put(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_1, edit_text1.text.toString())
                put(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_2, edit_text2.text.toString())
                put(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_3, edit_text3.text.toString())
                put(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_4, edit_text4.text.toString())
                put(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_5, edit_text5.text.toString())
                put(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_6, edit_text6.text.toString())
                put(FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_7, edit_text7.text.toString())
            }
            val newRowId = db?.insert(FeedReaderContract.CRISIS_PLAN_ENTRAL.TABLE_NAME, null, values)
            if(newRowId != null)
            {
                Toast.makeText(this,R.string.create_plan_successfully,Toast.LENGTH_SHORT).show()
            }else
            {
                Toast.makeText(this,R.string.create_plan_unsuccessfully,Toast.LENGTH_SHORT).show()
            }
        }



    }


}