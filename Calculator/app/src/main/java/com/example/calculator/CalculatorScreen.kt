package com.example.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.Components.CalcButton
import com.example.calculator.ui.theme.CalculatorTheme


@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {

    var displaytext by remember { mutableStateOf("0") };

    var operand by remember { mutableStateOf(0.0) }

    var operator by remember { mutableStateOf("")}

    var middleOperation by remember { mutableStateOf(true) }

    fun SetDisplay(value:Double ){
        if (value % 1 == 0.0) {
            displaytext = value.toInt().toString()
        } else {
            displaytext = value.toString()
        }

    }

    val onNumPressed : (String) -> Unit = { num ->
        if(middleOperation) {
            if (displaytext == "0") {
                if (num == ".") {
                    displaytext = "0."
                } else {
                    displaytext = num
                }

            } else {
                if (!(displaytext.contains('.')) || num != ".") {
                    displaytext += num;
                }
            }
        }
        else{displaytext = num}
        middleOperation = true;
    }

    val onOperatorPressed : (String) -> Unit = { op ->

        if (operator.isNotEmpty()) {
            when (operator) {
                "+" -> operand += displaytext.toDouble()
                "-" -> operand -= displaytext.toDouble()
                "*" -> operand *= displaytext.toDouble()
                "/" -> operand /= displaytext.toDouble()
                "=" -> operator = ""
            }
            SetDisplay(operand)
        }
        operand = displaytext.toDouble()
        operator = op



        middleOperation = false
    }


    Column(
        modifier = modifier.padding(16.dp).fillMaxSize())
    {
        Text(text = displaytext,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textAlign = TextAlign.End
        )



        Spacer(modifier = Modifier.height(16.dp))



        Row(

            modifier = Modifier.height(30.dp).weight(1F),

        ) {
            CalcButton(
                label = "7",
                isOperation = false,
                onClick = onNumPressed,
                modifier = Modifier.weight(1F)

            )

            CalcButton(
                label = "8",
                isOperation = false,
                onClick = onNumPressed,
                modifier = Modifier.weight(1F)
            )
            CalcButton(
                label = "9",
                isOperation = false,
                onClick = onNumPressed,
                modifier = Modifier.weight(1F)
            )
            CalcButton(
                label = "*",
                isOperation = true,
                modifier = Modifier.weight(1F),
                onClick = onOperatorPressed,
            )
        }



        // Segunda linha: 4, 5, 6, -
        Row(
            modifier = Modifier.height(60.dp).weight(1F)
        ) {
            CalcButton(
                label = "4",
                isOperation = false,
                onClick = onNumPressed,
                modifier = Modifier.weight(1F)
            )
            CalcButton(
                label = "5",
                isOperation = false,
                onClick = onNumPressed,
                modifier = Modifier.weight(1F)
            )
            CalcButton(
                label = "6",
                isOperation = false,
                onClick = onNumPressed,
                modifier = Modifier.weight(1F)
            )
            CalcButton(
                label = "-",
                isOperation = true,
                modifier = Modifier.weight(1F),
                onClick = onOperatorPressed,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Terceira linha: 7, 8, 9, +
        Row(modifier = Modifier.height(60.dp).weight(1F)
        ) {
            CalcButton(
                label = "1",
                isOperation = false,
                onClick = onNumPressed,
                modifier = Modifier.weight(1F)
            )
            CalcButton(
                label = "2",
                isOperation = false,
                onClick = onNumPressed,
                modifier = Modifier.weight(1F)
            )
            CalcButton(
                label = "3",
                isOperation = false,
                onClick = onNumPressed,
                modifier = Modifier.weight(1F)
            )
            CalcButton(
                label = "+",
                isOperation = true,
                modifier = Modifier.weight(1F),
                onClick = onOperatorPressed,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Quarta linha: C, 0, /, =
        Row(
            modifier = Modifier.height(60.dp).weight(1F)
        ) {
            CalcButton(
                label = "=",
                isOperation = true,
                modifier = Modifier.weight(1F),
                onClick = onOperatorPressed,
            )
            CalcButton(
                label = "0",
                isOperation = false,
                onClick = onNumPressed,
                modifier = Modifier.weight(1F)
            )
            CalcButton(
                label = ".",
                isOperation = false,
                onClick = onNumPressed,
                modifier = Modifier.weight(1F)
            )
            CalcButton(
                label = "/",
                isOperation = true,
                modifier = Modifier.weight(1F),
                onClick = onOperatorPressed,
            )
        }


    }

}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorTheme {
        CalculatorScreen()
    }
}