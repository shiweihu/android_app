package table.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.myapplication.R

class carer_tips : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carer_tips)
    }

    override fun onStart() {
        super.onStart()

        val listview =  this.findViewById<ListView>(R.id.list_view)


    }
}