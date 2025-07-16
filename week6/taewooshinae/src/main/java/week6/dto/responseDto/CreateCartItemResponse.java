package week6.dto.responseDto;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

public class CreateCartItemResponse {
    private Boolean success;
    private Data data;

    public CreateCartItemResponse(boolean success, Long productId, LocalDateTime addedAt) {
        this.success = success;
        this.data = new Data(productId, addedAt);
    }

    public CreateCartItemResponse(boolean success) {
        this.success = success;
        this.data = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public Data getData() {
        return data;
    }


    public static class Data {
        private Long productId;
        private LocalDateTime addedAt;

        public Data(Long productId, LocalDateTime addedAt) {
            this.productId = productId;
            this.addedAt = addedAt;
        }

        public Long getProductId() {
            return productId;
        }

        public LocalDateTime getAddedAt() {
            return addedAt;
        }
    }
}
