package com.ozaltun.coinxplorer.presentation.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.ozaltun.coinxplorer.R

@Composable
fun CoinText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = colorResource(id = R.color.text),
    style: TextStyle = LocalTextStyle.current,
    textAlign: TextAlign? = null
) {
    val formattedText = if (text.contains('.')) {
        val splitText = text.split('.')
        val decimalPart = splitText.getOrNull(1)
        if (decimalPart != null && decimalPart.length > 5) {
            "${splitText[0]}.${decimalPart.take(5)}"
        } else {
            text
        }
    } else {
        text
    }
    Text(
        modifier = modifier,
        text = formattedText,
        color = color,
        style = style,
        textAlign = textAlign
    )
}