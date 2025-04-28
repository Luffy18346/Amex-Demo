package com.luffy18346.amexdemo.di

import com.luffy18346.amexdemo.domain.use_case.GetPicturesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetPicturesUseCase(get()) }
}