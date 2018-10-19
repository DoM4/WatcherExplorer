package com.domenicoaumenta.watcherexplorer.network

import com.domenicoaumenta.watcherexplorer.model.RepoOwner
import com.domenicoaumenta.watcherexplorer.model.RepoOwnerResponse
import com.domenicoaumenta.watcherexplorer.model.RepositoriesResponse
import com.domenicoaumenta.watcherexplorer.utils.BASE_URL
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by domenicoaumenta on 06/10/2018.
 */
interface GitHubWatcherAPI{

    /*
    * Get list of Sports from API
    * */
    @GET("search/repositories")
    fun searchRepositoriesByKeyword(@Query("q" ) keyWord:String) : Observable<RepositoriesResponse>
    @GET("/repos/{owner}/{repo}/subscribers")
    fun getWatchersByRepo(@Path ("owner") ownerName : String, @Path("repo") repoName : String) : Observable<List<RepoOwner>>

    companion object Factory {
        fun create(): GitHubWatcherAPI {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(GitHubWatcherAPI::class.java)
        }
    }
}