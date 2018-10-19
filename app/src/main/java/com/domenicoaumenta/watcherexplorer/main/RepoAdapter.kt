package com.domenicoaumenta.watcherexplorer.main

import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.domenicoaumenta.watcherexplorer.R
import com.domenicoaumenta.watcherexplorer.main.RepoAdapter.RepoViewHolder
import com.domenicoaumenta.watcherexplorer.model.RepoObject
import com.domenicoaumenta.watcherexplorer.utils.launchWatcherActivity
import com.domenicoaumenta.watcherexplorer.watcher.REPO_OBJECT
import com.domenicoaumenta.watcherexplorer.watcher.WatcherActivity
import kotlinx.android.synthetic.main.repo_layout_item.view.*
import org.jetbrains.anko.startActivity


/**
 * Created by domenicoaumenta on 14/10/2018.
 */

class RepoAdapter(private val context : Context, repoList: List<RepoObject>?) : RecyclerView.Adapter<RepoViewHolder>(){

     var repoList : List<RepoObject>? = repoList
         set(value) {
            field = value
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepoViewHolder {
        return RepoViewHolder(LayoutInflater.from(context).inflate(R.layout.repo_layout_item,p0,false))
    }

    override fun getItemCount(): Int {
        return if(repoList == null) 0 else repoList!!.size
    }

    override fun onBindViewHolder(p0: RepoViewHolder, p1: Int) {
        p0.bind(repoList?.get(p1))
    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repoObject: RepoObject?){
            itemView.repoTitle.text = repoObject?.name
            itemView.repoDescription.text = repoObject?.description
            itemView.repoWatchers.text = String.format("Watchers : ${repoObject?.watchers_count}")

            itemView.setOnClickListener {
                itemView.context.startActivity(itemView.context.launchWatcherActivity(repoObject))
            }
        }
    }
}
