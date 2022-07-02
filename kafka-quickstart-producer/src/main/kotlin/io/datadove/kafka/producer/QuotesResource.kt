package io.datadove.kafka.producer

import io.datadove.kafka.model.Quote
import io.smallrye.mutiny.Multi
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import java.util.*
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/quotes")
class QuotesResource {
    @Channel("quote-requests")
    var quoteRequestEmitter: Emitter<String>? = null

    /**
     * Endpoint to generate a new quote request id and send it to "quote-requests" Kafka topic using the emitter.
     */
    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    fun createRequest(): String {
        val uuid = UUID.randomUUID()
        quoteRequestEmitter!!.send(uuid.toString())
        return uuid.toString()
    }


    @Channel("quotes")
    var quotes: Multi<Quote?>? = null

    /**
     * Endpoint retrieving the "quotes" Kafka topic and sending the items to a server sent event.
     */
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    fun stream(): Multi<Quote?>? {
        return quotes
    }
}