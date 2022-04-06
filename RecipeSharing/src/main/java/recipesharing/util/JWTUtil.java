package recipesharing.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Generate & verify a json web token for user
 */
public class JWTUtil {
    private static final String PRIVATE_KEY = "12Msz@##lu!#$$";

    /**
     * generate a jwt
     */
    public static String getToken(Map<String, String> map) {
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7); //expired by 7 days

        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(PRIVATE_KEY));
    }

    public static String getToken(String email, String password) {
        JWTCreator.Builder builder = JWT.create();

        //payload
        Map map = new HashMap();
        map.put("email", email);
        map.put("password", password);

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7); //expired by 7 days

        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(PRIVATE_KEY));
    }
    /**
     * verify jwt
     */
    public static DecodedJWT verify(String token) {
        //throws exceptions
        return JWT.require(Algorithm.HMAC256(PRIVATE_KEY)).build().verify(token);
    }


}
