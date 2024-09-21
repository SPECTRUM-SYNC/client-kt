package spectrum.fittech.backend.Object

object TokenManager {
    var token: String? = null
        private set // Impede a modificação direta de fora, apenas através do método `saveToken`

    fun saveToken(newToken: String?) {
        token = newToken
    }

    fun clearToken() {
        token = null
    }
}
