package com.daday.githubuser

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserItems(
    var name: String? = null,
    var username: String? = null,
    var avatar: String? = null,
    var url: String? = null,
    var company: String? = null,
    var location: String? = null,
    var followers: Int? = null,
    var following: Int? = null
) : Parcelable