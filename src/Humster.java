package java;

public class Humster extends Pet{
    public Humster(String name, String dateOfBirth, String commands)
    {
        super(name, dateOfBirth, commands);
    }

    public void introduction() {
        System.out.print("I am the humster!");
    }
}