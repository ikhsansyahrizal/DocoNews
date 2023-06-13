package com.docotel.ikhsansyahrizal.doconewss.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.docotel.ikhsansyahrizal.doconewss.local.bookmark.BookmarkManager
import com.docotel.ikhsansyahrizal.doconewss.R
import com.docotel.ikhsansyahrizal.doconewss.databinding.ActivityBookmarkBinding
import com.docotel.ikhsansyahrizal.doconewss.helper.NetworkAttribute
import com.docotel.ikhsansyahrizal.doconewss.viewmodel.BookmarkViewModel
import com.docotel.ikhsansyahrizal.doconewss.viewmodelfactory.BookmarkViewModelFactory
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem

class BookmarkActivity : AppCompatActivity(), NewsAdapter.OnItemClickListener {

    private lateinit var binding: ActivityBookmarkBinding
    private lateinit var bookmarkadapter: NewsAdapter
    private val viewModel: BookmarkViewModel by viewModels {
        BookmarkViewModelFactory(
            BookmarkManager(
                applicationContext.getSharedPreferences(
                    NetworkAttribute.PREFERENCE_NAME,
                    Context.MODE_PRIVATE
                )
            )
        )
    }
    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbarBookmark)
        toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
        toast.view?.findViewById<ImageView>(android.R.id.icon)?.apply {
            setImageResource(R.drawable.baseline_delete_24)
            visibility = View.VISIBLE
        }

        binding.rvNewsBookmark.layoutManager = LinearLayoutManager(this)
        bookmarkadapter = NewsAdapter(this)
        binding.rvNewsBookmark.adapter = bookmarkadapter


        viewModel.bookmarkedArticlesObserve.observe(this) {
            val newdata = viewModel.getBookmarkedArticles()
            bookmarkadapter.setList(newdata)
        }


        binding.swipeRefreshLayout.setOnRefreshListener {

            val newdata = viewModel.getBookmarkedArticles()
            bookmarkadapter.setList(newdata)
            binding.swipeRefreshLayout.isRefreshing = false
        }


    }

    override fun onItemClick(article: ArticlesItem, position: Int) {
        val intent = Intent(this@BookmarkActivity, DetailActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putParcelable("ARTICLE_KEY", article)
            putExtras(bundle)
            putExtra("index", position)
        }
        startActivity(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bookmark, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_clear -> {
                clearAllData()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clearAllData() {
        viewModel.clearAllData()
        bookmarkadapter.setList(emptyList())
//        Toast.makeText(applicationContext, getString(R.string.all_saved_data_cleared), Toast.LENGTH_SHORT).show()
        toast.setText(R.string.all_saved_data_cleared)
        toast.show()
    }

    override fun onResume() {
        val newdata = viewModel.getBookmarkedArticles()
        bookmarkadapter.setList(newdata)
        super.onResume()
    }

}