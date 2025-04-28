package com.luffy18346.amexdemo.ui.di

import com.luffy18346.amexdemo.data.repository.PictureRepositoryImpl
import com.luffy18346.amexdemo.domain.repository.IPictureRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IPictureRepository> { PictureRepositoryImpl(get()) }
}