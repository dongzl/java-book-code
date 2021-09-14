/**
 * @author dongzonglei
 * @description
 * @date 2019-09-13 15:54
 */
public class PassByValueExample {

    public static void main(String args[]) throws Exception {
        Dog1 dog = new Dog1("A");
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        func(dog);
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        System.out.println(dog.getName());          // A
    }

    private static void func(Dog1 dog) {
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        dog = new Dog1("B");
        System.out.println(dog.getObjectAddress()); // Dog@74a14482
        System.out.println(dog.getName());          // B
    }
}
