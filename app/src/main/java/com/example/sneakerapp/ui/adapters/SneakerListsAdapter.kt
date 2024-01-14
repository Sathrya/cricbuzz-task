package com.example.sneakerapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.sneakerapp.R
import com.example.sneakerapp.data.Sneakeritem
import com.example.sneakerapp.databinding.SneakerItemBinding

class SneakerListsAdapter(
    private val sneakerSelected : ((id: String?,sneakerName: String?,brand: String?,price: Int,year: Int?) -> Unit)? = null,
    private val addToCartClicked : ((id: String?, sneakerName: String?,price: Int) -> Unit)? = null
) : Adapter<SneakerListsAdapter.SneakerViewHolder>() {

    var sneakersList:List<Sneakeritem> = listOf()

     class SneakerViewHolder(val binding: SneakerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerViewHolder {
        val binding = SneakerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SneakerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SneakerViewHolder, position: Int) {
        holder.binding.sneakerName.text= sneakersList[position].name
        holder.binding.sneakerPrice.text= "$" + sneakersList[position].retailPrice
        holder.binding.image.setImageResource(R.drawable.imagee)
        holder.binding.addToCartBtn.setOnClickListener {
            sneakersList[position].retailPrice?.let { it1 ->
                addToCartClicked?.invoke(
                    sneakersList[position].id,
                    sneakersList[position].name,
                    it1.toInt()
                )
            }
        }

        holder.binding.card.setOnClickListener {
            sneakerSelected?.invoke(
                sneakersList[position].id,
                sneakersList[position].name,
                sneakersList[position].brand,
                sneakersList[position].retailPrice!!.toInt(),
                sneakersList[position].year
            )
        }
    }

    override fun getItemCount(): Int {
        return sneakersList.size
    }

    fun setItems(items:List<Sneakeritem>){
        this.sneakersList=items
        notifyDataSetChanged()
    }
}