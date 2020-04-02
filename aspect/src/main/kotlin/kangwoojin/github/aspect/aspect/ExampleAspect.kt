package kangwoojin.github.aspect.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class ExampleAspect {
    private val log = LoggerFactory.getLogger(javaClass)

    @Before("@annotation(LogExecutionTime)")
    fun before(joinPoint: JoinPoint) {
        log.info("before")
    }

    @Around("@annotation(LogExecutionTime)")
    @Throws(Throwable::class)
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis()

//        val flag = true
//        var proceed = joinPoint.proceed(arrayOf<Any>(flag))

        var proceed = joinPoint.proceed()
        val executionTime = System.currentTimeMillis() - start

        log.info("${joinPoint.signature} excuted in ${executionTime}ms")
        return proceed
    }

    @After("@annotation(LogExecutionTime)")
    fun after(joinPoint: JoinPoint) {
        log.info("after")
    }

    @AfterReturning(value = "@annotation(LogExecutionTime)", returning = "resource")
    fun afterReturning(resource: Any?) {
        log.info("after Returning $resource")
    }

    @AfterThrowing(value = "@annotation(LogExecutionTime)", throwing = "exception")
    fun afterThrowing(exception: Exception) {
        log.info("after Throwing", exception)
    }

}