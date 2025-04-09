package com.example.myapplication.iu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzautotest.NavigationManager
import com.example.finanzautotest.SwipeToDeleteCallback
import com.example.finanzautotest.databinding.FragmentListUserBinding
import com.example.myapplication.iu.helper.UserListUiStateHandler
import com.example.myapplication.iu.helper.UserListViewHelper
import com.example.myapplication.iu.adapter.UserAdapter
import com.example.myapplication.iu.viewModel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment(), UserAdapter.OnUserClickListener {

    private lateinit var binding: FragmentListUserBinding

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var uiStateHandler: UserListUiStateHandler
    private lateinit var userListViewHelper: UserListViewHelper

    private var isFabVisible = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        uiStateHandler = UserListUiStateHandler(binding, userListViewHelper)
        initListener()
        setupObserver()
        fragmentResultListener()
        setupFabScrollBehavior()
    }

    private fun fragmentResultListener() {
        setFragmentResultListener("userKey") { _, bundle ->
            viewModel.loadData()
        }
    }

    private fun initRecyclerView() {
        userListViewHelper = UserListViewHelper(binding.rcvUser, requireContext(), this)
        userListViewHelper.setUp()
        setUpSwipeToDelete()
    }

    private fun setUpSwipeToDelete() {
        val swipeCallback = SwipeToDeleteCallback(
            context = requireContext(),
            adapter = userListViewHelper.adapter,
            onDeleteConfirmed = { user, position ->
                onUserClicked(user.id)
                userListViewHelper.adapter.removeUserAt(position)
            }
        )
        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.rcvUser)
    }

    private fun setupObserver() {
        viewModel.getDataUserState().observe(viewLifecycleOwner) { state ->
            uiStateHandler.render(state)
        }
    }

    private fun initListener() {
        binding.addButton.setOnClickListener {
            NavigationManager.goToRegisterUser(this)
        }
    }

    override fun onUserClicked(id: String) {
        viewModel.deleteUser(id)
    }

    override fun onViewDetailClicked(id: String) {
        NavigationManager.goToUserDetail(this, id)
    }

    private fun setupFabScrollBehavior() {
        binding.rcvUser.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 10 && isFabVisible) {
                    hideFabWithSlide()
                } else if (dy < -10 && !isFabVisible) {
                    showFabWithSlide()
                }
            }
        })
    }

    private fun hideFabWithSlide() {
        binding.addButton.animate()
            .translationY(binding.addButton.height.toFloat() + 100f)
            .setDuration(200)
            .withEndAction { isFabVisible = false }
            .start()
    }

    private fun showFabWithSlide() {
        binding.addButton.animate()
            .translationY(0f)
            .setDuration(200)
            .withEndAction { isFabVisible = true }
            .start()
    }
}