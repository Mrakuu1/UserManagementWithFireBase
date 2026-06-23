package com.usermgmt.admin.presentation.auth.registration


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.usermgmt.admin.presentation.components.AppSnackbarHost
import com.usermgmt.admin.presentation.components.LoadingOverlay
import com.usermgmt.admin.presentation.navigation.Screens
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
){

    val uiState by viewModel.registrationState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit){

        viewModel.registrationEffect.collect{ effect ->
            when(effect){

                is RegistrationEffect.Success -> {
                    navController.navigate(Screens.Login.route)
                }

                is RegistrationEffect.ShowSnackbar -> {
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
        snackbarHost = { AppSnackbarHost(snackbarHostState) }
    ) { padding ->

        LoadingOverlay(uiState.isLoading)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)

        ) {


            Text(
                text = "User Registration",
                style = MaterialTheme.typography.headlineMedium
            )

            OutlinedTextField(
                value = uiState.name,
                onValueChange = { viewModel.onIntent(RegistrationIntent.UpdateUserName(it)) },
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)

            )

            OutlinedTextField(
                value = uiState.email,
                onValueChange = { viewModel.onIntent(RegistrationIntent.UpdateEMail( it.trim())) },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = uiState.phone,
                onValueChange = { viewModel.onIntent(RegistrationIntent.UpdatePhoneNumber(it)) },
                label = { Text("Phone") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)

            )

            OutlinedTextField(
                value = uiState.password,
                onValueChange = { viewModel.onIntent(RegistrationIntent.UpdatePassword(it)) },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )

            Button(
                onClick = { viewModel.onIntent(RegistrationIntent.RegisterUser) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register")
            }


        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview(){
    val navController = rememberNavController()
    RegisterScreen(navController = navController)
}