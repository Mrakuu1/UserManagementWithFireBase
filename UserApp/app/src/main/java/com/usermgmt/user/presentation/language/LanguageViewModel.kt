package com.usermgmt.user.presentation.language

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usermgmt.user.domain.usecase.ChangeLanguageUseCase
import com.usermgmt.user.domain.usecase.GetLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val getLanguage: GetLanguageUseCase,
    private val changeLanguage: ChangeLanguageUseCase
) : ViewModel(){

    protected val _languageState = MutableStateFlow(LanguageState())

    val languageState = _languageState.asStateFlow()

    private val _languageEffect = Channel<LanguageEffect>(Channel.BUFFERED)

    val languageEffect = _languageEffect.receiveAsFlow()


    protected fun update(
        reducer: (LanguageState)->LanguageState
    ){
        _languageState.update(reducer)
    }


    fun onIntent(intent:LanguageIntent){
        when(intent){
            is LanguageIntent.ChangeLanguage -> {
                update { it.copy(selectedLanguage = intent.code) }

                viewModelScope.launch {
                    changeLanguage(intent.code)
                }
            }
            is LanguageIntent.NavigateBack -> { _languageEffect.trySend(LanguageEffect.NavigateBack) }
             else -> {}
        }
    }

    init {
        viewModelScope.launch { getLanguage().collect { update { old -> old.copy(selectedLanguage = it) } } }
        update { it.copy(languageList = getLanguage.getLanguageList()) }
    }

}

