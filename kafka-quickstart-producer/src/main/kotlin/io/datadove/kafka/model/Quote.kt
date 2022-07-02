package io.datadove.kafka.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.runtime.annotations.RegisterForReflection

// register for reflection is required for native builds to handle serialization/deserialization with jackson
@RegisterForReflection
data class Quote(
    @field:JsonProperty("id") val id: String? = null, @field:JsonProperty("price") val price: Int? = null
) {
    override fun toString(): String {
        return "Quote{id='$id', price=$price}";
    }
}