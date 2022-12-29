package com.padcmyanmar.mjnm.themovieapp.interactors

import androidx.lifecycle.LiveData
import com.padcmyanmar.mjnm.themovieapp.data.models.MovieModel
import com.padcmyanmar.mjnm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mjnm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mjnm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mjnm.themovieapp.data.vos.MovieVO
import io.reactivex.rxjava3.core.Observable

object MovieInteractorImpl : Movieinteractor {

    //Model
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun getNowPlayingMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getNowPlayingMovies(onFailure)
    }

    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getPopularMovies(onFailure)
    }

    override fun getTopRatedMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getTopRatedMovies(onFailure)
    }

    override fun getGenre(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        return mMovieModel.getGenre(onSuccess,onFailure)
    }

    override fun getMovieByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return mMovieModel.getMovieByGenre(genreId,onSuccess,onFailure)
    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        return mMovieModel.getActors(onSuccess,onFailure)
    }

    override fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>? {
        return mMovieModel.getMovieDetails(movieId,onFailure)
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return mMovieModel.getCreditsByMovie(movieId,onSuccess,onFailure)
    }

    override fun searchMovie(query: String): Observable<List<MovieVO>> {
        return mMovieModel.searchMovie(query)
    }
}