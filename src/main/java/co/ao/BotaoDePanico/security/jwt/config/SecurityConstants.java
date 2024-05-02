package co.ao.BotaoDePanico.security.jwt.config;

//import java.time.Duration;
//import java.util.concurrent.TimeUnit;

/**
 *
 * @author Airton Leal
 */
public class SecurityConstants {

    static final String SECRET = "faztudo";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/user/sign-up";
    static final long EXPIRATION_TIME = 86400000L;
    
//    public static void main(String[] args) {
//        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
//    }
    
}
