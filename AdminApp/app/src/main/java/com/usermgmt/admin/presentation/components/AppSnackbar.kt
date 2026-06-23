package com.usermgmt.admin.presentation.components


import androidx.compose.material3.*
import androidx.compose.runtime.Composable


@Composable
fun AppSnackbarHost(
    snackbarHostState: SnackbarHostState
){
    SnackbarHost(
        hostState = snackbarHostState
    ){ data ->
        Snackbar(
            snackbarData = data
        )
    }
}