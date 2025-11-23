package com.example.appmocom1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InventoryViewModel : ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _itemList = MutableStateFlow<List<Item>>(emptyList())
    val itemList: StateFlow<List<Item>> = _itemList.asStateFlow()

    fun setUsername(name: String) {
        _username.value = name
    }

    fun addItem(name: String, quantity: Int) {
        val newItem = Item(name = name, quantity = quantity)
        _itemList.update { currentList ->
            currentList + newItem
        }
    }
}