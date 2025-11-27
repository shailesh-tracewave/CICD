package com.tracewave.blockapp


import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyDeviceAdminReceiver : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        Toast.makeText(context, "Admin permission enabled", Toast.LENGTH_SHORT).show()
    }

    override fun onDisabled(context: Context, intent: Intent) {
        Toast.makeText(context, "Admin permission disabled", Toast.LENGTH_SHORT).show()
    }
    override fun onDisableRequested(context: Context, intent: Intent): CharSequence? {
        val prefs = context.getSharedPreferences("security", Context.MODE_PRIVATE)
        val password = prefs.getString("uninstall_password", null)

        if (password != null) {
            // Show password check Activity
            val dialogIntent = Intent(context, VerifyPasswordActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(dialogIntent)
            return "Enter password to deactivate protection."
        }

        return super.onDisableRequested(context, intent)
    }

}
