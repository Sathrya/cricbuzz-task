package com.example.sneakerapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.sneakerapp.R
import com.example.sneakerapp.databinding.CartItemBinding
import com.example.sneakerapp.db.CartItem

class CartListAdapter (
    private val removeFromCart :((id: Int,sneakerId:String?,name:String?,price : Int) -> Unit)? = null
        ) :Adapter<CartListAdapter.CartViewHolder>(){

    private var cartList : MutableList<CartItem> = mutableListOf()

    class CartViewHolder( val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.binding.image.setImageResource(R.drawable.images)
        holder.binding.sneakerName.text = cartList[position].name
        holder.binding.sneakerPrice.text = "$"+cartList[position].price.toString()

        holder.binding.removeFromCart.setOnClickListener {
            removeFromCart?.invoke(
                cartList[position].sid,
                cartList[position].id,
                cartList[position].name,
                cartList[position].price
            )
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    fun setCart(items:MutableList<CartItem>){
        this.cartList = items
        notifyDataSetChanged()
    }
}