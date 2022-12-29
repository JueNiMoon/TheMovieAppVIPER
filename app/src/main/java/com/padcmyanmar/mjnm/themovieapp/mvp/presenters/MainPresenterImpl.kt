package com.padcmyanmar.mjnm.themovieapp.mvp.presenters

import android.graphics.Movie
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.mjnm.themovieapp.data.models.MovieModel
import com.padcmyanmar.mjnm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mjnm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mjnm.themovieapp.interactors.MovieInteractorImpl
import com.padcmyanmar.mjnm.themovieapp.interactors.Movieinteractor
import com.padcmyanmar.mjnm.themovieapp.mvp.views.MainView

class MainPresenterImpl : ViewModel(), MainPresenter {

    //view
    var mView: MainView? = null

    //Model
//    private val mMovieModel: MovieModel = MovieModelImpl

    //Interactor
    private val mMovieinteractor: Movieinteractor = MovieInteractorImpl

    //States
    private var mGenres:List<GenreVO>? = listOf()

    override fun initView(view: MainView) {
        mView = view
    }

    override fun onTapGenre(genrePosition: Int) {
        mGenres?.getOrNull(genrePosition)?.id?.let { genreId ->
            mMovieinteractor.getMovieByGenre(
                genreId = genreId.toString(),
                onSuccess = {
                    mView?.showMoviesByGenre(it)
                }, onFailure = {
                    mView?.showError(it)
                }
            )
        }
    }

    override fun onUiReady(owner: LifecycleOwner) {
        //Now Playing
        mMovieinteractor.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showNowPlayingMovies(it)
        }

        // Popular Movies
        mMovieinteractor.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showPopularMovies(it)
        }

        // Top Rated Movies
        mMovieinteractor.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showTopRatedMovies(it)
        }

        // Genre and Get Movies for First Genre
        mMovieinteractor.getGenre(
            onSuccess = {
                mGenres = it
                mView?.showGenres(it)
                it.firstOrNull()?.id?.let { firstGenreId->
                    onTapGenre(firstGenreId)
                }
            }, onFailure = {mView?.showError(it)}
        )

        //Actors
        mMovieinteractor.getActors(
            onSuccess = {
                mView?.showActors(it)
            }, onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }
}