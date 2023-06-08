package com.docotel.ikhsansyahrizal.doconewss.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.docotel.ikhsansyahrizal.doconewss.R
import com.docotel.ikhsansyahrizal.doconewss.databinding.ActivityMainBinding
import com.docotel.ikhsansyahrizal.doconewss.helper.EndlessOnScrollListener
import com.docotel.ikhsansyahrizal.doconewss.helper.NetworkAttribute
import com.docotel.ikhsansyahrizal.doconewss.viewmodel.NewsViewModel
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter
    private val viewModel: NewsViewModel by viewModels()
    private var queryData = ""

    private val articleItemsArrayList = ArrayList<ArticlesItem>()

    private var page = 0
    private var loadMore = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsAdapter = NewsAdapter()
        binding.rvNews.adapter = newsAdapter

        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager

        setSupportActionBar(binding.myToolbar)

        observeLoading()

        viewModel.newsData.observe(this) { newList ->
            articleItemsArrayList.addAll(newList)
            newsAdapter.setList(articleItemsArrayList.toMutableList())
            binding.swipeRefreshLayout.isRefreshing = false

        }

        binding.rvNews.stopScroll()
        page = 1
        loadMore = false
        viewModel.loadNextPage("", page)
        observeStatus()

        scrollData().let { newItemDetected ->
            binding.rvNews.addOnScrollListener(newItemDetected)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            articleItemsArrayList.clear()
            binding.rvNews.stopScroll()
            page = 1
            loadMore = false
            viewModel.loadNextPage("", page)
            scrollData().let { newItemDetected ->
                binding.rvNews.addOnScrollListener(newItemDetected)
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }


    }


    private fun scrollData(): EndlessOnScrollListener {
        return object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                page += 1
                loadMore = true
                viewModel.loadNextPage("", page)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                articleItemsArrayList.clear()
                viewModel.loadNextPage(query, page)
                queryData = query
                scrollData().let { newItemDetected ->
                    binding.rvNews.addOnScrollListener(newItemDetected)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        return true
    }

    private fun observeLoading() {
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressbar.visibility = View.VISIBLE
            } else {
                binding.progressbar.visibility = View.GONE
            }
        }
    }

    private fun observeStatus() {
        viewModel.errorLimit.observe(this) { errorLimit ->
            if (errorLimit) {
                binding.tvError.text = NetworkAttribute.errorMessage
                binding.tvError.visibility = View.VISIBLE
            } else {
                binding.tvError.visibility = View.GONE
            }
        }
    }

}