package table.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import com.example.myapplication.R
import functions.Tool


class ContactUs : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tab_eight)
        setContentView(R.layout.activity_contact_us)

        this.findViewById<ImageButton>(R.id.act_close).setOnClickListener{_->
            this.finish()
        }

        val descriptionBut = this.findViewById<TextView>(R.id.description)
        descriptionBut.text = Tool.get().functionText(this,resources.getString(R.string.contact_description));
        descriptionBut.movementMethod = LinkMovementMethod.getInstance()

    }
}