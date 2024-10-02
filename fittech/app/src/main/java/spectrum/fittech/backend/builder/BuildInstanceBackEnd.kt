package spectrum.fittech.backend.builder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import spectrum.fittech.backend.interfaces.UsuarioInterface

val retrofit = Retrofit.Builder()
    .baseUrl("http://localhost:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiInterface = retrofit.create(UsuarioInterface::class.java)
