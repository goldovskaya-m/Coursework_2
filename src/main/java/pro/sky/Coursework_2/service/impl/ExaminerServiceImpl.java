package pro.sky.Coursework_2.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.Coursework_2.exception.QuestionAmountMismatchException;
import pro.sky.Coursework_2.model.Question;
import pro.sky.Coursework_2.service.ExaminerService;
import pro.sky.Coursework_2.service.QuestionService;

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
        if (amount < 0 || amount > questionService.getAll().size()){
            throw new QuestionAmountMismatchException(amount);
    }
        Set<Question> resultList = new HashSet<>();
        while (resultList.size() < amount) {
            resultList.add(questionService.getRandomQuestion());
        }
        return resultList;
    }
}
