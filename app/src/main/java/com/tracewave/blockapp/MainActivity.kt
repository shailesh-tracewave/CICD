package com.tracewave.blockapp


import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: AppAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.btnBlockYoutube)
        recycler.layoutManager = LinearLayoutManager(this)

        if (!isAccessibilityEnabled()) {
            Toast.makeText(this, "Enable Accessibility Service", Toast.LENGTH_LONG).show()
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }

        findViewById<Button>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        val apps = getInstalledApps()
        adapter = AppAdapter(apps, this)
        recycler.adapter = adapter
    }

    private fun getInstalledApps(): List<AppInfo> {
        val pm = packageManager
        val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        val list = mutableListOf<AppInfo>()

        for (app in packages) {
            // Skip system apps
            if ((app.flags and ApplicationInfo.FLAG_SYSTEM) == 0) {
                list.add(
                    AppInfo(
                        app.loadLabel(pm).toString(),
                        app.packageName,
                        app.loadIcon(pm)
                    )
                )
            }
        }
        return list.sortedBy { it.appName.lowercase() }
    }

    private fun isAccessibilityEnabled(): Boolean {
        val prefString = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        )
        return prefString?.contains(packageName) == true
    }
}

