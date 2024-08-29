package com.example.mad_cw_00011193.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_cw_00011193.models.Product
import com.example.mad_cw_00011193.network.RetrofitInstance
import com.example.mad_cw_00011193.network.myResponse.MyListResponse
import com.example.mad_cw_00011193.network.product.ProductResponse
import com.example.mad_cw_00011193.utils.Constants
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    val productsLiveData: MutableLiveData<List<Product>> by lazy {
        MutableLiveData<List<Product>>()
    }

    init {
        getListOfProductsFromRemoteDb()
//        deleteAllProducts()
    }

    fun getListOfProductsFromRemoteDb() {
        viewModelScope.launch {
            try {
                val response: MyListResponse<ProductResponse> =
                    RetrofitInstance.productService.getAllProducts(Constants.STUDENT_ID)
                val productsFromResponse = response.data

                if (productsFromResponse != null) {
                    val myProducts = mutableListOf<Product>()

                    for (productFromResponse in productsFromResponse) {
                        myProducts.add(
                            Product(
                                productFromResponse.id,
                                productFromResponse.name,
                                productFromResponse.description,
                                productFromResponse.price
                            )
                        )
                    }

                    productsLiveData.value = myProducts
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

//    fun deleteAllProducts() {
//        viewModelScope.launch {
//            try {
//
//                val response: MyResponse = RetrofitInstance.productService.deleteAllProducts(
//                    Constants.STUDENT_ID
//                )
//
//                Log.d("Delete_response", response.toString())
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
}