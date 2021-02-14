package demo.com.androidapp.userinterface.viewholders

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import demo.com.androidapp.R
import demo.com.androidapp.data.Store
import demo.com.androidapp.data.models.Item
import demo.com.androidapp.userinterface.activities.RepoDetailActivity
import java.io.Serializable
import kotlinx.android.synthetic.main.repository_item.view.*


/**
 * Created by sundayakinsete on 28/01/2018.
 */

class RepositoryItemViewHolder(repositories: MutableList<Item>) : RecyclerView.Adapter<RepositoryItemViewHolder.ViewHolder>() {

    var repositories: MutableList<Item> = repositories

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: RepositoryItemViewHolder.ViewHolder, position: Int) {

        val repo = repositories.get(position)

        holder.bindRepo(repo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemViewHolder.ViewHolder {

        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)

        return ViewHolder(inflatedView)
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v

        private var repo: Item? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {

            val context = itemView.context

            val viewRepoIntent = Intent(context, RepoDetailActivity::class.java)

            Store.viewedRepo = repo

            context.startActivity(viewRepoIntent)
        }

        fun bindRepo(repo: Item) {
            this.repo = repo

            Picasso.with(itemView.context).load(repo.owner?.avatarUrl).into(view.owner_avatar)

            view.repo_title.text = repo.name

            view.repo_description.text = repo.fullName

            view.repo_watchers.text = " " + String.format("%,d", repo?.forksCount)

            view.repo_language.text = repo.language

        }

    }
}