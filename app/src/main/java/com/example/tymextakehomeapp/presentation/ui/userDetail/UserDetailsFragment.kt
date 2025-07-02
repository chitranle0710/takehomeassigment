package com.example.tymextakehomeapp.presentation.ui.userDetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tymextakehomeapp.R
import com.example.tymextakehomeapp.data.remote.dto.User
import com.example.tymextakehomeapp.databinding.FragmentListUserBinding
import com.example.tymextakehomeapp.databinding.FragmentUserDetailsBinding
import com.example.tymextakehomeapp.domain.model.DTOUser
import com.example.tymextakehomeapp.presentation.ui.listUser.ListUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserDetailViewModel by viewModels()
    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = args.userId
        viewModel.loadUserDetail(userId)
        observerData()
    }

    private fun observerData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect { user ->
                    user?.let {
                        initData(it)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initData(user: DTOUser) {
        with(binding) {
            tvUserName.text = user.login
            tvName.text = user.name ?: "N/A"
            Glide.with(imgAvatar)
                .load(user.avatarUrl)
                .circleCrop()
                .into(imgAvatar)


            tvFollowersData.text = "${user.followers}+"
            tvFollowingData.text = "${user.following}+"


            tvBlogLink.text = user.htmlUrl

        }
    }
}