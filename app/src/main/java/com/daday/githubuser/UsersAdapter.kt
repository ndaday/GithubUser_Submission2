package com.daday.githubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_users.view.*



class UsersAdapter(private val listData: ArrayList<UserItems>) : RecyclerView.Adapter<UsersAdapter.ListViewHolder>() {

    private var onItemClickCallback: UsersAdapter.OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): UsersAdapter.ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_users, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersAdapter.ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userItems: UserItems) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(userItems.avatar)
                    .apply(RequestOptions().override(55, 55))
                    .into(img_avatar)
                tv_item_username.text = userItems.username
                tv_item_description.text = userItems.url

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(userItems) }
            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(dataUsers: UserItems)
    }

}