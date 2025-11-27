package com.tracewave.blockapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BlockedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blocked)

        findViewById<TextView>(R.id.tvMessage).text =
            "🚫 YouTube is blocked by Parent app.\nTry again later."
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Prevent back navigation
        return
    }
}
