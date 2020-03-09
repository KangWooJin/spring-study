package kangwoojin.github.io.elasticsearch.model

import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Data
@Document(indexName = "model-stat")
data class User(
        @Id
        val id: String,
        val name: String,
        val createdDate: String)