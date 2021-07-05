package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

class Torch(context: Context) {
    private var cameraId: String? = null
    private var cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    init {
        cameraId = getCameraId()
    }

    fun flashOn (){  //플래쉬 켜기
        cameraId?.let { cameraManager.setTorchMode(it, true) }
    }

    fun flashOff (){ //플래쉬 끄기
        cameraId?.let { cameraManager.setTorchMode(it, false) }
    }

    private fun getCameraId(): String?{
        val cameraIds = cameraManager.cameraIdList
        for(id in cameraIds)
        {
            val info = cameraManager.getCameraCharacteristics(id)
            val flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE) //플래쉬 사용 가능
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING) // 렌즈가 뒤를 향해 있음
            if(flashAvailable != null && flashAvailable && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK){
                return id
            }
        }
        return null
    }
}