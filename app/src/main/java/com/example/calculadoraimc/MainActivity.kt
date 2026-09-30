package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeighttat
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier) {
    var altura by remember {
        mutableStateOf("")
    }
    var peso by remember {
        mutableStateOf("")
    }
    var imc by remember {
        mutableStateOf("")
    }
    var categoriaIMC by remember {
        mutableStateOf("")
    }


    Column(
        modifier = modifier.fillMaxSize().background(Color.White)
    )
    {
        Box(modifier = Modifier.fillMaxSize()){
            Column(
                modifier = Modifier.fillMaxWidth()
            )
            {
                /*Header*/
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .height(160.dp)
                        .background(color = colorResource(id = R.color.azul_header)),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Image(
                        painter = painterResource(R.drawable.bmi),
                        contentDescription = "BMI Image",
                        modifier = Modifier.size(80.dp)
                            .padding(vertical = 16.dp)
                    )

                    Text(
                        text = "Calculadora IMC",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }


                Column(
                    modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 32.dp)
                )
                {
                    /*Foms*/

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .offset(y = (-30).dp) // O offset tira o componente da orientação padrão do layout movendo -30.dp, ou seja, o elevando 30.dp

                        ,
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF9F6F6)
                        ),
                        elevation = CardDefaults.cardElevation(4.dp), // Realiza o sombreamento externo no card
                    )
                    {
                        Column(
                            modifier = Modifier.fillMaxSize().padding(horizontal = 15.dp, vertical = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            Text(
                                text = "Seus Dados",
                                color = colorResource(id = R.color.azul_header),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(Modifier.size(10.dp))

                            OutlinedTextField(
                                value = altura,
                                onValueChange = {altura = it},
                                label = {
                                    Text("Altura")
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = colorResource(id = R.color.azul_header),
                                    focusedLabelColor = colorResource(id = R.color.azul_header)
                                ),
                                shape = RoundedCornerShape(15.dp)
                            )

                            Spacer(Modifier.size(20.dp))

                            OutlinedTextField(
                                value = peso,
                                onValueChange = {peso = it},
                                label = {
                                    Text("Peso")
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = colorResource(id = R.color.azul_header),
                                    focusedLabelColor = colorResource(id = R.color.azul_header)
                                ),
                                shape = RoundedCornerShape(15.dp)
                            )

                            Button(
                                modifier = Modifier.fillMaxWidth().padding(15.dp),
                                onClick = {
                                     imc = DecimalFormat("#,##0.0")
                                         .format(
                                             calcularIMC(altura.toDouble(), peso.toDouble())
                                         )
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.azul_header),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "CALCULAR",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    Spacer(Modifier.size(50.dp))

                    /*Card resultado*/

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                        ,
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF329f6B)
                        ),
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    )
                    {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){


                            Text(
                                text = imc, // <- Aqui será uma variável
                                fontSize = 28.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(Modifier.size(30.dp))

                            Text(
                                text = "Peso Ideal", // <- Aqui será uma variável
                                fontSize = 28.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

fun calcularIMC(altura: Double, peso:Double): Double{
    if (altura == 0.0 || peso == 0.0)
        return 0.0

    return peso / (altura * altura)
}