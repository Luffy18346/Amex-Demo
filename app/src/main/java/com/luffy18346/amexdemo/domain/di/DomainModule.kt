package com.luffy18346.amexdemo.domain.di

import com.luffy18346.amexdemo.data.repository.PictureRepositoryImpl
import com.luffy18346.amexdemo.domain.repository.IPictureRepository
import com.luffy18346.amexdemo.domain.use_case.GetPicturesUseCase
import org.koin.dsl.module

val domainModule = module {
    single<IPictureRepository> { PictureRepositoryImpl(get()) }
    factory { GetPicturesUseCase(get()) }
}