package kangwoojin.github.io.elasticsearch

import kangwoojin.github.io.elasticsearch.model.User
import kangwoojin.github.io.elasticsearch.model.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class ElasticsearchTests(@Autowired val userRepository: UserRepository) {

    @Test
    fun mappingTest() {
        val user = User("id",
                "woojin",
                System.currentTimeMillis().toString(),
//                "2019-01-01 00:01:01",
                System.currentTimeMillis(),
                Date(2019, 12, 1, 1, 1, 1)
        )

        println("save")
        val actual = userRepository.save(user)

        println(actual)

        println("find")
        val findUser = userRepository.findById("id").get()

        println(findUser)

        assertThat(actual).isNotNull
    }

}
