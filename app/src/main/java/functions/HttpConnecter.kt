package functions


import View.ProgersssDialog
import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


interface Response {
    fun notification(s: String?)
}
interface Response_binary
{
    fun notification(inputS: InputStream?)
}

class Http {
    private data class SocketPack constructor(var response: Response?,var response_binary:Response_binary?, var strData: String?,val inputS: InputStream?)
    {

    }
    var dialog:ProgersssDialog? = null

    private val handler = Handler { msg->
        var socketPack = msg.obj as SocketPack
        socketPack?.response?.notification(socketPack.strData)
        dialog?.dismiss()
        false
    }
    private val handler_binary = Handler { msg->
        var socketPack = msg.obj as SocketPack
        socketPack?.response_binary?.notification(socketPack.inputS)
        dialog?.dismiss()
        false
    }


//    private val handler: Handler = @SuppressLint("HandlerLeak")
//    object : Handler() {
//        override fun handleMessage(msg: Message) {
//            var socketPack = msg.obj as SocketPack
//            socketPack?.response?.notification(socketPack.strData)
//            dialog?.dismiss()
//        }
//    }

    fun doGetBinary(httpurl: String,responseBinary: Response_binary)
    {
        ThreadManager.get().execute(Runnable {
            val result = requestBinary(httpurl)
            var message = Message()
            message.obj = SocketPack(null,responseBinary,null,result)
            handler_binary.sendMessage(message)
        })
    }

    fun doGet(httpurl: String, response: Response?) {
        ThreadManager.get().execute(Runnable {
            val result = request(httpurl)
            var message = Message()
            message.obj = SocketPack(response,null, result,null)
            handler.sendMessage(message)
        })
    }

    fun doGetWithDialog(context: Context, httpurl: String, response: Response?)
    {
        dialog = ProgersssDialog(context)
        dialog?.show()
        doGet(httpurl, response)
    }

    private fun requestBinary(httpurl: String):InputStream?
    {
        var connection: HttpURLConnection? = null
        val url = URL(httpurl)
        connection = url.openConnection() as HttpURLConnection
        connection!!.requestMethod = "GET"
        connection.connectTimeout = 15000
        connection.readTimeout = 60000
        connection.instanceFollowRedirects = false
        connection.connect()
        val responseCode = connection.responseCode
        if (responseCode == 200)
        {
          return connection.inputStream;
        }
        return null
    }

    private fun request(httpurl: String): String? {
        var connection: HttpURLConnection? = null
        var `is`: InputStream? = null
        var br: BufferedReader? = null
        var result: String? = null
        try {
            val url = URL(httpurl)
            connection = url.openConnection() as HttpURLConnection
            connection!!.requestMethod = "GET"
            connection.connectTimeout = 15000
            connection.readTimeout = 60000
            connection.instanceFollowRedirects = false
            connection.connect()
            val responseCode = connection.responseCode
            if (responseCode == 200) {
                `is` = connection.inputStream
                br = BufferedReader(InputStreamReader(`is`, "UTF-8"))
                val sbf = StringBuffer()
                var temp: String? = null
                while (br.readLine().also { temp = it } != null) {
                    if(sbf.isNotEmpty() && temp?.isNotEmpty() ?:false )
                    {
                        sbf.append("\r\n")
                    }
                    sbf.append(temp)
                }
                result = sbf.toString()
            } else if (responseCode == 301) {
                val location = connection.getHeaderField("Location")
                connection.disconnect()
                return request(location)
            } else {
                println("response code :$responseCode")
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            br?.let { br.close() }
            `is`?.let {  `is`.close() }
            connection?.disconnect()
        }
        return result
    }

    companion object {
        private var instance: Http? = null
            get()
            {
                if(field == null)
                    field = Http()
                return field
            }
        fun get(): Http
        {
            return instance!!
        }

    }
}
