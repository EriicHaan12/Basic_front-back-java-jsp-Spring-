package weberichan;

public class Student {
    private String name;
    private int id;
    private int age;
    private int score;

    public Student(String name, int id, int age, int score) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
