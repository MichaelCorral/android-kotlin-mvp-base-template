package com.michaelcorral.mvptemplate.screens.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.michaelcorral.mvptemplate.R
import com.michaelcorral.mvptemplate.api.models.ItunesContentResults
import com.michaelcorral.mvptemplate.application.GlideApp
import kotlinx.android.synthetic.main.itunes_item.view.*

class MainScreenAdapter(
    private val itunesResults: List<ItunesContentResults>,
    private val onItunesContentClick: (item: ItunesContentResults) -> Unit
) : RecyclerView.Adapter<MainScreenAdapter.ViewHolder>() {

    private val items = mutableListOf<ItunesContentResults>()

    fun addItems(items: List<ItunesContentResults>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: ItunesContentResults) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun updateItem(item: ItunesContentResults, index: Int) {
        notifyItemChanged(index, item)
    }

    fun replaceItems(items: List<ItunesContentResults>) {
        clearItems()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun removeItem(index: Int) {
        items.removeAt(index)
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itunes_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = itunesResults.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itunesResults[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(itunesDetails: ItunesContentResults) {
            GlideApp.with(itemView)
                .load(itunesDetails.artworkUrl200)
                .transform(RoundedCorners(16))
                .into(itemView.itunesItemImageView)

            itemView.itunesItemTextViewTrackName.text = itunesDetails.trackName

            itemView.itunesItemTextViewTrackPrice.text = itemView.resources.getString(
                R.string.itunes_item_track_price_label,
                itunesDetails.trackPrice.toString(),
                itunesDetails.currency
            )

            itemView.setOnClickListener { onItunesContentClick(itunesDetails) }
        }
    }
}