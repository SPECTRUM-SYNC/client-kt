package spectrum.fittech.backend.auth

object IdUserManager {
        var userId : Int? = null
            private set

        fun saveId(idUser : Int?) {
            userId = idUser
        }

        fun clearId() {
            userId = null
        }
}