package com.dalamarmajere.grocerylist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.dalamarmajere.grocerylist.R
import com.dalamarmajere.grocerylist.data.db.entity.ShoppingItem
import com.dalamarmajere.grocerylist.databinding.ShoppingItemBinding
import com.dalamarmajere.grocerylist.ui.shopping_list.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        holder.binding.tvName.text = currentShoppingItem.name
        holder.binding.tvAmount.text = "${currentShoppingItem.amount}"

        holder.binding.ivDelete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }

        holder.binding.ivMinus.setOnClickListener {
            if (currentShoppingItem.amount > 1) {
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }
        }

        holder.binding.ivPlus.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context))
        return ShoppingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(val binding: ShoppingItemBinding): RecyclerView.ViewHolder(binding.root)

}