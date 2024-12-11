package brandonnavarro.microservicio_cliente.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import brandonnavarro.microservicio_cliente.model.Cliente;
import brandonnavarro.microservicio_cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
   
    public Mono<Cliente> getClientePorCodigoUnico(String codigoUnico){

        String codigoUnicoDcryp = DesencriptarCodigoUnico(codigoUnico);
        
        Optional<Cliente> client = clienteRepository.findByCodigoUnico(codigoUnicoDcryp);
        return client.map(Mono::just).orElseGet(Mono::empty);
    }



    @Value("${encryption.secretKey}") String base64Key;
    public SecretKey ObtenerLlaveSecreta() {
        // Decodificar la clave Base64 y crear el objeto SecretKey
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }


    public String DesencriptarCodigoUnico(String encryptedData) {
         try {
            SecretKey secretKey = ObtenerLlaveSecreta();

            byte[] decodedData = hexToBytes(encryptedData);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedData = cipher.doFinal(decodedData);
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar datos", e);
        }
    }

    private byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                                 + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}
