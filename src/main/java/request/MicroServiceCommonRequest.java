package request;

import exception.CustomException;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

/**
 * Design pattern for builder
 *
 * @author chau
 */

public class MicroServiceCommonRequest {

    private String payload;
    private String queue;

    private MicroServiceCommonRequest(CommRequestBuilder builder) {
        this.payload = builder.payload;
        this.queue = builder.queue;
    }

    public String getPayload() {
        return payload;
    }

    public String getQueue() {
        return queue;
    }

    /**
     * This builder build common request to send by mq in micro services;
     */
    public static class CommRequestBuilder {
        private Integer event;
        private String payload;
        private String queue;

        /**
         * @param event   Event Id of request
         * @param payload POJO object will be convert to json
         * @param queue   queue to send message
         */
        public CommRequestBuilder(Integer event, Object payload, String queue) {
            try {
                this.event = event;
                this.payload = new JsonObject(Json.encodePrettily(payload))
                        .put("event", this.event)
                        .encodePrettily();
                this.queue = queue;
            } catch (DecodeException | EncodeException e) {
                throw new CustomException("This is not a json object");
            }
        }

        public CommRequestBuilder() {
            this.event = -1;
            this.payload = new JsonObject().encodePrettily();
            this.queue = null;
        }

        public CommRequestBuilder setEvent(Integer event) {
            this.event = event;
            return this;
        }

        public CommRequestBuilder setPayload(Object payload) {
            try {
                this.payload = new JsonObject(Json.encodePrettily(payload))
                        .put("event", this.event)
                        .encodePrettily();
            } catch (DecodeException | EncodeException e) {
                throw new CustomException("This is not a json object");
            }
            return this;
        }

        public CommRequestBuilder setQueue(String queue) {
            this.queue = queue;
            return this;
        }

        public MicroServiceCommonRequest build() {
            return new MicroServiceCommonRequest(this);
        }
    }
}
