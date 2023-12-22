package com.ozaltun.coinxplorer.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ozaltun.coinxplorer.R

@Composable
fun CoinTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    label: String = "",
    onValueChange: (String) -> Unit = {},
    keyboardOptions: (KeyboardOptions) = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    passwordVisibility: Boolean = true,
    readOnly: Boolean = false,
    trailingIcon : @Composable () -> Unit = {}
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, top = 16.dp)
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = colorResource(id = R.color.input_background)
                ),
                shape = RoundedCornerShape(size = 8.dp)
            ),
        value = text, onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(id = R.color.input_background),
            disabledContainerColor = colorResource(id = R.color.input_background),
            unfocusedContainerColor = colorResource(id = R.color.input_background),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        label = { Text(text = label) },
        trailingIcon = {
            trailingIcon.invoke()
        },
        singleLine = true,
        readOnly = readOnly
    )
}
@Preview(showBackground = true)
@Composable
fun CoinTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    label: String = "",
    onValueChange: (String) -> Unit = {},
    keyboardOptions: (KeyboardOptions) = KeyboardOptions.Default,
    readOnly: Boolean = false,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, top = 8.dp)
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = colorResource(id = R.color.input_background)
                ),
                shape = RoundedCornerShape(size = 8.dp)
            ),
        value = text, onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(id = R.color.input_background),
            disabledContainerColor = colorResource(id = R.color.input_background),
            unfocusedContainerColor = colorResource(id = R.color.input_background),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        label = { Text(text = label) },
        singleLine = true,
        readOnly = readOnly
    )
}