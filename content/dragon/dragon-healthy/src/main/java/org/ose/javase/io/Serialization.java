package org.ose.javase.io;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/*
public class Serialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serialize();
        externalize();
        serializeSubclass();
        serializeProxy();
    }

    private static void serialize() {
        String filePath = "employee.ser";

        Employee emp = new Employee(100, "Pankaj", 5000);

        try {
            SerializationUtil.serialize(emp, filePath);
            System.out.println("Serialized Employee: " + emp);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {

            Employee empNew = (Employee) SerializationUtil.deserialize(filePath);

            System.out.println("Deserialized Employee: " + empNew);

        } catch (ClassNotFoundException || IOException e) {
            e.printStackTrace();
        }
        new File(filePath).delete();
    }

    private static void externalize() {
        String filePath = "person.ser";

        Person person = new Person(1, "Pankaj", "Male");

        try {
            SerializationUtil.serialize(person, filePath);
            System.out.println("Serialized Person: " + person);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Person p = (Person) SerializationUtil.deserialize(filePath);
            System.out.println("Deserialized Person: " + p);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        new File(filePath).delete();
    }

    private static void serializeSubclass() {
        String filePath = "subclass.ser";

        SubClass subClass = new SubClass();
        subClass.setId(10);
        subClass.setValue("Data");
        subClass.setName("Pankaj");

        try {
            SerializationUtil.serialize(subClass, filePath);
            System.out.println("Serialized SubClass: " + subClass);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            SubClass subNew = (SubClass) SerializationUtil.deserialize(filePath);
            System.out.println("Deserialized SubClass: " + subNew);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        new File(filePath).delete();
    }

    private static void serializeProxy() {
        String filePath = "data.ser";

        Data data = new Data("Pankaj");

        try {
            SerializationUtil.serialize(data, filePath);
            System.out.println("Serialized Data: " + data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Data newData = (Data) SerializationUtil.deserialize(filePath);
            System.out.println("Deserialized Data: " + newData);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        new File(filePath).delete();
    }
}

class SerializationUtil {
    // serialize the given object and save it to file
    public static void serialize(Object obj, String filePath) throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(filePath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    // deserialize to Object from given file
    public static Object deserialize(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(filePath);
            ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();

            return obj;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }
}

class Employee implements Serializable {
    // used to verify compatibility, throws InvalidClassExceptions is not compatible
    // Use case: class fields are extended after serialization.
    private static final long serialVersionUID = -6470090944414208496L;

    private int               id;
    private String            name;
    transient private int     salary;                                  // transient fields will not be serialized

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{name = " + name + ", id = " + id + ", salary = " + salary + "}";
    }
}

class Person implements Externalizable {
    private int    id;
    private String name;
    private String gender;

    public Person() {
    } // default constructor is required

    public Person(int id, String name, String gener) {
        this.id = id;
        this.name = name;
        this.gender = gener;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeObject(name + "xyz");
        out.writeObject("abc" + gender);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        name = (String) in.readObject();
        if (!name.endsWith("xyz")) {
            throw new IOException("corrupted data");
        }
        name = name.substring(0, name.length() - 3);
        gender = (String) in.readObject();
        if (!gender.startsWith("abc")) {
            throw new IOException("corrupted data");
        }
        gender = gender.substring(3);
    }

    @Override
    public String toString() {
        return "Person{id = " + id + ", name = " + name + ", gender = " + gender + "}";
    }
}

class SuperClass { // super class does not implement Serializable
    private int    id;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

class SubClass extends SuperClass implements Serializable, ObjectInputValidation {
    private static final long serialVersionUID = -1322322139926390329L;

    private String            name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubClass{id = " + getId() + ", value = " + getValue() + ", name = " + getName()
               + "}";
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();

        // write fields declared in superclass
        oos.writeInt(getId());
        oos.writeObject(getValue());
    }

    // notice the order of read and write should be same
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        // register an ObjectInputValidation object, validations are called after the entire graph has been reconstituted.
        ois.registerValidation(this, 0);

        ois.defaultReadObject();

        setId(ois.readInt());
        setValue((String) ois.readObject());
    }

    @Override
    public void validateObject() throws InvalidObjectException {
        // validate the object here
        if (name == null || name.isEmpty()) {
            throw new InvalidObjectException("name can't be null or empty");
        }
        if (getId() <= 0) {
            throw new InvalidObjectException("ID can't be negative or zero");
        }
    }
}

class Data implements Serializable {
    private static final long serialVersionUID = 2087368867376448459L;

    private String            data;

    public Data(String d) {
        this.data = d;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{data = " + data + "}";
    }

    // serialization proxy class
    private static class SerializationProxy implements Serializable {
        private static final long   serialVersionUID = 8333905273185436744L;

        private static final String PREFIX           = "ABC";
        private static final String SUFFIX           = "DEFG";
        private String              serialData;

        public SerializationProxy(Data d) {
            //obscuring data for security
            this.serialData = PREFIX + d.data + SUFFIX;
        }

        private Object readResolve() throws InvalidObjectException {
            if (serialData.startsWith(PREFIX) && serialData.endsWith(SUFFIX)) {
                return new Data(serialData.substring(3, serialData.length() - 4));
            } else {
                throw new InvalidObjectException("Data corrupted");
            }
        }
    }

    // replacing serialized object with Data.SerializationProxy object
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream ois) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
}
*/