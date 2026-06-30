package com.usermgmt.user.presentation.auth.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.usermgmt.user.R
import com.usermgmt.user.presentation.auth.registration.RegistrationIntent
import com.usermgmt.user.presentation.components.AppSnackbarHost
import com.usermgmt.user.presentation.navigation.Screens
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
){
    val loginUiState by viewModel.loginState.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit){

        viewModel.loginEffect.collect{ effect ->
            when(effect){
                is LoginEffect.Success -> {
//                    navController.navigate("home")
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = "Login Success"
                        )
                    }
                }
                is LoginEffect.RegisterUser -> {
                    navController.navigate(Screens.Register.route)
                }
                is LoginEffect.LanguageSelection -> {
                    navController.navigate(Screens.Language.route)
                }
                is LoginEffect.ShowSnackbar -> {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(
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
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = { AppSnackbarHost(snackBarHostState) },
        topBar = { TopBar(viewModel = viewModel) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {

            Text(
                text = stringResource(R.string.user_login),
                style = MaterialTheme.typography.headlineLarge
            )

            OutlinedTextField(
                value = loginUiState.name,
                onValueChange = { viewModel.onIntent(LoginIntent.UpdateUserName(it)) },
                label = { Text(stringResource(R.string.username)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = loginUiState.password,
                onValueChange = { viewModel.onIntent(LoginIntent.UpdatePassword(it)) },
                label = { Text(stringResource(R.string.password)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )

            Button(
                onClick = { viewModel.onIntent(LoginIntent.LoginUser) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = (stringResource(R.string.login))
                )
            }

            TextButton(
                onClick = { viewModel.onIntent(LoginIntent.RegisterUser) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.not_yet_registered) + " " + stringResource(R.string.create_account),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun TopBar(
    viewModel: LoginViewModel
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {

        Button(
            onClick = { viewModel.onIntent(LoginIntent.LanguageSelection) },
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Icon(
                painterResource(R.drawable.language_ic),
                contentDescription = "language Icon"
            )
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview(){
    val navController = rememberNavController()
    LoginScreen(navController = navController)

}

