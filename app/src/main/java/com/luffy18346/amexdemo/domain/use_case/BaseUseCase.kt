package com.luffy18346.amexdemo.domain.use_case

interface BaseUseCase<out Output, in Parameters> {
    suspend fun invoke(parameters: Parameters? = null): Output
}