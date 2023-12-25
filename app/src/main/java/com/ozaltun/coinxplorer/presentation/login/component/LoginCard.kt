package com.ozaltun.coinxplorer.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ozaltun.coinxplorer.R
import com.ozaltun.coinxplorer.util.constant.Dimens
import com.ozaltun.coinxplorer.util.constant.Dimens.ExtraSmallPadding2
import com.ozaltun.coinxplorer.util.constant.Dimens.ExtraSmallPadding3


@Composable
fun LoginCard(
    buttonText: String,
    title: String,
    textMail: String,
    textPassword: String,
    onValueChangeMail: (String) -> Unit = {},
    onValueChangePassword: (String) -> Unit = {},
    onClick: () -> Unit
) {
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    Card(
        Modifier
            .fillMaxWidth()
            .padding(ExtraSmallPadding2),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.card_background)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(ExtraSmallPadding3),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title, style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = Dimens.FontSize
                ),
                textAlign = TextAlign.Center
            )
            LoginTextField(
                label = stringResource(id = R.string.e_mail),
                text = textMail,
                onValueChange = onValueChangeMail,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Mail, contentDescription = null,
                    tint = colorResource(id = R.color.primary)
                )
            }
            LoginTextField(
                label = stringResource(id = R.string.password),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                text = textPassword,
                onValueChange = onValueChangePassword,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                passwordVisibility = true
            ) {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    if (passwordVisibility) Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "icon visibility",
                        tint = colorResource(id = R.color.primary)
                    ) else Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = "icon visibility",
                        tint = colorResource(id = R.color.primary)
                    )
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary)),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(4.dp),
                onClick = {
                    onClick.invoke()
                }
            ) {
                Text(
                    text = buttonText,
                    color = colorResource(id = R.color.text),
                    fontSize = Dimens.FontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}