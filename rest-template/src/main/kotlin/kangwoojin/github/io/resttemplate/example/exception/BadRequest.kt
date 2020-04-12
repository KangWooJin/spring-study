package kangwoojin.github.io.resttemplate.example.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "custom bad request")
class BadRequest(exception: Exception) : Exception(exception) {
}