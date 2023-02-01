package com.ramprasad.acronymsdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ramprasad.acronymsdemo.R
import com.ramprasad.acronymsdemo.databinding.AcronymListItemBinding
import com.ramprasad.acronymsdemo.model.LongForm

/**
 * Created by Ramprasad on 1/29/23.
 */
class AcronymAdapter(val longForms: List<LongForm>) :
    RecyclerView.Adapter<AcronymAdapter.ViewHolder>() {

    class ViewHolder(val binding: AcronymListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(longform: LongForm) {
            binding.longForm = longform
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AcronymListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(longForms[position])
    }

    override fun getItemCount(): Int {
        return longForms.size
    }

    companion object {
        @BindingAdapter("setVariationText")
        @JvmStatic
        fun setVariationText(variationText: TextView, variations: List<LongForm>) {
            val variationList: MutableList<String> = mutableListOf()
            for (variation in variations) {
                variationList.add(variation.meaning)
            }
            val variationString = variationList.joinToString()
            variationText.text =
                variationText.context.resources.getString(R.string.variation_text, variationString)
        }
    }
}
