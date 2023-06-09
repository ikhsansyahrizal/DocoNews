package com.docotel.ikhsansyahrizal.doconewss.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.docotel.ikhsansyahrizal.doconewss.databinding.NewsItemBinding
import com.docotel.ikhsansyahrizal.doconewss.helper.DIFFUTIL_ARTICLEITEM_CALLBACK
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem
import com.squareup.picasso.Picasso

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var clickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(article: ArticlesItem)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    private val differ = AsyncListDiffer(this, DIFFUTIL_ARTICLEITEM_CALLBACK)

    inner class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ArticlesItem) {
            binding.tvTitle.text = data.title ?: ""
            binding.tvDesc.text = data.description ?: ""
            binding.tvAuthor.text = (data.author ?: "")
            binding.tvPublish.text = data.publishedAt ?: ""

            Picasso.get()
                .load(data.urlToImage)
                .into(binding.imgNews)

            binding.root.setOnClickListener {
                clickListener.onItemClick(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.bind(currentItem)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setList(list: List<ArticlesItem>) {
        differ.submitList(list)
    }


}