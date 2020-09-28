package nospringquiz;

import entity.Question;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class QuizController {
    Map<Question, Integer> answers = new HashMap<>();
    private final QuizService quizService;
    ListIterator<Question> questions;
    Question currentQuestion;

    public QuizController(QuizService quizService, long quizId) {
        this.quizService = quizService;
        questions = quizService.getQuiz(quizId).listIterator();
    }

    public Question next(){
        if(questions.hasNext()){
            currentQuestion = questions.next();
        }
        return currentQuestion;
    }
    public Question previous(){
        if(questions.hasPrevious()){
            currentQuestion = questions.previous();
        }
        return currentQuestion;
    }

    public void saveAnswer(Question question, int answer){
        answers.put(question, answer);
    }

    public int summary(){
       return answers.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getValidOption() == entry.getValue())
                .mapToInt(entry -> entry.getKey().getPoints())
                .sum();
    }
}
