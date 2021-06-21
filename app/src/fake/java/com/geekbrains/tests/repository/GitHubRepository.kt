package com.geekbrains.tests.repository

import com.geekbrains.tests.model.SearchResponse
import com.geekbrains.tests.presenter.RepositoryContract
import retrofit2.Response

internal class GitHubRepository(private val gitHubApi: GitHubApi) : RepositoryContract {

    private val TEST_NUMBER_OF_RESULTS_ALGOL_REAL = 2431

    override fun searchGithub(
        query: String,
        callback: RepositoryCallback
    ) {
        callback.handleGitHubResponse(Response.success(SearchResponse(TEST_NUMBER_OF_RESULTS_ALGOL_REAL, listOf())))
    }
}