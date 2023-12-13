package bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int no;

//    transient关键字表示游离的，不参与序列化。
    private transient String name;      // name不参与序列化操作

    public User() {
    }

    public User(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return no == user.no && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, name);
    }
}
