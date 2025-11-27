package com.tracewave.blockapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(private val apps: List<AppInfo>, private val context: Context)
    : RecyclerView.Adapter<AppAdapter.ViewHolder>() {

    private val prefs = context.getSharedPreferences("blocklist", Context.MODE_PRIVATE)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.imgAppIcon)
        val name: TextView = view.findViewById(R.id.txtAppName)
        val toggle: Switch = view.findViewById(R.id.switchBlock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_app_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = apps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = apps[position]
        holder.icon.setImageDrawable(app.icon)
        holder.name.text = app.appName
        holder.toggle.isChecked = prefs.getBoolean(app.packageName, false)

        holder.toggle.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            prefs.edit().putBoolean(app.packageName, isChecked).apply()
        }
    }
}
