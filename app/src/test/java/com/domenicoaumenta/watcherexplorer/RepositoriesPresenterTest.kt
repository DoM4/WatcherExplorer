package com.domenicoaumenta.watcherexplorer

import com.domenicoaumenta.watcherexplorer.model.RepoObject
import com.domenicoaumenta.watcherexplorer.model.RepositoriesResponse
import com.domenicoaumenta.watcherexplorer.network.GitHubWatcherAPI
import com.domenicoaumenta.watcherexplorer.repositories.RepositoriesContract
import com.domenicoaumenta.watcherexplorer.repositories.RepositoriesPresenter
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.doReturn
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


/**
 * Created by domenicoaumenta on 20/10/2018.
 */
class RepositoriesPresenterTest{

    @InjectMocks lateinit var presenter : RepositoriesPresenter

    @Mock
    lateinit var view : RepositoriesContract.View

    lateinit var repositoriesResponse : RepositoriesResponse

    @Mock
    lateinit var gitHubWatcherAPI : GitHubWatcherAPI

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter.attach(view)
        presenter.subscribe()
    }

    // 1
    @Test
    fun testShowLoading() {
        presenter.loadData("kotlin")

        verify(view).showProgress(true)
    }

    @Test
    fun testSearchRepo() {

        val searchKey = "kotlin"

        repositoriesResponse = RepositoriesResponse(3,false,getRepoObject())

        doReturn(Observable.just(repositoriesResponse)).`when`(gitHubWatcherAPI).searchRepositoriesByKeyword(searchKey)

        presenter.loadData(searchKey)

        verify(view).loadDataSuccess(repositoriesResponse.items)
    }


    private fun getRepoObject() : List<RepoObject>{
        return Gson().fromJson<List<RepoObject>>(REPO_LIST, mutableListOf<RepoObject>()::class.java)
    }
}

