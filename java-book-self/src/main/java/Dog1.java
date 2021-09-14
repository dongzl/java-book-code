/**
 * @author dongzonglei
 * @description
 * @date 2019-09-13 15:53
 */
public class Dog1 {

    String name;

    Dog1(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getObjectAddress() {
        return super.toString();
    }
}
