package com.example.myapplication

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

interface CallBack<T>
{
    fun callback(result:T)
}

class ThreadManager private constructor(){
    private var executor:ThreadPoolExecutor =ThreadPoolExecutor(1,3,10, TimeUnit.SECONDS,
        LinkedBlockingQueue<Runnable>())
    companion object
    {
        public var instance:ThreadManager? = null
        get()
        {
            if(field == null)
                field = ThreadManager()
            return field
        }
        fun get():ThreadManager
        {
            return instance!!
        }
    }
    fun execute(task:Runnable)
    {
        executor.execute(task)
    }




}