package kangwoojin.github.io.elasticsearch.model

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.util.*

@Data
@Document(indexName = "model-stat", type = "_doc")
data class User(
        @Id
        val id: String = "",
        val name: String = "",
        val createdDate: String = "",
        @Field(type = FieldType.Date)
        @JsonFormat(shape = JsonFormat.Shape.NUMBER)
        val testDate11: Long? = null,
        @Field(type = FieldType.Date)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
        val date: Date? = null
//        @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
//        @JsonProperty("@timestamp")
//        val dateTime: String
)
