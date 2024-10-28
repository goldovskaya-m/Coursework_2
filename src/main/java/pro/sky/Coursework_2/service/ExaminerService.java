package pro.sky.Coursework_2.service;

import pro.sky.Coursework_2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question>getQuestions(int amount);
}
