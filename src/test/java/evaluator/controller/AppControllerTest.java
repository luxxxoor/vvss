package evaluator.controller;

import evaluator.exception.NotAbleToCreateTestException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AppControllerTest {

    private AppController appController;
    private static final String fisier4 = "C:\\Work\\5-ProiectEvaluatorExamen\\5-ProiectEvaluatorExamen\\ProiectEvaluatorExamen\\src\\main\\java\\evaluator\\intrebari4.txt";
    private static final String fisier4Domenii = "C:\\Work\\5-ProiectEvaluatorExamen\\5-ProiectEvaluatorExamen\\ProiectEvaluatorExamen\\src\\main\\java\\evaluator\\intrebari4domenii.txt";
    private static final String fisier = "C:\\Work\\5-ProiectEvaluatorExamen\\5-ProiectEvaluatorExamen\\ProiectEvaluatorExamen\\src\\main\\java\\evaluator\\intrebari.txt";

    @Before
    public void init() {
        appController = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void verificaNrIntrebari() {

        appController.loadIntrebariFromFile(fisier4);
        try {
            appController.createNewTest();
        } catch (NotAbleToCreateTestException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void verificaNrDomenii() {

        appController.loadIntrebariFromFile(fisier4Domenii);
        try {
            appController.createNewTest();
        } catch (NotAbleToCreateTestException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void creeazaTest() {

        appController.loadIntrebariFromFile(fisier);
        try {
            appController.createNewTest();
        } catch (NotAbleToCreateTestException e) {
            System.out.println(e.getMessage());
        }
    }
}