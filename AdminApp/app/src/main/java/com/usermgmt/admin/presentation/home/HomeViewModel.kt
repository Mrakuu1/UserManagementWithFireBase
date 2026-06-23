package com.usermgmt.admin.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.usermgmt.admin.core.common.AppResult
import com.usermgmt.admin.domain.usecase.DeleteUserUseCase
import com.usermgmt.admin.domain.usecase.FetchUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchUserListUseCase: FetchUserListUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel(){

    protected val _homeState = MutableStateFlow(HomeState())

    val homeState = _homeState.asStateFlow()

    private val _homeEffect = Channel<HomeEffect>(Channel.BUFFERED)

    val homeEffect = _homeEffect.receiveAsFlow()


    protected fun update(
        reducer: (HomeState)->HomeState
    ){
        _homeState.update(reducer)
    }


    fun onIntent(intent:HomeIntent){
        when(intent){
            is HomeIntent.OnUserClick -> { deleteUser(intent.userId) }
             else -> {}
        }
    }

    fun getUserList() {
        viewModelScope.launch {
            update { it.copy(isLoading = true) }
            val result = fetchUserListUseCase.invoke()
            when(result) {
               is AppResult.Success ->{ update { it.copy(users = result.data) } }
                is AppResult.Error -> { _homeEffect.send(HomeEffect.ShowSnackbar(result.message)) }
                else -> {}
            }
            update { it.copy(isLoading = false) }
        }
    }

    fun deleteUser(userId:String) {
        viewModelScope.launch {
            update { it.copy(isLoading = true) }
            when(deleteUserUseCase.invoke(userId)) {
                is AppResult.Success -> { getUserList() }
                is AppResult.Error -> { _homeEffect.send(HomeEffect.ShowSnackbar("Failed to delete user, Something went wrong")) }
                else -> {}
            }
            update { it.copy(isLoading = false) }
        }
    }

}

