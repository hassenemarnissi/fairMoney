package com.example.presentationlayer.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.domainlayer.common.Result
import com.example.presentationlayer.databinding.FragmentUserDetailsBinding
import com.example.presentationlayer.extensions.makeGone
import com.example.presentationlayer.users.UserUi
import com.example.presentationlayer.users.UserViewModel
import com.example.presentationlayer.users.listing.UserAdapter
import com.example.presentationlayer.users.listing.UsersFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

private const val PARCELABLE_KEY = "USER_ID_PARCELABLE"

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private val viewModel: UserViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        arguments?.let {
            val userId = it.getString(PARCELABLE_KEY)
            userId?.let { userId -> viewModel.getUserDetails(userId) }
            viewModel.observeUserDetails().observe(viewLifecycleOwner, { renderView(it) })

        }
        return binding.root
    }
    private fun renderView(userDetails: Result<UserDetailsUi>) {
        when (userDetails) {
            is Result.Loading -> binding.shimmerLayout.startShimmer()
            is Result.Failure -> {
                binding.shimmerLayout.apply {
                    stopShimmer()
                    makeGone()
                }
            }
            is Result.Success -> {
                binding.shimmerLayout.apply {
                    stopShimmer()
                    makeGone()
                }
                binding.userDetailsUi = userDetails.data
            }
        }
    }

}
