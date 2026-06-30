package com.usermgmt.user.presentation.language


import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.usermgmt.user.R
import com.usermgmt.user.presentation.components.AppSnackbarHost
import com.usermgmt.user.presentation.components.LanguageSelectionCard


@Composable
fun LanguageScreen(
    navController: NavController,
    viewModel: LanguageViewModel = hiltViewModel()
){
    val state = viewModel.languageState.collectAsState()
    val context = LocalContext.current
    val activity = context as Activity

    LaunchedEffect(Unit) {
        viewModel.languageEffect.collect { effect ->
            when (effect) {
                is LanguageEffect.NavigateBack -> {
//                    activity.recreate()
                    navController.popBackStack()
                }

                is LanguageEffect.ShowMessage -> {
                    // Handle the show message effect
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable(true, onClick = { viewModel.onIntent(LanguageIntent.NavigateBack) }),
                    painter = painterResource(R.drawable.nav_back_ic),
                    contentDescription = "navigate back Icon"
                )
                Text(
                    text = stringResource(R.string.language),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .statusBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            state.value.languageList.forEach { language ->

                LanguageSelectionCard(
                    language = language,
                    selected = language.code == state.value.selectedLanguage,
                    onSelect = {
                        viewModel.onIntent(LanguageIntent.ChangeLanguage(it))
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun LanguageScreenPreview(){
    val navController = rememberNavController()
    LanguageScreen(navController)

}

