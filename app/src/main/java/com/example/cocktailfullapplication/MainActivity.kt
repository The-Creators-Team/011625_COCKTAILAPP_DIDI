package com.example.cocktailfullapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cocktailfullapplication.ui.FirebaseAuthHelper
import com.example.cocktailfullapplication.ui.theme.CocktailFullApplicationTheme
import com.example.cocktailfullapplication.ui.viewmodel.CocktailViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailFullApplicationTheme {
                // Start the authentication flow
                AuthenticationScreen()
            }
        }
    }
}

@Composable
fun AuthenticationScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSignedIn by remember { mutableStateOf(false) }

    if (!isSignedIn) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Welcome!", fontSize = 50.sp)
            Spacer(modifier = Modifier.height(50.dp))
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                FirebaseAuthHelper.signInWithEmailAndPassword(email, password) { success, error ->
                    if (success) {
                        isSignedIn = true
                    } else {
                        // Handle error
                    }
                }
            }) {
                Text("Login")
            }
        }
    } else {
        CocktailSearchScreen()
    }
}

@Composable
fun CocktailSearchScreen() {
    val viewModel: CocktailViewModel = CocktailViewModel()
    var query by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row {
            BasicTextField(value = query, onValueChange = { query = it })
            Button(onClick = { viewModel.searchCocktails(query)
            println(viewModel.cocktails)
            }) {
                Text("Search")
            }
        }

        Row {
            BasicTextField(value = category, onValueChange = { category = it })
            Button(onClick = { viewModel.filterCocktails(category) }) {
                Text("Filter")
            }
        }

        LazyColumn {
            items(viewModel.cocktails) { cocktail ->
                Text(cocktail.strDrink)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthenticationScreenPreview(){
    AuthenticationScreen()
}

@Preview(showBackground = true)
@Composable
fun CocktailSearchScreenPreview(){
    CocktailSearchScreen()
}






