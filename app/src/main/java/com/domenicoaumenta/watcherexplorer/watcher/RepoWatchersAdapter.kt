package com.domenicoaumenta.watcherexplorer.watcher

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.domenicoaumenta.watcherexplorer.R
import com.domenicoaumenta.watcherexplorer.model.RepoOwner
import kotlinx.android.synthetic.main.repo_owner_layout_item.view.*


/**
 * Created by domenicoaumenta on 16/10/2018.
 */

class RepoWatchersAdapter(private val context : Context, repoList: List<RepoOwner>?) : RecyclerView.Adapter<RepoWatchersAdapter.RepoWatcherViewHolder>(){
    override fun onBindViewHolder(p0: RepoWatcherViewHolder, p1: Int) {
        p0.bind(repoList?.get(p1))
    }

    var repoList : List<RepoOwner>? = repoList
        set(value) {
            field = value
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepoWatcherViewHolder {
        return RepoWatcherViewHolder(LayoutInflater.from(context).inflate(R.layout.repo_owner_layout_item,p0,false))
    }

    override fun getItemCount(): Int {
        return if(repoList == null) 0 else repoList!!.size
    }


    class RepoWatcherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repoOwner: RepoOwner?){
            itemView.repoOwnerName.text = repoOwner?.login
            itemView.repoOwnerUrl.text = repoOwner?.url
            Glide.with(itemView.context)
                .load(repoOwner?.avatar_url)
                .apply(RequestOptions.circleCropTransform())
                .into(itemView.repoOwnerImage)
        }
    }
}
