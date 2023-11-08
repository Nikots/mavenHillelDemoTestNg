package hillel.testNgDemo;

import hillel.ErrorCodeMapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ErrorCodeMapperTest {
    ErrorCodeMapper errorCodeMapper = new ErrorCodeMapper();

    @Test
    @Parameters("code")
    void shouldReturnNotSupported(@Optional("-1") Integer code) {
        assertEquals("Not Supported", errorCodeMapper.getMessage(code));
    }

    @Test(dataProvider = "errorData")
    void shouldReturnCorrectErrorMessage(Integer code, String expectedMessage) {
        System.out.println("test3");
        assertEquals(expectedMessage, errorCodeMapper.getMessage(code));
    }

    @DataProvider
    public Object[][] errorData() {
        return new Object[][]{
                {100, "Continue"},
                {200, "OK"},
                {302, "Found"}
        };
    }

}