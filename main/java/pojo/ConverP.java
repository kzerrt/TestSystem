package pojo;

/**
 * @auther:Florence
 * @date:2022/04/28/21:48
 */
public class ConverP {
    private int id;
    private String word;
    private String name;

    @Override
    public String toString() {
        return "ConverP{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
