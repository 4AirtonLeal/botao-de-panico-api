package co.ao.BotaoDePanico.error;

/**
 *
 * @author Airton Leal
 */
public class ValidationErrorDetails extends ErrorDetails {

    private final String field;
    private final String fieldMessage;

    public String getField() {
        return field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public static class Builder {

        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;
        private String field;
        private String fieldMessage;

        Builder() {
        }

        public Builder title(final String value) {
            this.title = value;
            return this;
        }

        public Builder status(final int value) {
            this.status = value;
            return this;
        }

        public Builder detail(final String value) {
            this.detail = value;
            return this;
        }

        public Builder timestamp(final long value) {
            this.timestamp = value;
            return this;
        }

        public Builder developerMessage(final String value) {
            this.developerMessage = value;
            return this;
        }
        public Builder field(final String value) {
            this.field = value;
            return this;
        }
        public Builder fieldMessage(final String value) {
            this.fieldMessage = value;
            return this;
        }

        public ValidationErrorDetails build() {
            return new ValidationErrorDetails(title, status, detail, timestamp, developerMessage, field, fieldMessage);
        }
    }

    public static ValidationErrorDetails.Builder builder() {
        return new ValidationErrorDetails.Builder();
    }

    private ValidationErrorDetails(final String title, final int status, final String detail, final long timestamp, final String developerMessage, final String field, final String fieldMessage) {
        this.setTitle(title);
        this.setStatus(status);
        this.setDetail(detail);
        this.setTimestamp(timestamp);
        this.setDeveloperMessage(developerMessage);
        this.field = field;
        this.fieldMessage = fieldMessage;
    }

    

}
