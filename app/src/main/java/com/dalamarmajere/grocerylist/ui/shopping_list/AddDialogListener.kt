package com.dalamarmajere.grocerylist.ui.shopping_list

import com.dalamarmajere.grocerylist.data.db.entity.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}