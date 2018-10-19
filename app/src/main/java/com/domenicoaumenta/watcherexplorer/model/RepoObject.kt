package com.domenicoaumenta.watcherexplorer.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoObject(
    val id : Int,
    val node_id : String,
    val name: String,
    val full_name: String,
    val owner : RepoOwner,
    val private: Boolean,
    val html_url : String,
    val description: String,
    val fork: Boolean,
    val url: String,
    val created_at : String,
    val updated_at: String,
    val pushed_at: String,
    val homepage: String,
    val size: Int,
    val stargazers_count: Int,
    val watchers_count: Int,
    val language: String,
    val forks_count: Int,
    val open_issues_count: Int,
    val master_branch: String?,
    val default_branch: String?,
    val score: Double = 0.0
) : Parcelable
