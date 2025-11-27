package com.tracewave.blockapp


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingActivity : AppCompatActivity() {

    private lateinit var edtPassword: EditText
    private lateinit var btnSave: Button
    private lateinit var btnActivateAdmin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        edtPassword = findViewById(R.id.edtPassword)
        btnSave = findViewById(R.id.btnSavePassword)
        btnActivateAdmin = findViewById(R.id.btnActivateAdmin)

        btnSave.setOnClickListener {
            val password = edtPassword.text.toString()
            if (password.isNotEmpty()) {
                getSharedPreferences("security", MODE_PRIVATE)
                    .edit()
                    .putString("uninstall_password", password)
                    .apply()
                Toast.makeText(this, "Password saved!", Toast.LENGTH_SHORT).show()
            }
        }

        btnActivateAdmin.setOnClickListener {
            DeviceAdminHelper.activateAdmin(this)
        }
    }
}
