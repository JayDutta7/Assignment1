package com.test.assignment.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.assignment.R
import com.test.assignment.network.entity.AppList
import java.util.Locale

class ApplicationListAdapter(
    private val context: Context,
    private var dataItems: ArrayList<AppList>? = null,
    private val listener: SelectedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var list: ArrayList<AppList> = arrayListOf()


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtEntityName: TextView = view.findViewById(R.id.applicationName)
        val iconImg: ImageView = view.findViewById(R.id.iconImg)
        val switch: SwitchCompat = view.findViewById(R.id.material_switch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.child_application_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataItems?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.txtEntityName.text = dataItems?.get(position)?.appName
        context.let {
            Glide.with(it).load(dataItems?.get(position)?.appIcon)
                .error(R.mipmap.ic_launcher)
                .into(holder.iconImg)
        }
        holder.switch.isChecked = dataItems?.get(position)?.isSelect ?: false

        holder.switch.setOnClickListener {
            if (dataItems?.get(position)?.isSelect == true) {
                Log.e("switch", "On")
                setChecked(false, position)
            } else {
                Log.e("switch", "Off")
                setChecked(true, position)
            }
        }
    }

    private fun setChecked(
        value: Boolean,
        position: Int
    ) {
        dataItems?.get(position)?.isSelect = (value)
        listener.selectItems(value, position)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filter = FilterResults()
                if (constraint != null && !dataItems.isNullOrEmpty()) {
                    val result: ArrayList<AppList> = ArrayList()
                    if (list.size == 0) {
                        list = dataItems as ArrayList<AppList>
                    }
                    if (list.size > 0) {
                        for (row in list) {
                            if (row.appName?.lowercase(Locale.ROOT)
                                    ?.contains(constraint.toString())!!
                            )
                                result.add(row)
                        }
                    }
                    filter.values = result
                }
                return filter
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                dataItems = filterResults?.values as ArrayList<AppList>
                notifyDataSetChanged()
            }

        }


    }
}