package evaluator.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;

import static org.junit.Assert.*;

public class TestIntrebare {

    private AppController appController;
    private static final String file = "C:\\Work\\5-ProiectEvaluatorExamen\\5-ProiectEvaluatorExamen\\ProiectEvaluatorExamen\\src\\main\\java\\evaluator\\intrebari.txt";

    @Before
    public void init() {
        appController = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void test1() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("Enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
        assertEquals(1, appController.getIntrebari().size());
    }

    @Test
    public void test2(){

        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            try {
                appController.addNewIntrebare(intrebare, file);
            } catch (DuplicateIntrebareException e) {
                System.out.println(e.getMessage());
            }
        } catch (InputValidationFailedException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(0, appController.getIntrebari().size());
    }

    @Test
    public void test3() {
        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("Enunt test?", "a) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            try {
                appController.addNewIntrebare(intrebare, file);
            } catch (DuplicateIntrebareException e) {
                System.out.println(e.getMessage());
            }
        } catch (InputValidationFailedException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(0, appController.getIntrebari().size());
    }

    @Test
    public void test4() {
        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("A", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            try {
                appController.addNewIntrebare(intrebare, file);
            } catch (DuplicateIntrebareException e) {
                System.out.println(e.getMessage());
            }
        } catch (InputValidationFailedException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(0, appController.getIntrebari().size());
    }

    @Test
    public void test5() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("A?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
        assertEquals(1, appController.getIntrebari().size());
    }
}