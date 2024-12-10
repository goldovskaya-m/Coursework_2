package pro.sky.Coursework_2.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.Coursework_2.exception.QuestionAmountMismatchException;
import pro.sky.Coursework_2.model.Question;
import pro.sky.Coursework_2.service.ExaminerService;
import pro.sky.Coursework_2.service.QuestionService;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        long start = System.currentTimeMillis();
        Collection<Question> allQuestions = questionService.getAll();
        if (amount < 0 || amount > allQuestions.size()) {
            throw new QuestionAmountMismatchException(amount);
        }
        if (amount == allQuestions.size()) {
            return allQuestions;
        }
            Set<Question> resultSet = new HashSet<>();
            while (resultSet.size() < amount) {
                resultSet.add(questionService.getRandomQuestion());
            }
        System.out.println(System.currentTimeMillis() - start);
            return resultSet;
        }

    }

