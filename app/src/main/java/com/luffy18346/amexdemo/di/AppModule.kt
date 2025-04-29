package com.luffy18346.amexdemo.di

import com.luffy18346.amexdemo.ui.feature.detail.DetailViewModel
import com.luffy18346.amexdemo.ui.feature.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("IODispatcher")) {
        Dispatchers.IO
    }

    viewModel { MainViewModel(get(), get(named("IODispatcher")))}
    viewModelOf(::DetailViewModel)
}
