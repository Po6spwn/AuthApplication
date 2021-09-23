package com.example.myapplication.unidentified

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.util.ConnectionResult
import android.net.NetworkInfo

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


class UnidentifiedViewModel : ViewModel() {
    private val _liveDataCurrency = MutableLiveData<Boolean>()
    var liveDataCurrency: LiveData<Boolean> = _liveDataCurrency

    //Проверка на интернет-соединение
     fun isInternetAvailable(context: Context) {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities)

        if(actNw!= null && networkCapabilities!= null )
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

        _liveDataCurrency.value = result
    }
}