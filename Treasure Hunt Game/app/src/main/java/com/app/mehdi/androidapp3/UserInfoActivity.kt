package com.app.mehdi.androidapp3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class UserInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        findViewById<Button>(R.id.submit_button).setOnClickListener {
            val name = findViewById<EditText>(R.id.name_input).text.toString()
            val phoneNumber = findViewById<EditText>(R.id.phone_input).text.toString()

            if (name.isNotEmpty() && phoneNumber.isNotEmpty()) {
                val intent = Intent(this, MapsActivity::class.java).apply {
                    putExtra("USER_NAME", name)
                    putExtra("USER_PHONE", phoneNumber)
                }
                startActivity(intent)
                finish()
            }
        }
    }
}
