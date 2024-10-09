package com.example.calculator.ui.Components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.Orange
import com.example.calculator.ui.theme.Purple40

@Composable
fun CalcButton(
    modifier: Modifier = Modifier,
    label : String = "",
    isOperation : Boolean = false,
    onClick : (String) -> Unit = {}
) {

    Button(
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp),
        colors = if (isOperation)
            ButtonDefaults.run { buttonColors(Orange) }
        else
            ButtonDefaults.run { buttonColors(Purple40) },
        onClick = {
            onClick(label)
        }) {
        Text(text = label,
            style = MaterialTheme.typography.bodyLarge)
    }

}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorTheme {
        CalcButton(
            label = "7"
        )
    }
}







