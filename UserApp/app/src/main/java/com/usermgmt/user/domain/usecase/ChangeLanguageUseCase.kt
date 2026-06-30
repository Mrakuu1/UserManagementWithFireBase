package com.usermgmt.user.domain.usecase

import com.usermgmt.user.domain.repository.LanguageRepository
import javax.inject.Inject

class ChangeLanguageUseCase @Inject constructor(
    private val repository: LanguageRepository
){

    suspend operator fun invoke(
        language:String
    ){
        repository.changeLanguage(language)
    }

}