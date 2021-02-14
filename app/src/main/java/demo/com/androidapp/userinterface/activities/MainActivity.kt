package demo.com.androidapp.userinterface.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import demo.com.androidapp.R
import demo.com.androidapp.data.models.Item
import demo.com.androidapp.data.models.Repo
import demo.com.androidapp.data.remote.APIService
import kotlinx.android.synthetic.main.activity_main.*
import demo.com.androidapp.data.remote.ApiUtils
import demo.com.androidapp.userinterface.viewholders.RepositoryItemViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    var context: Context? = null
    private var mAPIService: APIService? = null
    var repositoryList: MutableList<Item> = arrayListOf()
    lateinit  var adapter: RepositoryItemViewHolder
    var page = 1
    private var layoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        context = this

        supportActionBar?.title = "Trending Android Repos"

        mAPIService = ApiUtils.apiService

        setupRecyclerView()

        fetchRepository()
    }

    /// Make call to fetch trending repositories
    private fun fetchRepository() {

        mAPIService?.getRepositories(page)?.enqueue(object : Callback<Repo> {
            override fun onFailure(call: Call<Repo>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<Repo>?, response: Response<Repo>?) {
                if (response != null) {

                    if (response.isSuccessful){

                        var repo = response.body()

                        repositoryList.addAll(repo?.items!!)

                        adapter.repositories = repositoryList

                        adapter.notifyDataSetChanged()

                    }
                }
            }

        })
    }



    private fun setupRecyclerView() {
        layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager

        adapter  = RepositoryItemViewHolder(repositoryList)

        recyclerView.adapter = adapter

        setupRecyclerListener()
    }



    private val lastVisibleItemPosition: Int
        get() = layoutManager!!.findLastVisibleItemPosition()



    ///// Setting up recycler listener for endless pagination
    private fun setupRecyclerListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView!!.layoutManager.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {

                    /////  Last item visible now, increment page count and load more repositiory
                    page += 1
                    fetchRepository()
                }
            }
        })
    }
}
