package kangwoojin.github.io.elasticsearch

import kangwoojin.github.io.elasticsearch.model.User
import kangwoojin.github.io.elasticsearch.model.UserRepository
import net.bytebuddy.utility.RandomString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*

@SpringBootTest
class ElasticsearchTests(@Autowired val userRepository: UserRepository) {

    @Test
    fun mappingTest() {
        val id = RandomString.make()
        val name = "woojin"
        val user = User(id)

        println("save")
        val actual = userRepository.save(user)

        println(actual)

        println("findById")
        val findUser = userRepository.findById(id).get()

        println(findUser)

        println("findbyName")

        val userList = userRepository.searchSimilar(findUser, arrayOf("name"), Pageable.unpaged())

        userList.forEach(System.out::println)

        assertThat(actual).isNotNull
    }

}
