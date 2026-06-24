package com.usermgmt.admin.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usermgmt.admin.core.common.AppResult
import com.usermgmt.admin.domain.model.User
import com.usermgmt.admin.domain.usecase.DeleteUserUseCase
import com.usermgmt.admin.domain.usecase.FetchUserListUseCase
import com.usermgmt.admin.domain.usecase.UpdateUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchUserListUseCase: FetchUserListUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserDetailUseCase: UpdateUserDetailUseCase
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
            is HomeIntent.OnUserClick -> { update { it.copy( expandedUserId =if(it.expandedUserId == intent.userId) null else intent.userId ) } }

            is HomeIntent.EditUser -> { update { it.copy(editingUserId = intent.user.id, editingUser = intent.user) }}

            is HomeIntent.UpdateUser -> { update { it.copy(editingUser = intent.user) } }

            is HomeIntent.DeleteUser -> { deleteUser(intent.userId) }

            HomeIntent.SaveUpdate -> { homeState.value.editingUser?.let { updateUser(it) } }

            HomeIntent.CancelEdit -> { update { it.copy( editingUser = null,editingUserId = null) } }

            HomeIntent.OnEmailClick -> { _homeEffect.trySend(HomeEffect.ShowSnackbar("Email cannot be modified.")) }
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

    fun updateUser(user : User) {
        viewModelScope.launch {
           update { it.copy(isLoading = true) }
                when(updateUserDetailUseCase.invoke(user)) {
                    is AppResult.Success -> {
                        getUserList()
                        update { it.copy(editingUser = null, editingUserId = null) }
                    }
                    is AppResult.Error -> { _homeEffect.send(HomeEffect.ShowSnackbar("Failed to delete user, Something went wrong")) }
                    else -> {}
                }
           update { it.copy(isLoading = false) } }
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

