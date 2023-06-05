package com.dalamarmajere.grocerylist.ui.shopping_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dalamarmajere.grocerylist.data.repository.ShoppingRepository

class ShoppingViewModelFactory(
    private val repository: ShoppingRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}