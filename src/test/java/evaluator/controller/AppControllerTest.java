package evaluator.controller;

import org.junit.Assert;
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
            Assert.fail("Operation with invalid inputs should not pass");
        } catch (Exception ignored) {
        }

        Assert.assertEquals("Invalid inpud data. Expected 4 \"intrebari\"", appController.getIntrebari().size(), 4);
    }

    @Test
    public void verificaNrDomenii() {

        appController.loadIntrebariFromFile(fisier4Domenii);
        try {
            appController.createNewTest();
            Assert.fail("Operation with invalid inputs should not pass");
        } catch (Exception ignored) {
        }

        Assert.assertEquals("Expected 5 \"intrebari\"", appController.getIntrebari().size(), 5);
        try {
            Assert.assertEquals("Expected 4 \"domenii\"", appController.getStatistica().getIntrebariDomenii().size(), 4);
        } catch (Exception ignored) {
            Assert.fail("Invalid. Could not create statistics");
        }
    }

    @Test
    public void creeazaTest() {

        appController.loadIntrebariFromFile(fisier);
        try {
            appController.createNewTest();
        } catch (Exception e) {
            Assert.fail("Operation with valid inputs should always pass");
        }

        Assert.assertEquals("Expected 5 \"intrebari\"", appController.getIntrebari().size(), 5);
        try {
            Assert.assertEquals("Expected 5 \"domenii\"", appController.getStatistica().getIntrebariDomenii().size(), 5);
        } catch (Exception ignored) {
            Assert.fail("Invalid. Could not create statistics");
        }
    }

    @Test
}