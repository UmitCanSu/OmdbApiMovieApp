package com.example.filmapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.filmapp.databinding.FragmentMovieDetailBinding
import com.example.filmapp.until.downland
import com.example.filmapp.model.MovieDetail
import com.example.filmapp.presentation.movie_detail.MovieDetailFragmentViewModel
import com.example.filmapp.presentation.movie_detail.MovieDetailState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModel by viewModels<MovieDetailFragmentViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val imdbID = MovieDetailFragmentArgs.fromBundle(it!!).imdbID
            viewModel.searchMovieWithOmdbID(imdbID)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
        binding.backButton.setOnClickListener {
           Navigation.findNavController(it).popBackStack()
        }
    }


    private suspend fun observableState() {
        viewModel.state.collect { fragmentState ->
            when (fragmentState) {
                is MovieDetailState.Idle -> {

                }

                is MovieDetailState.Loading -> {
                    modeLoading()
                }

                is MovieDetailState.Error -> {
                    modeError()
                    fillErrorMessage(fragmentState.message)
                }

                is MovieDetailState.Success -> {
                    fillTheFragment(fragmentState.movieDetail)
                    modeSuccess()
                }
            }
        }
    }

    private fun modeLoading() {
        binding.movieDetailProgresBar.visibility = View.VISIBLE
        binding.movieDetailMovieInformationLayout.visibility = View.GONE
        binding.movieDetailErrorMessage.visibility = View.GONE
    }

    private fun modeError() {
        binding.movieDetailErrorMessage.visibility = View.VISIBLE
        binding.movieDetailProgresBar.visibility = View.GONE
        binding.movieDetailMovieInformationLayout.visibility = View.GONE
    }

    private fun modeSuccess() {
        binding.movieDetailMovieInformationLayout.visibility = View.VISIBLE
        binding.movieDetailProgresBar.visibility = View.GONE
        binding.movieDetailErrorMessage.visibility = View.GONE
    }

    private fun fillTheFragment(movie: MovieDetail) {
        binding.movieDetailImage.downland(movie.poster)
        binding.movieName.text = movie.title
        binding.movieDirector.text = movie.director
        binding.movieYear.text = movie.year
    }

    private fun fillErrorMessage(errorMessage: String) {
        binding.movieDetailErrorMessage.text = errorMessage
    }
}

