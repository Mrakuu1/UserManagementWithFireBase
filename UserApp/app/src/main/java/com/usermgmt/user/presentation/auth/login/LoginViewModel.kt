package com.usermgmt.user.presentation.auth.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usermgmt.user.core.common.AppResult
import com.usermgmt.user.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(){

    protected val _loginState = MutableStateFlow(LoginState())

    val loginState = _loginState.asStateFlow()

    private val _loginEffect = Channel<LoginEffect>(Channel.BUFFERED)

    val loginEffect = _loginEffect.receiveAsFlow()


    protected fun update(
        reducer: (LoginState)->LoginState
    ){
        _loginState.update(reducer)
    }


    fun onIntent(intent:LoginIntent){
        when(intent){
            is LoginIntent.LoginUser -> { login() }
            is LoginIntent.UpdateUserName -> { update { it.copy(name = intent.name) } }
            is LoginIntent.UpdatePassword -> { update { it.copy(password = intent.password) } }
             else -> {}
        }
    }

    fun login(){
        viewModelScope.launch {
            update { it.copy(isLoading = true) }

            val result = loginUseCase.invoke(
                _loginState.value.name,
                _loginState.value.password
            )

            when(result) {
                is AppResult.Success -> {
                    _loginEffect.send(LoginEffect.ShowSnackbar(result.data))
                    _loginEffect.send(LoginEffect.Success)
                }
                is AppResult.Error -> {
                    _loginEffect.send(LoginEffect.ShowSnackbar(result.message))
                }
            }

            update { it.copy(isLoading = false) }
        }
    }
}

