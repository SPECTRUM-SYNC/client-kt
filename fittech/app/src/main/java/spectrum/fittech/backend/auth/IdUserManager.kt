package spectrum.fittech.backend.Object

import android.content.Context
import android.content.SharedPreferences
import spectrum.fittech.backend.auth.TokenManager

object IdUserManager {

    private const val PREFS_NAME = "user_prefs"
    private const val USER_ID_KEY = "user_id"
    private const val USER_NAME_KEY = "user_name"
    private const val USER_NAME_PIC = "user_pic"


    // Salva o ID do usuário
    fun saveId(context: Context, idUser: Int?) {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(USER_ID_KEY, idUser ?: -1)  // Armazena -1 se o ID for nulo
            apply()
        }
    }

    // Recupera o ID do usuário
    fun getId(context: Context): Int? {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val id = sharedPref.getInt(USER_ID_KEY, -1)
        return if (id != -1) id else null  // Retorna null se o ID armazenado for -1
    }

    // Limpa o ID do usuário
    fun clearId(context: Context) {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove(USER_ID_KEY)
            apply()
        }
    }

    // Salva o nome do usuário
    fun saveUserName(context: Context, userName: String?) {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(USER_NAME_KEY, userName)
            apply()
        }
    }

    // Recupera o nome do usuário
    fun getUserName(context: Context): String? {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(
            USER_NAME_KEY,
            null
        )  // Retorna null se o nome não estiver armazenado
    }

    // Limpa o nome do usuário
    fun clearUserName(context: Context) {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove(USER_NAME_KEY)
            apply()
        }
    }

    // Salva a foto do usuário
    fun saveUserPic(context: Context, userPic: String?) {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(USER_NAME_PIC, userPic)
            apply()
        }
    }

    // Recupera a foto do usuário
    fun getUserPic(context: Context): String? {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(
            USER_NAME_PIC,
            null
        )  // Retorna null se o nome não estiver armazenado
    }

    // Limpa a foto do usuário
    fun clearUserPic(context: Context) {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove(USER_NAME_PIC)
            apply()
        }
    }

    fun clearAll(context: Context) {
        clearId(context)
        clearUserName(context)
        clearUserPic(context)
        TokenManager.clearToken(context)
    }
}