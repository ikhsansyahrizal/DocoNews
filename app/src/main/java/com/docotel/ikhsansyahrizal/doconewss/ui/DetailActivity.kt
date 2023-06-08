package com.docotel.ikhsansyahrizal.doconewss.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import com.docotel.ikhsansyahrizal.doconewss.databinding.ActivityDetailBinding
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {

        const val EXTRA_NEWS_ITEM = "extra_news_item"

        const val ARTICLE_TITLE_EXTRA = "articleTitle"
        const val ARTICLE_DESCRIPTION_EXTRA = "articleDescription"
        const val ARTICLE_AUTHOR_EXTRA = "articleAuthor"
        const val ARTICLE_CONTENT_EXTRA = "articleContent"
        const val ARTICLE_URL_EXTRA = "articleUrl"
        const val ARTICLE_PUBLISH_EXTRA = "articlePublish"
        const val ARTICLE_IMAGE_EXTRA = "articleImage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedItem = intent.getSerializableExtra("selectedItem") as? ArticlesItem

        val articleTitle = intent.getStringExtra(ARTICLE_TITLE_EXTRA)
        val articleContent = intent.getStringExtra(ARTICLE_CONTENT_EXTRA)
        val articleAuthor = intent.getStringExtra(ARTICLE_AUTHOR_EXTRA)
        val articleImage = intent.getStringExtra(ARTICLE_IMAGE_EXTRA)
        val articleLink = intent.getStringExtra(ARTICLE_URL_EXTRA)

        binding.tvTitleDetail.text = articleTitle
        binding.tvDescriptionDetail.text = articleContent
        binding.tvAuthorDetail.text = articleAuthor

        Picasso.get().load(articleImage).into(binding.imgDetail)


        val tv_read_more = binding.tvReadMoreDetail

        tv_read_more.isClickable = true
        tv_read_more.movementMethod = LinkMovementMethod.getInstance()

        val url = intent.getStringExtra(ARTICLE_URL_EXTRA)
        val linkText = "read more"

        val linkSpan = SpannableString(linkText )
        linkSpan.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }, 0, linkText.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        tv_read_more.text = linkSpan
    }
}