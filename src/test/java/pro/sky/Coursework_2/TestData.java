package pro.sky.Coursework_2;

import pro.sky.Coursework_2.model.Question;

import java.util.Collection;
import java.util.List;

public class TestData {
    public final static Question RANDOM_QUESTION_1 = new Question("Spring has DI", "YES");

    public final static Question RANDOM_QUESTION_2 = new Question("Spring has Ioc", "YES");

    public final static Question RANDOM_QUESTION_3 = new Question("Spring has ACID", "NO");

    public static Collection<Question> getAll() {
        return List.of(RANDOM_QUESTION_1, RANDOM_QUESTION_2, RANDOM_QUESTION_3);
    }
}

