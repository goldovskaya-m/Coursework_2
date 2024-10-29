package pro.sky.Coursework_2.service.impl;

import com.github.javafaker.Faker;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.Test;
import pro.sky.Coursework_2.exception.QuestionNotFoundException;
import pro.sky.Coursework_2.model.Question;

import java.util.Collection;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static pro.sky.Coursework_2.TestData.*;

public class JavaQuestionServiceTest {
    private final JavaQuestionService javaQuestionService = new JavaQuestionService();

    @Test
    void shouldAddQuestion_WhenCorrectQuestion_ThenAdd() {

        //test
        Question actual = javaQuestionService.add(RANDOM_QUESTION_1);

        //check
        Collection<Question> result = javaQuestionService.getAll();
        //assertThat(result).hasSize(1);
        //assertThat(result.iterator().next()).isEqualTo(RANDOM_QUESTION_1);
        assertThat(actual).isEqualTo(RANDOM_QUESTION_1);

    }

    @Test
    void shouldAddQuestion_WhenQuestionAlreadyAdded_ThenAdd() {
        //test && check
        Question actual = javaQuestionService.add(RANDOM_QUESTION_1);
    }

    @Test
    void shouldAddQuestion2_WhenQuestionAlreadyAdded_ThenThrowException() {
        javaQuestionService.add(RANDOM_QUESTION_1);
        //test && check
        assertThatExceptionOfType(QuestionNotFoundException.class).isThrownBy(() -> javaQuestionService.add(RANDOM_QUESTION_1));
    }

    @Test
    void shouldAddQuestion_WhenCorrectQuestionAlreadyAdded_ThenThrowException() {
        javaQuestionService.add(RANDOM_QUESTION_2);
        assertDoesNotThrow(() -> javaQuestionService.add(RANDOM_QUESTION_1));
    }

    @Test
    void shouldRemoveQuestion_WhenCorrectQuestion_ThenRemove() {
        javaQuestionService.add(RANDOM_QUESTION_1);
        //test
        Question actual = javaQuestionService.remove(RANDOM_QUESTION_1);
        //check
        Collection<Question> result = javaQuestionService.getAll();
        assertThat(result).isEmpty();

        assertThat(actual).isEqualTo(RANDOM_QUESTION_1);
    }


    @Test
    void shouldRemoveQuestion_WhenCorrectQuestionAndAnswer_ThenRemove() {
        //test
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> javaQuestionService.remove(RANDOM_QUESTION_1));
    }

    @Test
    void shouldReturnAllQuestions() {
        javaQuestionService.add(RANDOM_QUESTION_1);
        javaQuestionService.add(RANDOM_QUESTION_2);
        javaQuestionService.add(RANDOM_QUESTION_3);
        //test
        Collection<Question> actual = javaQuestionService.getAll();
        //check
        assertThat(actual).hasSize(3);
        assertThat(actual).containsExactlyInAnyOrderElementsOf((getAll()));
    }

    @Test
    void getReturnRandomQuestion() {
        javaQuestionService.add(RANDOM_QUESTION_1);
        javaQuestionService.add(RANDOM_QUESTION_2);
        javaQuestionService.add(RANDOM_QUESTION_3);
        Collection<Question> expected = getAll();

        //test
        Question actual = javaQuestionService.getRandomQuestion();
        //check
        assertThat(expected).contains(actual);

    }
}
