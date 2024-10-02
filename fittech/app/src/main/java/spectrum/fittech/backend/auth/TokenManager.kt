package spectrum.fittech.backend.auth

import android.content.Context
import android.content.SharedPreferences

object TokenManager {

    private const val PREFS_NAME = "auth_prefs"
    private const val TOKEN_KEY = "auth_token"

    // Salva o token
    fun saveToken(context: Context, newToken: String?) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(TOKEN_KEY, newToken)
            apply()
        }
    }

    // Recupera o token
    fun getToken(context: Context): String? {
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(TOKEN_KEY, null)  // Retorna null se o token não estiver armazenado
    }

    // Limpa o token
    fun clearToken(context: Context) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove(TOKEN_KEY)
            apply()
        }
    }
}
