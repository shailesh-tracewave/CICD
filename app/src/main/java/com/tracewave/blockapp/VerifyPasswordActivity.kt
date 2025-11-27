package com.tracewave.blockapp


import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VerifyPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_password)

        val edt = findViewById<EditText>(R.id.edtVerifyPassword)
        val btn = findViewById<Button>(R.id.btnVerify)

        btn.setOnClickListener {
            val entered = edt.text.toString()
            val saved = getSharedPreferences("security", MODE_PRIVATE)
                .getString("uninstall_password", "")

            if (entered == saved) {
                Toast.makeText(this, "Password correct — admin can be disabled now.", Toast.LENGTH_SHORT).show()

                val dpm = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
                val admin = ComponentName(this, MyDeviceAdminReceiver::class.java)
                dpm.removeActiveAdmin(admin)

                finish()
            } else {
                Toast.makeText(this, "Wrong password!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
