package functions

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Toast


class Tool {


    public fun call(context: Context, num: String)
    {
        val intent = Intent(Intent.ACTION_DIAL)
        val data = Uri.parse("tel:$num")
        intent.data = data
        context.startActivity(intent)
    }
    public fun functionText(context: Context, str: String):SpannableStringBuilder
    {
        val style = SpannableStringBuilder()
        var index  = str.indexOf("\${")
        var indexEnd = -1
        if(index==-1)
        {
            style.append(str.substring(indexEnd + 1, str.length))
        }else
        {
            while(index!=-1)
            {
                var temp_text = str.substring(indexEnd + 1, index)
                style.append(temp_text)
                indexEnd =  str.indexOf("}", index)
                var funtion  = str.substring(index + 2, indexEnd)

                val  attribute =  funtion.split("|")
                val type_map=  attribute[0].split("=")
                val title_map=  attribute[1].split("=")
                val content_map=  attribute[2].split("=")
                val typeface_map = attribute[3].split("=")
                val fontcolor_map = attribute[4].split("=")
                var clickableSpan: ClickableSpan?=null
                var colorSpan: ForegroundColorSpan?=  ForegroundColorSpan(
                    Color.parseColor(
                        fontcolor_map[1]
                    )
                )
                var fontSpan:StyleSpan = StyleSpan(Typeface.NORMAL)


                when(typeface_map[1])
                {
                    "NORMAL" -> {
                        fontSpan = StyleSpan(Typeface.NORMAL)
                    }
                    "BOLD_ITALIC" -> {
                        fontSpan = StyleSpan(Typeface.BOLD_ITALIC)
                    }
                    "BOLD" -> {
                        fontSpan = StyleSpan(Typeface.BOLD)
                    }
                    "ITALIC" -> {
                        fontSpan = StyleSpan(Typeface.ITALIC)
                    }
                }



                when(type_map[1])
                {
                    "1" -> {
                        clickableSpan = object : ClickableSpan() {
                            override fun onClick(widget: View) {
                                this@Tool.call(context, content_map[1])

                            }
                            override fun updateDrawState(ds: TextPaint) {
                                ds.setUnderlineText(false)
                            }
                        }

                    }
                    "2" -> {
                        clickableSpan = object : ClickableSpan() {
                            override fun onClick(widget: View) {
                                this@Tool.accessWeb(context, content_map[1])
                            }

                            override fun updateDrawState(ds: TextPaint) {
                                ds.setUnderlineText(true)
                            }
                        }
                    }
                }
                style.append(title_map[1])
                style.setSpan(
                    clickableSpan,
                    style.length - title_map[1].length,
                    style.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                style.setSpan(
                    colorSpan,
                    style.length - title_map[1].length,
                    style.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                style.setSpan(
                    fontSpan,
                    style.length - title_map[1].length,
                    style.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                index  = str.indexOf("\${", indexEnd)
                if(index==-1)
                {
                    style.append(str.substring(indexEnd + 1, str.length))
                }
            }
        }

      return style
    }

   public fun accessWeb(context: Context, url: String)
   {
       val intent = Intent()
       intent.action = Intent.ACTION_VIEW
       intent.data = Uri.parse(url)
       // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
       // 官方解释 : Name of the component implementing an activity that can display the intent
       // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
       // 官方解释 : Name of the component implementing an activity that can display the intent
       if (intent.resolveActivity(context?.packageManager) != null) {
           val componentName = intent.resolveActivity(context?.packageManager)
           // 打印Log   ComponentName到底是什么

           context?.startActivity(Intent.createChooser(intent, "select a browser"))
       } else {
           Toast.makeText(
               context?.applicationContext,
               "please,download a browser",
               Toast.LENGTH_SHORT
           ).show()
       }
   }


    companion object {
        private var instance: Tool? = null
            get()
            {
                if(field == null)
                    field = Tool()
                return field
            }
        fun get(): Tool
        {
            return instance!!
        }
    }
}