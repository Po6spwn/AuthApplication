package com.example.myapplication.util

/* Класс, для обработки полученного от запроса результата*/
data class ConnectionResult(
    val success: User? = null,
    val error: String? = null
)
