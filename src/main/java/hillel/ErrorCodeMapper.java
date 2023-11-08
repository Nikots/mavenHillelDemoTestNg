package hillel;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeMapper {
    private Map<Integer, String> errorCodeToMessage = new HashMap<>();
    private Map<String, Integer> errorMessageToCode = new HashMap<>();

    public ErrorCodeMapper() {
        // Ініціалізація відповідності кодів та повідомлень про помилку
        errorCodeToMessage.put(100, "Continue");
        errorCodeToMessage.put(102, "Processing");
        errorCodeToMessage.put(200, "OK");
        errorCodeToMessage.put(201, "CREATED");
        errorCodeToMessage.put(302, "Found");
        errorCodeToMessage.put(304, "Not Modified");
        errorCodeToMessage.put(400, "Bad Request");
        errorCodeToMessage.put(429, "Too Many Requests");
        errorCodeToMessage.put(500, "Internal Server Error");
        errorCodeToMessage.put(502, "Bad Gateway");

        // Заповнення обернених відповідностей
        for (Map.Entry<Integer, String> entry : errorCodeToMessage.entrySet()) {
            errorMessageToCode.put(entry.getValue(), entry.getKey());
        }
    }

    public String getMessage(Integer errorCode) {
        if (errorCode == null) return "Cannot be null";
        return errorCodeToMessage.getOrDefault(errorCode, "Not Supported");
    }

    public Integer getErrorCode(String errorMessage) {
        return errorMessageToCode.getOrDefault(errorMessage, null);
    }
}