package com.daday.githubuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_follower.*

class FollowerFragment : Fragment() {

    private lateinit var adapter: UserAdapter
    private lateinit var mainViewModel: MainViewModel
    private val listData = ArrayList<UserItems>()

    companion object {
        private val ARG_USERNAME = "username"

        fun newInstance(username: String?): FollowerFragment {
            val fragment = FollowerFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follower, container, false)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        showRecyclerview()
    }

    private fun showRecyclerview() {
        rv_follower.layoutManager = LinearLayoutManager(activity)
        rv_follower.adapter = adapter

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserItems) {
                showSelectedUser(data)
            }
        })

        showLoading(true)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        mainViewModel.setUsers()

//        mainViewModel.getUsers().observe(this, { userItems ->
//            if (userItems != null) {
//                adapter.setData(userItems)
//                showLoading(false)
//            }
//        })
    }

    private fun showSelectedUser(userItems: UserItems) {
        UserItems(
            userItems.name,
            userItems.username,
            userItems.avatar,
            userItems.company,
            userItems.location,
            userItems.followers.toString(),
            userItems.following
        )
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val username = arguments?.getString(ARG_USERNAME)
    }
}