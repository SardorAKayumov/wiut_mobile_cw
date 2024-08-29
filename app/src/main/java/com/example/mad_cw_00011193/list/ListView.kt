package com.example.mad_cw_00011193.list

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad_cw_00011193.R
import com.example.mad_cw_00011193.addNew.AddNewActivity
import com.example.mad_cw_00011193.models.Product

@Composable
fun ListView(
    onProductClick: (String) -> Unit = {},
    viewModel: ListViewModel = ListViewModel()
) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {

        val products by viewModel.productsLiveData.observeAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    colorResource(R.color.product_list_bg)
                )
                .padding(0.dp, 0.dp, 0.dp, 50.dp),
        ) {
            products?.let {
                items(items = it.toList(), itemContent = { item ->
                    ProductItem(product = item, onProductClick)
                })
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(0.dp, 0.dp, 0.dp, 70.dp),
            onClick = { context.startActivity(Intent(context, AddNewActivity::class.java)) }) {
            Text(
                stringResource(id = R.string.list_add_new_text),
                modifier = Modifier.padding(15.dp, 5.dp),
                color = colorResource(id = R.color.add_new_product_text_color)
            )
        }
    }

}

@Composable
fun ProductItem(product: Product, onProductClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 10.dp, 15.dp, 0.dp)
            .clickable {
                onProductClick(product.id.toString())
            }
    ) {
        Name(name = product.name)
        Description(description = product.description)
        Divider(
            modifier = Modifier
                .padding(top = 10.dp)
                .background(Color.Gray)
        )
    }
}

@Composable
private fun Name(name: String) {
    Text(
        text = name,
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Description(description: String) {
    Text(
        text = description,
        color = Color.DarkGray,
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif
    )
}
