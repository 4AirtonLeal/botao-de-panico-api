package co.ao.BotaoDePanico.EndPoint;

import co.ao.BotaoDePanico.service.UserDetailService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Airton Leal
 */
public class Testes {

    static UserDetailService user;

    public static void main(String[] args) {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date date = new Date();
        //System.out.println(dateFormat.format(date));
        //Date d = new Date();
        //System.out.println(d);

//        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
//        System.out.println(encode.encode("hik3nlab20"));
        //System.out.println(user.getAgenteLogado().getNome());
        LocalDateTime agora = LocalDateTime.now();
        System.out.println("DTA: " + LocalDateTime.now());

        Date data = Date.from(agora.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("LocalDateTime = " + data);
    }
}
