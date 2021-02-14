package demo.com.androidapp.userinterface.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import demo.com.androidapp.R
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Picasso
import demo.com.androidapp.data.Store
import demo.com.androidapp.data.models.Item
import kotlinx.android.synthetic.main.activity_repo_detail.*
import android.net.Uri




class RepoDetailActivity : AppCompatActivity() {

    var repo: Item? = null
    var context:Context?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repo_detail)

        context = this

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        repo = Store.viewedRepo

        if(repo != null){
            displayRepoInformation()
        }
    }

    private fun displayRepoInformation() {

        supportActionBar?.title = repo?.name

        Picasso.with(context).load(repo?.owner?.avatarUrl).into(owner_avatar)

        repo_title.text = repo?.name

        repo_description.text = repo?.fullName

        repo_full_description.text = repo?.description

        repo_watchers.text = String.format("%,d", repo?.watchers)

        repo_stars.text = String.format("%,d", repo?.stargazersCount)

        repo_forks.text = String.format("%,d", repo?.forksCount)

        repo_open_issues.text =  String.format("%,d", repo?.openIssues)

        repo_language.text = repo?.language
    }



    fun btnViewOnGithubClicked(view: View){

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo?.htmlUrl))

        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
