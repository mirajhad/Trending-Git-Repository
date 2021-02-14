package demo.com.androidapp.data.remote

import demo.com.androidapp.data.models.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by sundayakinsete on 27/01/2018.
 */
interface APIService {

    //?q=android%20language:java&sort=stars&order=desc
    ///// This query fetches most trending android repository /////
    ///// For demo purpose we would call it directly
    //?page=2&per_page=100'
    @GET("search/repositories?q=android%20language:java&sort=stars&order=desc&per_page=10")
    fun getRepositories(@Query("page") page: Int): Call<Repo>
}
