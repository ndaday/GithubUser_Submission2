package com.daday.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.LoginFilter
import android.widget.Toolbar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_user.*

class DetailUser : AppCompatActivity() {


    companion object {
        const val ARG_USERNAME = "username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        vPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(vPager)

        supportActionBar?.elevation = 0f

        setData()
    }

    private fun setActionBar(title: String) {
        if (supportActionBar != null)
            this.title = title
    }

    private fun setData() {
        val userItem = intent.getParcelableExtra(ARG_USERNAME) as UserItems
        userItem.username?.let { setActionBar(it) }
        tvName.text = userItem.name
        tvUname.text = userItem.username
        tvCompany.text = userItem.company
        tvLocation.text = userItem.location
        tvCountFollower.text = userItem.followers.toString()
        tvCountFollowing.text = userItem.following.toString()
        Glide.with(this)
            .load(userItem.avatar)
            .into(img_avatar)
    }
}