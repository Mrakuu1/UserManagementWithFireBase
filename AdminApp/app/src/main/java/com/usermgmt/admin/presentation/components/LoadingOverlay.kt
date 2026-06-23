package com.usermgmt.admin.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun LoadingOverlay(
    isLoading:Boolean
){
    if(isLoading){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(
                        alpha = 0.4f
                    )
                )
                .blur(10.dp)
                // consume clicks
                .clickable(
                    enabled = true,
                    onClick = {}
                ),

            contentAlignment = Alignment.Center

        ){
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp)
            )
        }


    }

}