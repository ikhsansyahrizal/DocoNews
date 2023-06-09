package com.docotel.ikhsansyahrizal.doconewss.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.docotel.ikhsansyahrizal.doconewss.R
import com.docotel.ikhsansyahrizal.doconewss.databinding.ActivityBookmarkBinding
import com.docotel.ikhsansyahrizal.doconewss.viewmodel.BookmarkViewModel
import com.docotel.ikhsansyahrizal.doconewss.viewmodelfactory.BookmarkViewModelFactory
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem

class BookmarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkBinding
    private lateinit var bookmarkadapter: NewsAdapter
    private lateinit var viewModel: BookmarkViewModel
    private lateinit var toast : Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbarBookmark)
        toast = Toast.makeText(applicationContext,"", Toast.LENGTH_SHORT)
        toast.view?.findViewById<ImageView>(android.R.id.icon)?.apply {
            setImageResource(R.drawable.baseline_delete_24)
            visibility = View.VISIBLE
        }

        val viewModelFactory = BookmarkViewModelFactory(applicationContext)
        viewModel = ViewModelProvider(this, viewModelFactory)[BookmarkViewModel::class.java]

        binding.rvNewsBookmark.layoutManager = LinearLayoutManager(this)
        bookmarkadapter = NewsAdapter()
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

        bookmarkadapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(article: ArticlesItem) {
                val intent = Intent(this@BookmarkActivity, DetailActivity::class.java).apply {
                    val bundle = Bundle()
                    bundle.putParcelable("ARTICLE_KEY", article)
                    putExtras(bundle)
                }
                startActivity(intent)
            }
        })

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