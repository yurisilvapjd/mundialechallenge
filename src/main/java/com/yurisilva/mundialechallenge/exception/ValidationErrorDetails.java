package com.yurisilva.mundialechallenge.exception;

import lombok.Getter;

@Getter
public class ValidationErrorDetails extends ErrorDetails {

    private String field;
    private String fieldMessage;


    public static final class Builder {

        protected String title;
        protected int status;
        protected String details;
        protected Long timestamp;
        protected String developerMessage;
        private String field;
        private String fieldMessage;

        private Builder() {
        }

        public static Builder aValidationErrorDetails() {
            return new Builder();
        }

        public Builder withField(String field) {
            this.field = field;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withFieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
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

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
            validationErrorDetails.setTitle(title);
            validationErrorDetails.setStatus(status);
            validationErrorDetails.setDetails(details);
            validationErrorDetails.setTimestamp(timestamp);
            validationErrorDetails.setDeveloperMessage(developerMessage);
            validationErrorDetails.field = this.field;
            validationErrorDetails.fieldMessage = this.fieldMessage;
            return validationErrorDetails;
        }
    }
}
