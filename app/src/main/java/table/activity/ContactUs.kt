package table.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.example.myapplication.R
import functions.Tool


class ContactUs : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contact_us)

        this.findViewById<ImageButton>(R.id.act_close).setOnClickListener{_->
            this.finish()
        }

        val descriptionBut = this.findViewById<TextView>(R.id.description)
        descriptionBut.text = Tool.get().functionText(this,resources.getString(R.string.contact_description));
        descriptionBut.movementMethod = LinkMovementMethod.getInstance()

        val name_edit = this.findViewById<EditText>(R.id.client_name_edit)
        val message_edit = this.findViewById<EditText>(R.id.message_edit)
        val send_email = this.findViewById<Button>(R.id.submit_but)
        send_email.setOnClickListener{_->
            if(name_edit.text.toString().isEmpty())
            {
                 Toast.makeText(this,R.string.toast_input_name,Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(message_edit.text.toString().isEmpty())
            {
                Toast.makeText(this,R.string.toast_input_message,Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Tool.get().sendEmail(this,message_edit.text.toString(),name_edit.text.toString())
        }

    }
}