package pro.sky.Coursework_2.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.Coursework_2.model.Question;
import pro.sky.Coursework_2.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{amount}")
    public Collection<Question> getQuestions(@PathVariable("amount")int amount) {
        return examinerService.getQuestions(amount);
    }
}
