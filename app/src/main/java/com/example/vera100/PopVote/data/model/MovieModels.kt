package com.example.vera100.PopVote.data.model

import android.R

data class Movie(
    val title: R.string,
    val description: R.string,
    val valutation: Int
)
data class Genre(
    val name: R.string,
    val movieList: MutableList<Movie> = mutableListOf()
)