package com.domenicoaumenta.watcherexplorer.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by domenicoaumenta on 14/10/2018.
 */
@Parcelize
data class RepoOwner(
    val login : String,
    val id : Int,
    val node_id : String,
    val avatar_url : String,
    val gravatar_id : String,
    val url : String,
    val received_events_url : String,
    val type : String
) : Parcelable