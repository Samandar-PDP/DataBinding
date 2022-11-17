package com.example.databinding.model

data class RepositoryDTO(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)