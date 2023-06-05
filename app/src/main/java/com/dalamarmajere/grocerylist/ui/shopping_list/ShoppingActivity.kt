package com.dalamarmajere.grocerylist.ui.shopping_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager
import com.dalamarmajere.grocerylist.R
import com.dalamarmajere.grocerylist.data.db.ShoppingDatabase
import com.dalamarmajere.grocerylist.data.db.entity.ShoppingItem
import com.dalamarmajere.grocerylist.data.repository.ShoppingRepository
import com.dalamarmajere.grocerylist.databinding.ActivityShoppingBinding
import com.dalamarmajere.grocerylist.databinding.DialogAddShopppingItemBinding
import com.dalamarmajere.grocerylist.databinding.ShoppingItemBinding
import com.dalamarmajere.grocerylist.other.ShoppingItemAdapter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        binding.rvShoppingItem.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItem.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener {
            AddShoppingItemDialog(this,
            object: AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}