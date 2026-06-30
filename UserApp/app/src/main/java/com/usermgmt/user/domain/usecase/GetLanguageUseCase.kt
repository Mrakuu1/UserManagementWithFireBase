package com.usermgmt.user.domain.usecase

import com.usermgmt.user.R
import com.usermgmt.user.domain.model.Language
import com.usermgmt.user.domain.repository.LanguageRepository
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val repository: LanguageRepository
){
    operator fun invoke() = repository.getLanguage()

    fun getLanguageList() = listOf(
        Language("en", R.string.english),
        Language("hi",R.string.hindi)
    )
}