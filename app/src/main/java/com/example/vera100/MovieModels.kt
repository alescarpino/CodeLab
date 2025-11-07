package com.example.vera100

data class Movie(
    val title: android.R.string,
    val description: android.R.string,
    val valutation: Int
)
data class Genre(
    val name: android.R.string,
    val movieList: MutableList<Movie> = mutableListOf()
)