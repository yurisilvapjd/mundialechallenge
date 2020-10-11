package com.yurisilva.mundialechallenge.exception;

import lombok.Getter;

@Getter
public class BusinessErrorDetails extends ErrorDetails{

    public BusinessErrorDetails() {
    }

    public static final class Builder {
        private String title;
        private int status;
        private String details;
        private Long timestamp;
        private String developerMessage;

        private Builder() {
        }

        public static Builder aBusinessErrorDetails() {
            return new Builder();
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withDetails(String details) {
            this.details = details;
            return this;
        }

        public Builder withTimestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public BusinessErrorDetails build() {
            BusinessErrorDetails businessErrorDetails = new BusinessErrorDetails();
            businessErrorDetails.status = this.status;
            businessErrorDetails.timestamp = this.timestamp;
            businessErrorDetails.details = this.details;
            businessErrorDetails.developerMessage = this.developerMessage;
            businessErrorDetails.title = this.title;
            return businessErrorDetails;
        }
    }
}
