package com.usermgmt.user.presentation.auth.registration

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.core.Context
import com.usermgmt.user.core.common.AppResult
import com.usermgmt.user.core.util.DeviceInfoUtil
import com.usermgmt.user.domain.model.User
import com.usermgmt.user.domain.usecase.GetDeviceInfoUseCase
import com.usermgmt.user.domain.usecase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val getDeviceInfoUseCase: GetDeviceInfoUseCase
) : ViewModel() {

    protected val _registrationState = MutableStateFlow(RegistrationState())
    val registrationState = _registrationState.asStateFlow()

    private val _registrationEffect = Channel<RegistrationEffect>(Channel.BUFFERED)
    val registrationEffect = _registrationEffect.receiveAsFlow()

    protected fun update(reducer: (RegistrationState) -> RegistrationState) = _registrationState.update(reducer)

    fun onIntent(intent: RegistrationIntent){
        when(intent) {
            is RegistrationIntent.RegisterUser -> registerUser()
            is RegistrationIntent.UpdateUserName -> update { it.copy(name = intent.name) }
            is RegistrationIntent.UpdateEMail -> update { it.copy(email = intent.email) }
            is RegistrationIntent.UpdatePassword -> update { it.copy(password = intent.password) }
            is RegistrationIntent.UpdatePhoneNumber -> update { it.copy(phone = intent.phone) }
        }
    }
    
    fun registerUser() {
        viewModelScope.launch {

            if(registrationState.value.name.isBlank()){
                _registrationEffect.send(RegistrationEffect.ShowSnackbar("User Name required"))
                return@launch
            }

            if(registrationState.value.email.isBlank()){
                _registrationEffect.send(RegistrationEffect.ShowSnackbar("Email required"))
                return@launch
            }

            if(!Patterns.EMAIL_ADDRESS
                    .matcher(registrationState.value.email)
                    .matches()
            ){
                _registrationEffect.send(RegistrationEffect.ShowSnackbar("Invalid email format"))
                return@launch
            }

            if(registrationState.value.phone.isBlank()){
                _registrationEffect.send(RegistrationEffect.ShowSnackbar("Phone Number required"))
                return@launch
            }

            if(registrationState.value.password.isBlank()){
                _registrationEffect.send(RegistrationEffect.ShowSnackbar("Password required"))
                return@launch
            }

            update { it.copy(isLoading = true) }

            val result = registerUserUseCase(
                User(
                    registrationState.value.id,
                    registrationState.value.name,
                    registrationState.value.email,
                    registrationState.value.phone,
                    registrationState.value.password,
                    registrationState.value.role,
                    registrationState.value.createdAt,
                    deviceData = getDeviceInfoUseCase.invoke()
                ),
                password = registrationState.value.password
            )

            when(result){

                is AppResult.Success -> {
                    _registrationEffect.send(RegistrationEffect.Success)
                    _registrationEffect.send(RegistrationEffect.ShowSnackbar("Registration successful"))
                    update { it.copy(id = "", name = "", email = "", phone = "", password = "", isLoading = false) }
                }

                is AppResult.Error -> {
                    _registrationEffect.send(RegistrationEffect.ShowSnackbar(result.message))
                }
            }

            update { it.copy(isLoading = false) }
        }
    }
}