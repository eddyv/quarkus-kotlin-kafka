package io.datadove.kafka.processor

import io.datadove.kafka.model.Quote
import io.smallrye.reactive.messaging.annotations.Blocking
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Outgoing
import org.jboss.logging.Logger
import java.util.*
import javax.enterprise.context.ApplicationScoped


/**
 * A bean consuming data from the "quote-requests" Kafka topic (mapped to "requests" channel) and giving out a random quote.
 * The result is pushed to the "quotes" Kafka topic.
 */
@ApplicationScoped
class QuotesProcessor {
    private val random = Random()

    private val logger: Logger = Logger.getLogger(QuotesProcessor::class.java)

    @Incoming("requests")
    @Outgoing("quotes")
    @Blocking
    @Throws(
        InterruptedException::class
    )
    fun process(quoteRequest: String?): Quote {
        // simulate some hard working task

        val quote = Quote(quoteRequest, random.nextInt(100))
        logger.info("Sending quote $quote after 200ms")
        Thread.sleep(200)
        logger.info("Sending...")
        return quote
    }
}