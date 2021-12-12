package com.example.presentationlayer.users.listing

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.presentationlayer.databinding.FragmentUsersBinding
import com.example.presentationlayer.users.UserUi
import com.example.domainlayer.common.Result
import com.example.presentationlayer.extensions.makeGone
import com.example.presentationlayer.users.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        viewModel.observeUsers().observe(viewLifecycleOwner, { renderView(it) })
        return binding.root
    }


    private fun renderView(users: Result<List<Pair<UserUi,String>>>) {
        when (users) {
            is Result.Loading -> binding.loadingView.startShimmer()
            is Result.Failure -> {
                binding.loadingView.apply {
                    stopShimmer()
                    makeGone()
                }
            }
            is Result.Success -> {
                binding.loadingView.apply {
                    stopShimmer()
                    makeGone()
                }
                binding.usersRecyclerView.adapter = UserAdapter(context = requireContext(),
                    collection = users.data, clickAction = { (userId,cardView) ->
                        val extras = FragmentNavigatorExtras(cardView to userId)
                        val action =
                            UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(userId = userId)

                        findNavController().navigate(action, extras)
                    })
            }
        }
    }
}