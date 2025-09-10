package likelion.shopping_mall.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 공통 응답용 dto
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class CommonResponse<T> {
    private T data;
    private boolean success;
    private String message;


    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(data,true,null);
    }

    public static <T> CommonResponse<T> success(T data, String message) {
        return new CommonResponse<>(data, true, message);
    }

    public static <T> CommonResponse<T> error(String message) {
        return new CommonResponse<>(null, false, message);
    }
}
