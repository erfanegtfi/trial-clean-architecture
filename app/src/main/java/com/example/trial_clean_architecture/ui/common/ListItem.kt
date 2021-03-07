package com.example.trial_clean_architecture.ui.common

interface ListItem {
    val listID: String?
    override fun equals(other: Any?): Boolean
}