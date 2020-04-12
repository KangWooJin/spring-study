package kangwoojin.github.io.resttemplate.example.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "not found exception")
class NotFoundException(exception: Exception?, message: String?) : Exception(exception) {

}