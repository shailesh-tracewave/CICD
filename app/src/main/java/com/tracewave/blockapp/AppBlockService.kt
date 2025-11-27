package com.tracewave.blockapp

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent

class AppBlockService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val currentApp = event.packageName?.toString() ?: return

            val prefs = getSharedPreferences("blocklist", MODE_PRIVATE)
            val isBlocked = prefs.getBoolean(currentApp, false)

            if (isBlocked) {
                val intent = Intent(this, BlockedActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
            }
        }
    }

    override fun onInterrupt() {}
}
