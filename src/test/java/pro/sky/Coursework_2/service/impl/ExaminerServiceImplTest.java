package pro.sky.Coursework_2.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Coursework_2.exception.QuestionAmountMismatchException;
import pro.sky.Coursework_2.model.Question;

import java.util.Collection;


import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static pro.sky.Coursework_2.TestData.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void setUp() {
        when(javaQuestionService.getAll()).thenReturn(getAll());
    }

    @AfterEach
    public void setDown() {
        verify(javaQuestionService, times(1)).getAll();
    }

    @Test
    @DisplayName("Возвращает коллекцию рандомных вопросов")
    void getQuestions() {
        int amount = 2;
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                RANDOM_QUESTION_1,
                RANDOM_QUESTION_2,
                RANDOM_QUESTION_3
        );

        //test
        Collection<Question> actual = examinerService.getQuestions((amount));

        //check
        assertThat(getAll()).containsAnyElementsOf(actual);

        verify(javaQuestionService, times(1)).getAll();
        verify(javaQuestionService, times(amount))
                .getRandomQuestion();
    }

    @Test
    @DisplayName("Возвращает все 3 вопроса, не вызывает сервис повторно")
    void getQuestions_1() {
        int amount = 3;

        //test
        Collection<Question> actual = examinerService.getQuestions(amount);
        //check
        assertThat(getAll()).containsAnyElementsOf(actual);
        verify(javaQuestionService, times(1)).getAll();

        verify(javaQuestionService, never()).getRandomQuestion();
    }


    @Test
    @DisplayName("тест на ошибку Возвращает все вопросы не вызывает сервис повторно")
    void getQuestions_2() {
        int amount = nextInt(4, 10);
        //test
        assertThatExceptionOfType(QuestionAmountMismatchException.class)
                .isThrownBy(() -> examinerService.getQuestions(nextInt()));
    }

}


