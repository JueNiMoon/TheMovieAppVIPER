package com.padcmyanmar.mjnm.themovieapp.routers

import android.app.Activity
import com.padcmyanmar.mjnm.themovieapp.activities.MovieDetailsActivity
import com.padcmyanmar.mjnm.themovieapp.activities.MovieSearchActivity

fun Activity.navigateToMovieDetailsActivity(movieId : Int){
    startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
}

fun Activity.navigateToSearchActivity(){
    startActivity(MovieSearchActivity.newIntent(this))
}