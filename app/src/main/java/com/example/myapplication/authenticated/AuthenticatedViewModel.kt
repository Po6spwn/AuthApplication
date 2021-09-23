package com.example.myapplication.authenticated

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.util.ConnectionResult
import com.example.myapplication.util.User
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response


class AuthenticatedViewModel : ViewModel() {

    private val _liveDataCurrency = MutableLiveData<ConnectionResult>()
    var liveDataCurrency: LiveData<ConnectionResult> = _liveDataCurrency

    fun getValue() {
        viewModelScope.launch {
            try {
                //Формируем тело запроса
                val requestBody: RequestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("grant_type", "password")
                    .addFormDataPart("username", "test-user")
                    .addFormDataPart("password", "qlFUaO7N")
                    .build()

                // Осуществляем запрос
                val response = RetrofitBuilder.apiService.getInfoUser("Basic YXJiaW5hX2luZnJhX2lvc190ZXN0OmRtTEpMcTlUVUZCNw==",requestBody)

                if (response.isSuccessful)
                    _liveDataCurrency.value = ConnectionResult (success = response.body())
            } catch (t: Throwable) {
                _liveDataCurrency.value = ConnectionResult (error = t.message)
            }
        }
    }
}