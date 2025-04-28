package com.luffy18346.amexdemo.ui.di

import com.luffy18346.amexdemo.ui.feature.main.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
}