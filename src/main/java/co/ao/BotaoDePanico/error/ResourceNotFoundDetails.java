package co.ao.BotaoDePanico.error;

/**
 *
 * @author Airton Leal
 */
public class ResourceNotFoundDetails extends ErrorDetails {

    public static class Builder {

        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;

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

        public ResourceNotFoundDetails build() {
            return new ResourceNotFoundDetails(title, status, detail, timestamp, developerMessage);
        }
    }

    public static ResourceNotFoundDetails.Builder builder() {
        return new ResourceNotFoundDetails.Builder();
    }

    private ResourceNotFoundDetails(final String title, final int status, final String detail, final long timestamp, final String developerMessage) {
        this.setTitle(title);
        this.setStatus(status);
        this.setDetail(detail);
        this.setTimestamp(timestamp);
        this.setDeveloperMessage(developerMessage); 
    }

}
