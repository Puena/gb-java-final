package java;

public class Pet extends Animal
{
    public Pet(String name, String dateOfBirth, String commands)
    {
        super(name, dateOfBirth, commands);
    }
    @Override
    public String toString() {
        return "My name is " + name + ". please me!";
    }

}