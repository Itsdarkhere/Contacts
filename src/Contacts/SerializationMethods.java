package Contacts;

import java.io.*;

class SerializationMethods{

    //this writes the "object" into a file
    public void serialize(Object object, String fileName) throws IOException {
        FileOutputStream output = new FileOutputStream(fileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(output);
        ObjectOutputStream objectOutput = new ObjectOutputStream(bufferedOutputStream);
        objectOutput.writeObject(object);
        objectOutput.close();

    }

    //this reconstructs the "object" from a file
    public Object deserialize(String fileName) throws IOException, ClassNotFoundException{
        FileInputStream input = new FileInputStream(fileName);
        BufferedInputStream buffInput = new BufferedInputStream(input);
        ObjectInputStream objectInput = new ObjectInputStream(buffInput);
        Object object = objectInput.readObject();
        objectInput.close();

        return object;

    }
}
