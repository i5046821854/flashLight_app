package com.example.flashlight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val torch = Torch(this) //torch 클래스 인스턴스화

        flashSwitch.setOnCheckedChangeListener{ buttonView, isChekced ->
            if (isChekced){      //스위치가 켜져있다면 플래쉬 켜기 (서비스를 통해서)
                startService(intentFor<torchService>().setAction("on"))
            }
            else{               //꺼져있다면 플래쉬 끄기
                startService(intentFor<torchService>().setAction("off"))
            }
        }
    }
}

