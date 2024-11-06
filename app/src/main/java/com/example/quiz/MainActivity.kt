package com.example.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.quiz.ui.theme.QuizTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ){
                    QuizLayout()
                }
            }
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()

    NavHost( navController = navController, startDestination = "home") {

        // Here we declare all screens,
        // giving them a "route", which is a unique name
        // for each one.

        composable(route = "home") {
            HomeScreen()
        }

        composable(route = "details") {
            DetailsScreen()
        }

    }

}

@Composable
fun QuizLayout(){
    var message by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            . statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.question),
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(75.dp))
        EditTextField(
            value = answer,
            onValueChange = {answer = it},
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        Button(onClick = {
                if (answer == "yes"){
                    message = "correct"
                }else{
                    message = "wrong"
                }
            },
            modifier = Modifier
                .background(color = Color.Red)
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.height(75.dp))
        Text(
            text = message,
            modifier = Modifier
        )
    }
}

@Composable
fun EditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.input_holder)) },
        modifier = modifier
    )
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Text(
        text = "Submit",
        modifier = modifier
            .clickable(onClick = onClick)
    )
}

@Composable()
fun HomeScreen() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "This is the home screen",
    )
}

@Composable()
fun DetailsScreen() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "This is the details screen",
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizTheme {
        QuizLayout()
    }
}