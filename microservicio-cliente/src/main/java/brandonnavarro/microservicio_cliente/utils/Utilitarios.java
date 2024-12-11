package brandonnavarro.microservicio_cliente.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;

public class Utilitarios {
    
    //Metodo para generar una llave de encriptacion que luego es usada para desencriptar el codigo unico.
    // @GetMapping("/keyGenerador")    
    public String GeneradordeLlaveSecreta() {   
        try {
            // Generar una clave secreta (deberías guardarla de manera segura)
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            return  Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar el generador de claves", e);
        }       
    }

    //Metodo para encriptar codigo Unico en teoria deberia ejecutarse al grabar un cliente
    @Value("${encryption.secretKey}") String base64Key;
    // @GetMapping("/Encryp/{data}")
    public String EncriptarCodigoUnico(@PathVariable String data) {  
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        SecretKey sKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar datos", e);
        }       
    }

    //Metodo para convertir de Bytes a Hexadecimal
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
