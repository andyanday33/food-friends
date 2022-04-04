package recipesharing.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
@SpringBootTest
class JWTUtilTest {

    @Test
    void testJWT(){
        String token = JWTUtil.createToken("user_id_fdjskfhuds");
        System.out.println(token);
        Map<String, Object> stringObjectMap = JWTUtil.checkToken(token);
        System.out.println(stringObjectMap.get("userId"));
    }
}