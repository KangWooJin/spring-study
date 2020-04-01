package kangwoojin.github.io.elasticsearch.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import net.bytebuddy.utility.RandomString
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.util.*

@Data
@Document(indexName = "model-stat", type = "_doc")
data class User(
        @Id
        val id: String = "",
        val name: String = "woojin",
        val createdDate: String = System.currentTimeMillis().toString(),
        @Field(type = FieldType.Date)
//        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        val testDate11: Long? = System.currentTimeMillis(),
        @Field(type = FieldType.Date)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
        val date: Date? = Date(),
        @Field(type = FieldType.Date)
        @JsonProperty("dateTime")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        val dateTime: LocalDateTime = LocalDateTime.now(),
        @Field(type = FieldType.Date)
        @JsonProperty("localDateStr")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        val localDateStr: String = LocalDateTime.now().toString(),
//        @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
//        @JsonProperty("zonedDateStr")
//        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
//        val zonedDateStr: String = ZonedDateTime.now().toString()
        @Field(type = FieldType.Date)
        @JsonProperty("offsetDateStr")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        val offsetDateStr: String = OffsetDateTime.now().toString(),
        @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty("dateStr")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val dateStr: String = "2019-01-01 01:01:01"
)
