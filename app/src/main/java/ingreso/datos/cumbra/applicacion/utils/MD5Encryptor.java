package ingreso.datos.cumbra.applicacion.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryptor {

    public static final String md5(final String s) {
        String salida="";
        try {
            byte[] b = s.getBytes(StandardCharsets.UTF_8);
            for (int i = 0; i < b.length; i++) {
                salida=salida+""+b[i];
            }
            return salida;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
