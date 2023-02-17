package weberichan;

import java.util.Comparator;
import java.util.TreeSet;

public class List {
    private TreeSet<Student> students;

    public List() {
        // score에 대해 오름차순으로 정렬하는 TreeSet 생성
        this.students = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getScore() < o2.getScore()) {
                    return -1;
                } else if (o1.getScore() == o2.getScore()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    public void add(Student student) {
        students.add(student);
    }

    public void print() {
        // Student 객체 출력
        for (Student student : students) {
            System.out.println(student.getId() + " " + student.getName() + " " + student.getAge() + " " + student.getScore());
        }
    }
}
