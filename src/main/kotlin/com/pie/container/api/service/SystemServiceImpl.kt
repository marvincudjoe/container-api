import com.pie.container.api.service.DaemonServiceImpl
import com.pie.container.api.service.SystemService
import com.pie.container.api.utils.logger
import com.pie.container.api.utils.readMessage
import com.pie.container.api.utils.setGetRequest
import org.springframework.stereotype.Service

@Service
class SystemServiceImpl : SystemService {

    private var daemonService = DaemonServiceImpl()

    override fun pingDaemon() {
        daemonService.sendRequest(setGetRequest("/_ping"))
    }

    override fun apiVersion() {
        daemonService.sendRequest(setGetRequest("/version")).apply {
            // todo: return result as JSON
            logger.info("API Version is ${body.readMessage()}")
        }
    }
}
