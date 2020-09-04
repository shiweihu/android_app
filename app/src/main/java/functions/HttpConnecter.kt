package functions


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

class Http {
    private data class SocketPack constructor(var response: Response?, var strData:String?)
    {

    }
    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
           var socketPack = msg.obj as SocketPack
            socketPack.response?.notification(socketPack.strData)
        }
    }


    fun doGet(httpurl: String, response: Response?) {
        ThreadManager.get().execute(Runnable {
            val result = request(httpurl)
            var message = Message()
            message.obj = SocketPack(response,result)
            handler.sendMessage(message)
        })
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
                    if(sbf.isNotEmpty())
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
            if (null != br) {
                try {
                    br.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (null != `is`) {
                try {
                    `is`.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            connection!!.disconnect()
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
