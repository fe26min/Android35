package com.fe26min.part2.part2.chapter04.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fe26min.part2.part2.chapter04.databinding.ItemUserBinding
import com.fe26min.part2.part2.chapter04.model.User

class UserAdapter : ListAdapter<User, UserAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val viewBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
            fun bind(item: User) {
                viewBinding.usernameTextView.text = item.username
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }

}