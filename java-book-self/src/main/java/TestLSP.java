/**
 * @Description
 * @Author dongzonglei
 * @Date 2021/3/11 8:31 下午
 */
public class TestLSP {

    public static void main(String[] args) throws Exception {
        Pet pet = new Dog();
        pet.speak();
        pet.speakTo("Java");

        Dog dog1 = new Dog();
        makePetSpeak(dog1);
    }

    private static void makePetSpeak(Pet pet) {
        pet.speak();
        System.out.println("\n Pet speak.");
    }
}

class Pet {

    public void speak() {
        System.out.println("...");
    }

    public void speakTo(String name) {
        this.speak();
        System.out.println(name);
    }
}

class Dog extends Pet {

    @Override
    public void speak() {
        System.out.println("Wang!");
    }
}