package com.docotel.ikhsansyahrizal.doconewss.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.docotel.ikhsansyahrizal.doconewss.R
import com.docotel.ikhsansyahrizal.doconewss.databinding.ActivityDetailBinding
import com.docotel.ikhsansyahrizal.doconewss.viewmodel.DetailViewModel
import com.docotel.ikhsansyahrizal.doconewss.viewmodelfactory.DetailViewModelFactory
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var isBookmarked: Boolean = false

    private lateinit var articlesItem: ArticlesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbarDetail)

        val viewmodelFactory = DetailViewModelFactory(applicationContext)
        viewModel = ViewModelProvider(this, viewmodelFactory)[DetailViewModel::class.java]

        val article = intent.getParcelableExtra<ArticlesItem?>("ARTICLE_KEY")
        articlesItem = intent.getParcelableExtra("ARTICLE_KEY")!!

        binding.tvTitleDetail.text = article?.title
        binding.tvDescriptionDetail.text = article?.description
        binding.tvAuthorDetail.text = article?.author
        Picasso.get().load(article?.urlToImage).into(binding.imgDetail)


        val tvReadMore = binding.tvReadMoreDetail

        tvReadMore.isClickable = true
        tvReadMore.movementMethod = LinkMovementMethod.getInstance()

        val url = article?.url.toString()
        val linkText = "read more"

        val linkSpan = SpannableString(linkText)
        linkSpan.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                }
            }, 0, linkText.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        )
        tvReadMore.text = linkSpan


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        val bookmarkIcon = menu?.findItem(R.id.add_bookmark)
        if (isBookmarked) {
            bookmarkIcon?.setIcon(R.drawable.baseline_bookmark_added_24)
        } else {
            bookmarkIcon?.setIcon(R.drawable.baseline_bookmark_add_24)
        }
        updateBookmarkIcon(articlesItem)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        invalidateOptionsMenu()
        return when (item.itemId) {
            R.id.add_bookmark -> {
                toogledBookmark()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toogledBookmark() {
        if (isBookmarked) {
            viewModel.removeBookmark(articlesItem)
        } else {
            viewModel.addBookmark(articlesItem)
        }
        isBookmarked = !isBookmarked
    }

    private fun updateBookmarkIcon(articlesItem: ArticlesItem) {
        val bookmarkIcon = binding.myToolbarDetail.menu.findItem(R.id.add_bookmark)
        val listArticle = viewModel.getBookmarkedArticles()
        if (listArticle.contains(articlesItem)) {
            isBookmarked = true
            bookmarkIcon?.setIcon(R.drawable.baseline_bookmark_added_24)
        } else {
            bookmarkIcon?.setIcon(R.drawable.baseline_bookmark_add_24)
        }
        invalidateOptionsMenu()

    }
}