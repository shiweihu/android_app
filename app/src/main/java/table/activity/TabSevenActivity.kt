package table.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.myapplication.R

class TabSevenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_seven)

        findViewById<ImageButton>(R.id.act_close).setOnClickListener { _ ->
            this@TabSevenActivity.finish()
            overridePendingTransition(0, 0)
        }
    }


}