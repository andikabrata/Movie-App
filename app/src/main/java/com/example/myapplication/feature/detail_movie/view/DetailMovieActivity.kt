package com.example.myapplication.feature.detail_movie.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.common.extension.ViewState
import com.example.myapplication.common.extension.gridLayoutManager
import com.example.myapplication.common.extension.linearLayoutManager
import com.example.myapplication.common.extension.loadImage
import com.example.myapplication.core.base.BaseVMActivity
import com.example.myapplication.core.di.IMAGE_SOURCE
import com.example.myapplication.databinding.ActivityDetailMovieBinding
import com.example.myapplication.feature.detail_movie.adapter.ActorAdapter
import com.example.myapplication.feature.detail_movie.adapter.GenresAdapter
import com.example.myapplication.feature.detail_movie.view_model.DetailMovieViewModel
import com.example.myapplication.feature.dialog_trailer.view.DialogTrailerFragment
import java.math.BigDecimal
import java.math.RoundingMode

class DetailMovieActivity : BaseVMActivity<DetailMovieViewModel, ActivityDetailMovieBinding>() {

    private val adapter by lazy {
        GenresAdapter {}
    }

    private val adapterActor by lazy {
        ActorAdapter {}
    }

    override fun getViewModel() = DetailMovieViewModel::class.java

    override fun getViewBinding(): ActivityDetailMovieBinding {
        return ActivityDetailMovieBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        setBackgrounStatusBar()

        viewModel.movieId.value = intent.getIntExtra("movieId", 0)
        viewModel.getDetailMovie()
        viewModel.getActorMovie()

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.rvGenre.apply {
            linearLayoutManager(RecyclerView.HORIZONTAL)
        }
        binding.rvActor.apply {
            gridLayoutManager(4)
        }
        binding.ivBgPlay.setOnClickListener {
            viewModel.getVideoMovie()
        }
        binding.ivBookmarkMovie.setOnClickListener {
            Toast.makeText(applicationContext, "COOMING SOON", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun observerViewModel() {
        viewModel.detailMovieLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {}

                is ViewState.Success -> {
                    state.data.let {
                        binding.ivCoverMovie.loadImage(IMAGE_SOURCE + it.poster_path)
                        binding.tvTitleMovie.text = it.title
                        binding.tvRated.text = roundToTwoDecimalPlaces(it.vote_average).toString() + "/10 IMDb"
                        binding.tvDescription.text = it.overview
                        binding.tvValueLength.text = it.runtime.toString() + " min"
                        binding.tvValueLanguage.text = if (it.original_language == "en") "English" else "Tidak Diketahui"
                        binding.tvValueRating.text = roundToTwoDecimalPlaces(it.vote_average).toString()
                        adapter.addData(it.genres)
                        binding.rvGenre.adapter = adapter
                    }
                }

                is ViewState.Failed -> {}

                else -> {}
            }
        }

        viewModel.actorMovieLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {}

                is ViewState.Success -> {
                    state.data.cast.let {
                        adapterActor.addData(it!!)
                        binding.rvActor.adapter = adapterActor
                    }
                }

                is ViewState.Failed -> {}

                else -> {}
            }
        }

        viewModel.videoMovieLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {}

                is ViewState.Success -> {
                    state.data.results.let {
                        DialogTrailerFragment.show(supportFragmentManager, it?.get(0)?.key!!)
                    }
                }

                is ViewState.Failed -> {}

                else -> {}
            }
        }
    }

    private fun setBackgrounStatusBar() {
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.red)
    }

    private fun roundToTwoDecimalPlaces(number: Double): Double {
        return BigDecimal(number.toString()).setScale(1, RoundingMode.HALF_UP).toDouble()
    }
}