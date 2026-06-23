param(
    [string]$FeatureName
)


$featureLower = $FeatureName.ToLower()

$basePath = "app/src/main/java/com/usermgmt/user/presentation/auth/$featureLower"

$package = "com.usermgmt.user.presentation.auth.$featureLower"



New-Item `
-Type Directory `
-Force `
-Path $basePath | Out-Null



function Create-File(
    $fileName,
    $content
){

    $filePath = Join-Path $basePath $fileName


    New-Item `
    -ItemType File `
    -Path $filePath `
    -Force | Out-Null


    Set-Content `
    -Path $filePath `
    -Value $content `
    -Encoding UTF8


    Write-Host "Created $fileName"

}



Create-File "${FeatureName}Screen.kt" @"
package $package


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ${FeatureName}Screen(){


}


@Preview
@Composable
fun ${FeatureName}ScreenPreview(){

    ${FeatureName}Screen()

}

"@




Create-File "${FeatureName}Intent.kt" @"
package $package


sealed interface ${FeatureName}Intent {


}

"@




Create-File "${FeatureName}Effect.kt" @"
package $package


sealed interface ${FeatureName}Effect {


}

"@




Create-File "${FeatureName}State.kt" @"
package $package


data class ${FeatureName}State(

    val isLoading:Boolean = false

)

"@




Create-File "${FeatureName}ViewModel.kt" @"
package $package


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject



@HiltViewModel
class ${FeatureName}ViewModel @Inject constructor(

) : ViewModel(){

    protected val _${featureLower}State = MutableStateFlow(${FeatureName}State())

    val ${featureLower}State = _${featureLower}State.asStateFlow()

    private val _${featureLower}Effect = Channel<${FeatureName}Effect>(Channel.BUFFERED)

    val ${featureLower}Effect = _${featureLower}Effect.receiveAsFlow()


    protected fun update(
        reducer: (${FeatureName}State)->${FeatureName}State
    ){
        _${featureLower}State.update(reducer)
    }


    fun onIntent(intent:${FeatureName}Intent){
        when(intent){

             else -> {}
        }
    }


}

"@



Write-Host ""
Write-Host "$FeatureName created successfully"