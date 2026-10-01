package com.example.calculadoraimc

import android.R.attr.fontWeight
import android.R.attr.onClick
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
                            .size(360.dp)
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
                                    Text("Altura (cm)")
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF1D3557),
                                    unfocusedBorderColor = colorResource(id = R.color.azul_header),
                                    focusedLabelColor = colorResource(id = R.color.azul_header),
                                    unfocusedLabelColor = Color(0xFF858484)
                                ),
                                shape = RoundedCornerShape(15.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                )
                            )

                            Spacer(Modifier.size(20.dp))

                            OutlinedTextField(
                                value = peso,
                                onValueChange = {peso = it},
                                label = {
                                    Text("Peso (Kg)")
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF1D3557),
                                    unfocusedBorderColor = colorResource(id = R.color.azul_header),
                                    focusedLabelColor = colorResource(id = R.color.azul_header),
                                    unfocusedLabelColor = Color(0xFF858484)
                                ),
                                shape = RoundedCornerShape(15.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                )
                            )

                            Spacer(Modifier.size(25.dp))

                            Button(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 0.dp),
                                onClick = {

                                    if (
                                            altura
                                            .toDoubleOrNull() != null
                                            &&
                                            peso
                                                .toDoubleOrNull() != null
                                        ){
                                        imc = DecimalFormat("#,##0.0")
                                            .format(
                                                calcularIMC(altura.toDouble(), peso.toDouble())
                                            )

                                        categoriaIMC = definirCategoriaImc(imc.toDouble())
                                    }


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

                            Spacer(Modifier.size(1.dp))

                            Button(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 0.dp),
                                onClick = {
                                    altura = ""
                                    peso = ""
                                    imc = ""
                                    categoriaIMC = ""
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.azul_header),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "LIMPAR",
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
                            containerColor = colorirCard(categoriaIMC)
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
                                text = categoriaIMC, // <- Aqui será uma variável
                                fontSize = 20.sp,
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

fun calcularIMC(alturaCentimetro: Double, peso:Double): Double{
    if (alturaCentimetro == 0.0 || peso == 0.0)
        return 0.0

    val alturaMetro = alturaCentimetro / 100

    return peso / (alturaMetro * alturaMetro)
}
fun definirCategoriaImc(imc: Double): String{
    if (imc < 18.5) return "Abaixo do peso"
    else if(imc < 25) return "Peso Ideal"
    else if (imc < 30) return "Levemente acima do peso"
    else if (imc < 35) return "Obesidade Grau I"
    else if (imc < 40) return "Obesidade Grau II"
    else return "Obesidade Grau III"
}

@Composable
fun colorirCard(categoria: String): Color{
    if (categoria.equals(""))
        return Color(0xFFEDEDED)

    if (categoria.uppercase().equals("PESO IDEAL"))
        return colorResource(id = R.color.peso_ideal)
    else if (categoria.uppercase().equals("LEVEMENTE ACIMA DO PESO"))
        return colorResource(R.color.levemente_acima_peso)
    else
        return colorResource(R.color.peso_abaixo_obesidade)
}