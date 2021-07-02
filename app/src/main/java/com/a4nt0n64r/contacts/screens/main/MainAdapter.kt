package com.a4nt0n64r.contacts.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.a4nt0n64r.contacts.R
import com.a4nt0n64r.contacts.models.SimpleUser
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var listUsers = emptyList<SimpleUser>()

    fun setData(list: List<SimpleUser>) {
        listUsers = list
        notifyDataSetChanged()
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = itemView.findViewById<TextView>(R.id.item_profile_name)
        val surname = itemView.findViewById<TextView>(R.id.item_profile_surname)
        val image = itemView.findViewById<ImageView>(R.id.item_profile_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.name.text = listUsers[position].name
        holder.surname.text = listUsers[position].surname
        Glide.with(holder.image.context).load(listUsers[position].smallImgLink).into(holder.image)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            MainFragment.click(listUsers[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }
}
