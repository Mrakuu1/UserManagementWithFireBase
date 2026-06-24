package com.usermgmt.admin.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.usermgmt.admin.domain.model.User

@Composable
fun UserCard(
    user: User,
    expanded: Boolean,
    editing: Boolean,
    editUser: User?,
    onClick: () -> Unit,
    onEmailClick: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onUpdateUser: (User) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .animateContentSize()
                .clickable {
                    onClick()
                }
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically)
        ) {

            Text(
                text = user.name,
                fontStyle = FontStyle.Italic
            )

            if (expanded) {

                if (editing) {

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = editUser?.name ?: "",
                        onValueChange = { onUpdateUser(editUser!!.copy(name = it)) }
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(true, onClick = onEmailClick ),
                        value = editUser?.email ?: "",
                        onValueChange = { onUpdateUser(editUser!!.copy(email = it)) },
                        enabled = false
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = editUser?.phone ?: "",
                        onValueChange = { onUpdateUser(editUser!!.copy(phone = it))}
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Button(
                            onClick = onSave
                        ) {
                            Text("Update")
                        }

                        Button(
                            onClick = onCancel
                        ) {
                            Text("Cancel")
                        }
                    }

                } else {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Button(
                            onClick = onEdit
                        ) {
                            Text("Edit")

                        }

                        Button(
                            onClick = onDelete
                        ) {
                            Text("Delete")
                        }

                    }

                }
            }

        }

    }

}