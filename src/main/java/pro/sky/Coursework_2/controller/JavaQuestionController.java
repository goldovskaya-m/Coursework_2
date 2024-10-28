package pro.sky.Coursework_2.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.Coursework_2.model.Question;
import pro.sky.Coursework_2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question add(@RequestParam("question") String question,
                        @RequestParam("answer") String answer) {
        System.out.println(1);
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam("question") String question,
                          @RequestParam("answer") String answer) {
       return questionService.remove(question, answer);
   }

    @GetMapping
    public Collection<Question> getAll() {
        return questionService.getAll();
    }
}
