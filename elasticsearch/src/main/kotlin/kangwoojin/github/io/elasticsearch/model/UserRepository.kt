package kangwoojin.github.io.elasticsearch.model

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface UserRepository : ElasticsearchRepository<User, String> {

    fun findByName(name: String): List<User>
}