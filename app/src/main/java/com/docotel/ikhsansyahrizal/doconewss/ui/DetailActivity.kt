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
import androidx.appcompat.widget.SearchView
import com.docotel.ikhsansyahrizal.doconewss.R
import com.docotel.ikhsansyahrizal.doconewss.databinding.ActivityDetailBinding
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbarDetail)

        val article = intent.getParcelableExtra<ArticlesItem?>("ARTICLE_KEY")

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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_bookmark -> {
                // Perform bookmark action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}