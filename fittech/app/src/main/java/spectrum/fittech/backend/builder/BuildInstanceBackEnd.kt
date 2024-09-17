package spectrum.fittech.backend.builder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import spectrum.fittech.backend.interfaces.ApiInterface

val retrofit = Retrofit.Builder()
    .baseUrl("https://localhost:8080")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiInterface = retrofit.create(ApiInterface::class.java)
