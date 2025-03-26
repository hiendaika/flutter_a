package com.example.flutter_a

import io.flutter.embedding.android.FlutterActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity(){
    private val CHANNEL = "flutter_c_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HIENNV","SEND_DATA")
        MethodChannel(flutterEngine!!.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            Log.d("HIENNV","SEND_DATA1")
            if (call.method == "openFlutterC") {
                Log.d("HIENNV","SEND_DATA2")
                openFlutterC()
            } else {
                result.notImplemented()
            }
        }
    }

    fun openFlutterC() {
        Log.d("HIENNV","CAYVKL")
        val intent = FlutterActivity.withNewEngine()
            .initialRoute("home") // Route màn hình cần mở trong Flutter B
            .build(this)
        startActivity(intent)
    }
}
