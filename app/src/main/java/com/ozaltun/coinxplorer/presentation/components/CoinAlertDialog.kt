package com.ozaltun.coinxplorer.presentation.components
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozaltun.coinxplorer.R

@Composable
fun CoinAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    color: Color = colorResource(id = R.color.primary),
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background ,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = "dialog Icon",
                tint = color,
                modifier = Modifier.size(64.dp)
            )
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = dialogTitle,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = dialogText,
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
//        dismissButton = {
//            TextButton(
//                onClick = {
//                    onConfirmation()
//                }
//            ) {
//                Text(stringResource(id = R.string.okay))
//            }
//        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(id = R.string.alert_okay))
            }
        }
    )
}