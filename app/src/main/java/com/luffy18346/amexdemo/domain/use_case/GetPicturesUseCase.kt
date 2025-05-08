package com.luffy18346.amexdemo.domain.use_case

import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.domain.repository.IPictureRepository

class GetPicturesUseCase(
    private val pictureRepository: IPictureRepository
) {

    suspend operator fun invoke(parameters: Unit?): Result<List<Picture>> {
        return pictureRepository.getPictures()
    }
}

// Operator function without using BaseUseCase