package com.example.trial_clean_architecture.ui.common

class TileTypes<T>(vararg types: Tile<T>) {
    private val tileTypes: ArrayList<Tile<T>> = ArrayList()

    init {
        types.forEach { addType(it) }
    }

    private fun addType(type: Tile<T>) {
        tileTypes.add(type)
    }

    fun of(item: T?): Tile<T> {
        val type = tileTypes.find { it.isTypeOf(item) }
        type?.let {
            return type
        }
//        for (cellType in tileTypes) {
//            if (cellType.isTypeOf(item)) return cellType
//        }
        throw NoSuchItemTypeException()
    }

    fun of(viewType: Int): Tile<T> {
        val type = tileTypes.find { it.type() == viewType }
        type?.let {
            return type
        }
//        for (cellType in tileTypes) {
//            if (cellType.type() == viewType) return cellType
//        }
        throw NoSuchViewTypeException()
    }
}