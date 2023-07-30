package com.example.myapplication.feature.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.common.extension.*
import com.example.myapplication.core.base.BaseVMActivity
import com.example.myapplication.databinding.ActivityMovieBinding
import com.example.myapplication.feature.detail_movie.view.DetailMovieActivity
import com.example.myapplication.feature.main.adapter.NowPlayingMovieAdapter
import com.example.myapplication.feature.main.adapter.PopularMovieAdapter
import com.example.myapplication.feature.main.view_model.MovieViewModel

class MovieActivity : BaseVMActivity<MovieViewModel, ActivityMovieBinding>() {

    private val adapter by lazy {
        NowPlayingMovieAdapter {
            startActivity<DetailMovieActivity> {
                putExtra("movieId", it.id)
            }
        }
    }

    private val adapterPopularMovie by lazy {
        PopularMovieAdapter {
            startActivity<DetailMovieActivity> {
                putExtra("movieId", it.id)
            }
        }
    }

    override fun getViewModel() = MovieViewModel::class.java

    override fun getViewBinding(): ActivityMovieBinding {
        return ActivityMovieBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initViewModel()
        binding.rvNowShowing.apply {
            linearLayoutManager(RecyclerView.HORIZONTAL)
        }
        binding.rvPopular.apply {
            gridLayoutManager(2)
        }
        binding.ivFilter.setOnClickListener {
            Toast.makeText(applicationContext, "COOMING SOON", Toast.LENGTH_SHORT).show()
        }
        binding.ivNotification.setOnClickListener {
            Toast.makeText(applicationContext, "COOMING SOON", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViewModel() {
        viewModel.getListNowPlayingMovie()
        viewModel.getListPopularMovie()
    }

    override fun observerViewModel() {
        viewModel.listNowPlayinMovieLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.stateLayout.toLoading()
                }

                is ViewState.Success -> {
                    binding.stateLayout.toContent()
                    if (state.data.results?.isNotEmpty()!!) {
                        state.data.results.let { adapter.addData(it) }
                        binding.rvNowShowing.adapter = adapter
                    }
                }

                is ViewState.Failed -> {
                    binding.stateLayout.toError {
                        initViewModel()
                    }
                }

                else -> {}
            }
        }

        viewModel.listPopularMovieLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.stateLayoutV2.toLoading()
                }

                is ViewState.Success -> {
                    if (state.data.results?.isNotEmpty()!!) {
                        binding.stateLayoutV2.content()
                        state.data.results.let { adapterPopularMovie.addData(it) }
                        binding.rvPopular.adapter = adapterPopularMovie
                    }
                }

                is ViewState.Failed -> {
                    binding.stateLayoutV2.toError {
                        initViewModel()
                    }
                }

                else -> {}
            }
        }
    }
}