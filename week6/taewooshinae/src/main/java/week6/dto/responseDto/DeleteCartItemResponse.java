package week6.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCartItemResponse {
    private boolean success;
    private String message;

    public DeleteCartItemResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
