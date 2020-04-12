package kangwoojin.github.io.resttemplate.example.config

import kangwoojin.github.io.resttemplate.example.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.DefaultResponseErrorHandler
import java.io.IOException


class RestTemplateResponseErrorHandler : DefaultResponseErrorHandler() {
    @Throws(IOException::class)
    override fun hasError(httpResponse: ClientHttpResponse): Boolean {
        return super.hasError(httpResponse)
    }

    @Throws(IOException::class)
    override fun handleError(httpResponse: ClientHttpResponse) {
        if (httpResponse.statusCode.series() === HttpStatus.Series.SERVER_ERROR) {
            throw RuntimeException()
        } else if (httpResponse.statusCode.series() === HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
            if (httpResponse.statusCode === HttpStatus.NOT_FOUND) {
                throw NotFoundException(message = httpResponse.body.toString(), exception = null)
            }
        }
    }
}