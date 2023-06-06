package java;

public class AnimalPack extends Animal
{
    public AnimalPack(String name, String dateOfBirth, String commands)
    {
        super(name, dateOfBirth, commands);
    }

    @Override
    public String toString() {
        return "My name is " + name + ". Let's go hard weight!" ;
    }

}