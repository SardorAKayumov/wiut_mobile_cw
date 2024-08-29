package com.example.mad_cw_00011193.detailedView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad_cw_00011193.R

@Composable
fun DetailedView(productId: String, viewModel: DetailedViewModel = DetailedViewModel(productId)) {

    val product by viewModel.productLiveData.observeAsState()

    if (product != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.product_detailed_view_bg))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Name(name = product!!.name)
                Spacer(Modifier.weight(1f))
                Price(price = product!!.price)
            }
            Description(description = product!!.description)
            MyDivider()
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun Name(name: String) {
    Text(
        text = name,
        color = Color.Black,
        fontSize = 30.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Price(price: String) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.detailed_view_price_label, price),
        color = Color.Black,
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
private fun Description(description: String) {
    Text(
        modifier = Modifier.padding(top = 10.dp),
        text = description,
        color = Color.DarkGray,
        fontSize = 22.sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
private fun MyDivider() {
    Divider(
        color = Color.LightGray
    )
}