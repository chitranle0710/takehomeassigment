package com.example.tymextakehomeapp.presentation.ui.listUser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tymextakehomeapp.databinding.ItemUserBinding
import com.example.tymextakehomeapp.domain.model.DTOUser

/**
 * PagingDataAdapter for displaying paged GitHub users.
 *
 * @param onClick Callback triggered when a user item is clicked.
 */
class UserPagingAdapter(
    private val onClick: (dtoUser: DTOUser) -> Unit
) : PagingDataAdapter<DTOUser, UserPagingAdapter.UserViewHolder>(
    UserComparator
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: DTOUser) {
            binding.apply {
                root.setOnClickListener {
                    onClick.invoke(user)
                }
                tvUserName.text = user.login
                tvName.text = user.name ?: "N/A"
                Glide.with(imgAvatar)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(imgAvatar)
            }
        }
    }

    /**
     * Comparator used by PagingDataAdapter to determine item differences.
     */
    object UserComparator : DiffUtil.ItemCallback<DTOUser>() {
        override fun areItemsTheSame(oldItem: DTOUser, newItem: DTOUser): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DTOUser, newItem: DTOUser): Boolean =
            oldItem == newItem
    }
}
