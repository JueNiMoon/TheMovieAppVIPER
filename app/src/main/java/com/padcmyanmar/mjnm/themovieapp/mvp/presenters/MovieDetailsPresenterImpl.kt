package com.padcmyanmar.mjnm.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.mjnm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mjnm.themovieapp.interactors.MovieInteractorImpl
import com.padcmyanmar.mjnm.themovieapp.interactors.Movieinteractor
import com.padcmyanmar.mjnm.themovieapp.mvp.views.MovieDetailsView

class MovieDetailsPresenterImpl : ViewModel(), MovieDetailsPresenter {

    //Interactor
    private val mMovieinteractor: Movieinteractor = MovieInteractorImpl

    //View
    private var mView: MovieDetailsView? = null

    override fun initView(view: MovieDetailsView) {
        mView = view
    }

    override fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Int) {
        //Movie Deatils
        mMovieinteractor.getMovieDetails(movieId.toString()){
            mView?.showError(it)
        }?.observe(owner){
            it?.let {
                mView?.showMovieDetails(it)
            }
        }

        // Credits
        mMovieinteractor.getCreditsByMovie(
            movieId = movieId.toString(),
            onSuccess = {
                mView?.showCreditByMovies(cast = it.first, crew = it.second)
            }, onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTabBack() {
        mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {
    }
}