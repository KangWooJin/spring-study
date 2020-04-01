package kangwoojin.github.io.elasticsearch.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.data.elasticsearch.core.EntityMapper
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter
import org.springframework.data.elasticsearch.core.geo.CustomGeoModule
import java.io.IOException


@Configuration
class ElasticsearchConfig {
    @Bean
    fun elasticsearchTemplate(client: RestHighLevelClient, converter: ElasticsearchConverter): ElasticsearchRestTemplate {
        return try {
            ElasticsearchRestTemplate(client, converter, object : EntityMapper {
                private val objectMapper = ObjectMapper()

                @Throws(IOException::class)
                override fun mapToString(`object`: Any): String {
                    return objectMapper.writeValueAsString(`object`)
                }

                override fun <T : Any?> readObject(source: MutableMap<String, Any>?, targetType: Class<T>?): T? {
                    TODO("Not yet implemented")
                    println("readObject")
                }

                @Throws(IOException::class)
                override fun <T> mapToObject(source: String, clazz: Class<T>): T {
                    return objectMapper.readValue(source, clazz)
                }

                override fun mapObject(source: Any?): MutableMap<String, Any> {
                    TODO("Not yet implemented")
                    println("mapObject")
                }

                init {
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
//                    objectMapper.registerModule(CustomGeoModule())
//                    objectMapper.registerModule(JavaTimeModule())
                    objectMapper.findAndRegisterModules()
                }
            })
        } catch (ex: Exception) {
            throw IllegalStateException(ex)
        }
    }
}