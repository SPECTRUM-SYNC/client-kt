package spectrum.fittech.backend.builder

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val gson: Gson = GsonBuilder()
    .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
    .create()


class LocalDateTimeAdapter : TypeAdapter<LocalDateTime>() {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun write(out: JsonWriter, value: LocalDateTime?) {
        if (value == null) {
            out.nullValue()
        } else {
            out.value(value.format(formatter))
        }
    }

    override fun read(`in`: JsonReader): LocalDateTime? {
        val dateStr = `in`.nextString()
        return LocalDateTime.parse(dateStr, formatter)
    }


}
