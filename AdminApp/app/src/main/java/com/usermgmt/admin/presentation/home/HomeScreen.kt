package com.usermgmt.admin.presentation.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.usermgmt.admin.presentation.components.AppSnackbarHost
import com.usermgmt.admin.presentation.components.LoadingOverlay
import com.usermgmt.admin.presentation.components.UserCard
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    onUserItemClick : (String) -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
){


    val uiState by viewModel.homeState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getUserList()
    }

    LaunchedEffect(Unit){

        viewModel.homeEffect.collect { effect ->
            when(effect){

                is HomeEffect.Success -> {
                    onUserItemClick(effect.userId)
                }

                is HomeEffect.ShowSnackbar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = effect.message
                        )
                    }
                }

                else -> {}
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            Text(
                text = "Home Screen",
                style = MaterialTheme.typography.headlineMedium
            )
        },
        snackbarHost = { AppSnackbarHost(snackbarHostState) }
    ) { padding ->

        LoadingOverlay(uiState.isLoading)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            Text(
                text = "Users List",
                style = MaterialTheme.typography.headlineMedium
            )

            LazyColumn(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(uiState.users) { user ->
                    UserCard(
                        user = user,
                        expanded = uiState.expandedUserId == user.id,
                        editing = uiState.editingUserId == user.id,
                        editUser = uiState.editingUser,
                        onClick = {
                            viewModel.onIntent(
                                HomeIntent.OnUserClick(user.id)
                            )
                        },
                        onEdit = {
                            viewModel.onIntent(
                                HomeIntent.EditUser(user)
                            )
                        },
                        onDelete = {
                            viewModel.onIntent(
                                HomeIntent.DeleteUser(user.id)
                            )
                        },
                        onUpdateUser = {
                            viewModel.onIntent(
                                HomeIntent.UpdateUser(it)
                            )
                        },
                        onSave = {
                            viewModel.onIntent(
                                HomeIntent.SaveUpdate
                            )
                        },
                        onCancel = {
                            viewModel.onIntent(
                                HomeIntent.CancelEdit
                            )
                        },
                        onEmailClick = {
                            viewModel.onIntent(
                                HomeIntent.OnEmailClick
                            )
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview(){

    HomeScreen()

}

