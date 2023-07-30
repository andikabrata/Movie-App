package com.example.myapplication.core.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import org.koin.android.compat.ViewModelCompat
import kotlin.coroutines.CoroutineContext

abstract class BaseVMActivity<VM : BaseViewModel, B : ViewBinding> : AppCompatActivity(),
    CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    protected val viewModel: VM by ViewModelCompat.viewModel(this, getViewModel())

    lateinit var binding: B

    abstract fun getViewModel(): Class<VM>

    abstract fun getViewBinding(): B

    abstract fun onViewCreated(savedInstanceState: Bundle?)

    abstract fun observerViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onViewCreated(savedInstanceState)
        observerViewModel()
    }

    protected fun <T> LiveData<T>.onResult(action: (T) -> Unit) {
        observe(this@BaseVMActivity, Observer { data -> data?.let(action) })
    }

    override fun onDestroy() {
        coroutineContext.cancel()
        super.onDestroy()
    }

//    private fun getViewModelClass(): Class<VM> {
//        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
//        return type as Class<VM>
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected fun initToolbar(
        toolbar: Toolbar,
        title: String = "",
        subTitle: String = "",
        back: Boolean = true
    ) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        if (subTitle.isNotEmpty())
            supportActionBar?.subtitle = subTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(back)
    }
}