package com.example.gweiland_top20crypto.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gweiland_top20crypto.databinding.CryptoItemBinding
import com.example.gweiland_top20crypto.datamodels.ListingItem
import com.example.gweiland_top20crypto.utility.AnimationUtil.uniClick

class MainCryptoAdapter : RecyclerView.Adapter<MainCryptoAdapter.ThreadsViewHolder>() {
    private var listings = ArrayList<ListingItem>()
    private var onItemClickListener: ((ListingItem) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((ListingItem) -> Unit)?) {
        this.onItemClickListener = onItemClickListener
    }

    fun setUpRecyclerView(listings: ArrayList<ListingItem>?) {
        listings?.let { this.listings = listings }
        notifyDataSetChanged()
    }

    inner class ThreadsViewHolder(var binding: CryptoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreadsViewHolder {
        return ThreadsViewHolder(CryptoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ThreadsViewHolder, position: Int) {
        holder.binding.currListing = listings[position]
        holder.binding.currQuote = listings[position].quote?.get("USD")
        holder.binding.price = String.format("%.2f", listings[position]?.quote?.get("USD")?.price)

        holder.binding.root.uniClick(true) {
            onItemClickListener?.invoke(listings[position])
        }
    }

    override fun getItemCount(): Int  = listings.size
}