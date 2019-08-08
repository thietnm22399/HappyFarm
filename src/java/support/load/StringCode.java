/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support.load;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import model.Player;

/**
 *
 * @author nguye
 */
public class StringCode {

    public static String encode(Object o) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.flush();
        final String objectString = new String(Base64.getEncoder().encode(baos.toByteArray()));
        return objectString;
    }

    public static Object decode(String base64String) throws IOException, ClassNotFoundException {
        final byte[] objToBytes = Base64.getDecoder().decode(base64String);
        ByteArrayInputStream bais = new ByteArrayInputStream(objToBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object o = (Object) ois.readObject();
        return o;
    }
}
