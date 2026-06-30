package com.usermgmt.user

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.usermgmt.user.domain.repository.UserPreference
import com.usermgmt.user.presentation.language.LanguageViewModel
import com.usermgmt.user.presentation.navigation.AppNavHost
import com.usermgmt.user.presentation.theme.UserAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {

            userPreference.getLanguage().first().let {
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags(it)
                    )
                }

        }

        setContent {
            UserAppTheme {
                AppNavHost()
            }
        }
    }
}
