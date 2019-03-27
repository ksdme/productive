package xyz.ksdme.productive.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.ksdme.productive.R

class AppDrawerItems: RecyclerView.Adapter<AppDrawerItems.AppDrawerHolder>() {

    private val logTag = "AppDrawerItems"

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AppDrawerHolder {
        val inflated = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_app_drawer_item, parent, false)

        return AppDrawerHolder(inflated)
    }

    override fun getItemCount(): Int {
        return 30
    }

    override fun onBindViewHolder(p0: AppDrawerHolder, p1: Int) {
        Log.d(this.logTag, "onBindViewHolder")
    }

    class AppDrawerHolder(view: View):
        RecyclerView.ViewHolder(view)

}
