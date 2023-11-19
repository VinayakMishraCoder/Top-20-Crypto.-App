package com.example.gweiland_top20crypto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityLoginBinding
//    private val vm: LoginViewModel by lazy {
//        ViewModelProvider(this).get(LoginViewModel::class.java)
//    }
//    private val launchThreadsActivity = registerForActivityResult(ThreadsActivity) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    data class LaunchParams(
        val source: String? = null
    )

    companion object : ActivityResultContract<LaunchParams, Boolean>() {
        var SOURCE = "source"
        var VERIFY = "verify"

        override fun createIntent(context: Context, input: LaunchParams): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(SOURCE, input.source)
                if (input.source == VERIFY) {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }
}