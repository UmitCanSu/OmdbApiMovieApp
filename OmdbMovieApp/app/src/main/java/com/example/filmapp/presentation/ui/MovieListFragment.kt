package com.example.filmapp.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.filmapp.databinding.FragmentFilmListBinding
import com.example.filmapp.model.Movie
import com.example.filmapp.presentation.movie_list.FilmListFragmentViewModel
import com.example.filmapp.presentation.movie_list.MovieListState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentFilmListBinding
    private val viewModel: FilmListFragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener(searchViewOnQueryListener())
        viewModel.getLastSavedMovie()
        lifecycleScope.launch { observableMovieListState() }

    }

    private suspend fun observableMovieListState() {
        viewModel.state.collect { movieListState ->
            when (movieListState) {
                is MovieListState.Idle -> modeShowList()
                is MovieListState.Loading -> modeLoading()
                is MovieListState.Error -> {
                    binding.errorMessage.text = movieListState.message
                    modeError()
                }

                is MovieListState.Success -> {
                    val films: List<Movie> = movieListState.movieList
                    makeRecylerAdapter(films)
                    modeShowList()
                }
            }
        }
    }

    private fun searchViewOnQueryListener(): SearchView.OnQueryTextListener? {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                if (p0.isNotEmpty()) {
                    viewModel.searchMovieWithName(p0)
                }
                closeKeyboard()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        }
    }

    private fun closeKeyboard() {
        val view: View? = requireActivity().getCurrentFocus()
        if (view != null) {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun makeRecylerAdapter(films: List<Movie>) {
        val filmAdapter = MovieAdapter(films)
        binding.recyclerView.adapter = filmAdapter
        binding.recyclerView.hasFixedSize()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
    }

    private fun modeLoading() {
        binding.recyclerView.visibility = View.GONE
        binding.errorMessage.visibility = View.GONE
        binding.circularProgress.visibility = View.VISIBLE
        binding.searchView.visibility = View.GONE
    }

    private fun modeError() {
        binding.recyclerView.visibility = View.GONE
        binding.errorMessage.visibility = View.VISIBLE
        binding.circularProgress.visibility = View.GONE
        binding.searchView.visibility = View.VISIBLE
    }

    private fun modeShowList() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.errorMessage.visibility = View.GONE
        binding.circularProgress.visibility = View.GONE
        binding.searchView.visibility = View.VISIBLE
    }
}