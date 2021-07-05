package com.example.flashlight

import android.app.Service
import android.content.Intent
import android.os.IBinder

class torchService : Service() {

    private val torch : Torch by lazy{ //torch 객체를 처음 사용할 때 초기화됨
        Torch(this)
    }

    private var isRunning = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) { //앱에서 실행할 경우
            "on" -> {
                torch.flashOn()
                isRunning = true
            }
            "off" -> {
                torch.flashOff()
                isRunning = false
            }
            else -> {  //서비스에서 실행할 경우
                isRunning  = !isRunning
                if(isRunning)
                {
                    torch.flashOn()
                }
                else
                {
                    torch.flashOff()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


}