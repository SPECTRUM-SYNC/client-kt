package spectrum.fittech.backend.Object

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