const val REPO_LIST = """
    [
    {
      "id": 3432266,
      "node_id": "MDEwOlJlcG9zaXRvcnkzNDMyMjY2",
      "name": "kotlin",
      "full_name": "JetBrains/kotlin",
      "private": false,
      "owner": {
        "login": "nekocode",
        "id": 878437,
        "node_id": "MDEyOk9yZ2FuaXphdGlvbjg3ODQzNw==",
        "avatar_url": "https://avatars2.githubusercontent.com/u/878437?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/JetBrains",
        "html_url": "https://github.com/JetBrains",
        "followers_url": "https://api.github.com/users/JetBrains/followers",
        "following_url": "https://api.github.com/users/JetBrains/following{/other_user}",
        "gists_url": "https://api.github.com/users/JetBrains/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/JetBrains/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/JetBrains/subscriptions",
        "organizations_url": "https://api.github.com/users/JetBrains/orgs",
        "repos_url": "https://api.github.com/users/JetBrains/repos",
        "events_url": "https://api.github.com/users/JetBrains/events{/privacy}",
        "received_events_url": "https://api.github.com/users/JetBrains/received_events",
        "type": "Organization",
        "site_admin": false
      },
      "html_url": "https://github.com/JetBrains/kotlin",
      "description": "The Kotlin Programming Language",
      "fork": false,
      "url": "https://api.github.com/repos/JetBrains/kotlin",
      "forks_url": "https://api.github.com/repos/JetBrains/kotlin/forks",
      "keys_url": "https://api.github.com/repos/JetBrains/kotlin/keys{/key_id}",
      "collaborators_url": "https://api.github.com/repos/JetBrains/kotlin/collaborators{/collaborator}",
      "teams_url": "https://api.github.com/repos/JetBrains/kotlin/teams",
      "hooks_url": "https://api.github.com/repos/JetBrains/kotlin/hooks",
      "issue_events_url": "https://api.github.com/repos/JetBrains/kotlin/issues/events{/number}",
      "events_url": "https://api.github.com/repos/JetBrains/kotlin/events",
      "assignees_url": "https://api.github.com/repos/JetBrains/kotlin/assignees{/user}",
      "branches_url": "https://api.github.com/repos/JetBrains/kotlin/branches{/branch}",
      "tags_url": "https://api.github.com/repos/JetBrains/kotlin/tags",
      "blobs_url": "https://api.github.com/repos/JetBrains/kotlin/git/blobs{/sha}",
      "git_tags_url": "https://api.github.com/repos/JetBrains/kotlin/git/tags{/sha}",
      "git_refs_url": "https://api.github.com/repos/JetBrains/kotlin/git/refs{/sha}",
      "trees_url": "https://api.github.com/repos/JetBrains/kotlin/git/trees{/sha}",
      "statuses_url": "https://api.github.com/repos/JetBrains/kotlin/statuses/{sha}",
      "languages_url": "https://api.github.com/repos/JetBrains/kotlin/languages",
      "stargazers_url": "https://api.github.com/repos/JetBrains/kotlin/stargazers",
      "contributors_url": "https://api.github.com/repos/JetBrains/kotlin/contributors",
      "subscribers_url": "https://api.github.com/repos/JetBrains/kotlin/subscribers",
      "subscription_url": "https://api.github.com/repos/JetBrains/kotlin/subscription",
      "commits_url": "https://api.github.com/repos/JetBrains/kotlin/commits{/sha}",
      "git_commits_url": "https://api.github.com/repos/JetBrains/kotlin/git/commits{/sha}",
      "comments_url": "https://api.github.com/repos/JetBrains/kotlin/comments{/number}",
      "issue_comment_url": "https://api.github.com/repos/JetBrains/kotlin/issues/comments{/number}",
      "contents_url": "https://api.github.com/repos/JetBrains/kotlin/contents/{+path}",
      "compare_url": "https://api.github.com/repos/JetBrains/kotlin/compare/{base}...{head}",
      "merges_url": "https://api.github.com/repos/JetBrains/kotlin/merges",
      "archive_url": "https://api.github.com/repos/JetBrains/kotlin/{archive_format}{/ref}",
      "downloads_url": "https://api.github.com/repos/JetBrains/kotlin/downloads",
      "issues_url": "https://api.github.com/repos/JetBrains/kotlin/issues{/number}",
      "pulls_url": "https://api.github.com/repos/JetBrains/kotlin/pulls{/number}",
      "milestones_url": "https://api.github.com/repos/JetBrains/kotlin/milestones{/number}",
      "notifications_url": "https://api.github.com/repos/JetBrains/kotlin/notifications{?since,all,participating}",
      "labels_url": "https://api.github.com/repos/JetBrains/kotlin/labels{/name}",
      "releases_url": "https://api.github.com/repos/JetBrains/kotlin/releases{/id}",
      "deployments_url": "https://api.github.com/repos/JetBrains/kotlin/deployments",
      "created_at": "2012-02-13T17:29:58Z",
      "updated_at": "2018-10-20T07:54:22Z",
      "pushed_at": "2018-10-20T01:55:59Z",
      "git_url": "git://github.com/JetBrains/kotlin.git",
      "ssh_url": "git@github.com:JetBrains/kotlin.git",
      "clone_url": "https://github.com/JetBrains/kotlin.git",
      "svn_url": "https://github.com/JetBrains/kotlin",
      "homepage": "http://kotlinlang.org/",
      "size": 506402,
      "stargazers_count": 24755,
      "watchers_count": 24755,
      "language": "Kotlin",
      "has_issues": false,
      "has_projects": false,
      "has_downloads": true,
      "has_wiki": false,
      "has_pages": true,
      "forks_count": 2851,
      "mirror_url": null,
      "archived": false,
      "open_issues_count": 104,
      "license": null,
      "forks": 2851,
      "open_issues": 104,
      "watchers": 24755,
      "default_branch": "master",
      "score": 161.71288
    },
    {
      "id": 41577702,
      "node_id": "MDEwOlJlcG9zaXRvcnk0MTU3NzcwMg==",
      "name": "create-android-kotlin-app",
      "full_name": "nekocode/create-android-kotlin-app",
      "private": false,
      "owner": {
        "login": "nekocode",
        "id": 4783781,
        "node_id": "MDQ6VXNlcjQ3ODM3ODE=",
        "avatar_url": "https://avatars0.githubusercontent.com/u/4783781?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/nekocode",
        "html_url": "https://github.com/nekocode",
        "followers_url": "https://api.github.com/users/nekocode/followers",
        "following_url": "https://api.github.com/users/nekocode/following{/other_user}",
        "gists_url": "https://api.github.com/users/nekocode/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/nekocode/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/nekocode/subscriptions",
        "organizations_url": "https://api.github.com/users/nekocode/orgs",
        "repos_url": "https://api.github.com/users/nekocode/repos",
        "events_url": "https://api.github.com/users/nekocode/events{/privacy}",
        "received_events_url": "https://api.github.com/users/nekocode/received_events",
        "type": "User",
        "site_admin": false
      },
      "html_url": "https://github.com/nekocode/create-android-kotlin-app",
      "description": "Create android kotlin app from template. ",
      "fork": false,
      "url": "https://api.github.com/repos/nekocode/create-android-kotlin-app",
      "forks_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/forks",
      "keys_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/keys{/key_id}",
      "collaborators_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/collaborators{/collaborator}",
      "teams_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/teams",
      "hooks_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/hooks",
      "issue_events_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/issues/events{/number}",
      "events_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/events",
      "assignees_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/assignees{/user}",
      "branches_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/branches{/branch}",
      "tags_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/tags",
      "blobs_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/git/blobs{/sha}",
      "git_tags_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/git/tags{/sha}",
      "git_refs_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/git/refs{/sha}",
      "trees_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/git/trees{/sha}",
      "statuses_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/statuses/{sha}",
      "languages_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/languages",
      "stargazers_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/stargazers",
      "contributors_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/contributors",
      "subscribers_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/subscribers",
      "subscription_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/subscription",
      "commits_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/commits{/sha}",
      "git_commits_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/git/commits{/sha}",
      "comments_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/comments{/number}",
      "issue_comment_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/issues/comments{/number}",
      "contents_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/contents/{+path}",
      "compare_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/compare/{base}...{head}",
      "merges_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/merges",
      "archive_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/{archive_format}{/ref}",
      "downloads_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/downloads",
      "issues_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/issues{/number}",
      "pulls_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/pulls{/number}",
      "milestones_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/milestones{/number}",
      "notifications_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/notifications{?since,all,participating}",
      "labels_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/labels{/name}",
      "releases_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/releases{/id}",
      "deployments_url": "https://api.github.com/repos/nekocode/create-android-kotlin-app/deployments",
      "created_at": "2015-08-29T02:28:07Z",
      "updated_at": "2018-10-20T08:21:06Z",
      "pushed_at": "2018-10-08T02:30:22Z",
      "git_url": "git://github.com/nekocode/create-android-kotlin-app.git",
      "ssh_url": "git@github.com:nekocode/create-android-kotlin-app.git",
      "clone_url": "https://github.com/nekocode/create-android-kotlin-app.git",
      "svn_url": "https://github.com/nekocode/create-android-kotlin-app",
      "homepage": "",
      "size": 529,
      "stargazers_count": 1464,
      "watchers_count": 1464,
      "language": "Kotlin",
      "has_issues": true,
      "has_projects": true,
      "has_downloads": true,
      "has_wiki": true,
      "has_pages": false,
      "forks_count": 196,
      "mirror_url": null,
      "archived": false,
      "open_issues_count": 1,
      "license": {
        "key": "apache-2.0",
        "name": "Apache License 2.0",
        "spdx_id": "Apache-2.0",
        "url": "https://api.github.com/licenses/apache-2.0",
        "node_id": "MDc6TGljZW5zZTI="
      },
      "forks": 196,
      "open_issues": 1,
      "watchers": 1464,
      "default_branch": "master",
      "score": 80.40577
    }]
"""
