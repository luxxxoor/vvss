package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

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
    public void test_valid_BVA() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("Enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
    }

    @Test
    public void test_invalid_BVA() {

        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            try {
                appController.addNewIntrebare(intrebare, file);
            } catch (DuplicateIntrebareException e) {
                assertEquals("Prima litera din enunt nu e majuscula!", e.getMessage());
            }
        } catch (InputValidationFailedException e) {
            assertEquals("Prima litera din enunt nu e majuscula!", e.getMessage());
        }
    }

    @Test
    public void test_invalid2_BVA() {
        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("Enunt test?", "a) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            appController.addNewIntrebare(intrebare, file);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Varianta1 nu incepe cu '1)'!");
        }
    }

    @Test
    public void test_invalid3_BVA() {
        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("A", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            appController.addNewIntrebare(intrebare, file);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Ultimul caracter din enunt nu e '?'!");
        }

        assertEquals(0, appController.getIntrebari().size());
    }

    @Test
    public void test_valid2_BVA() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
        assertEquals(1, appController.getIntrebari().size());
    }

    @Test
    public void test_valid_ECP() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("Enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
        assertEquals(1, appController.getIntrebari().size());
    }

    @Test
    public void test_invalid_ECP() throws InputValidationFailedException {

        Intrebare intrebare = null;

        try {
            intrebare = new Intrebare("enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            appController.addNewIntrebare(intrebare, file);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Prima litera din enunt nu e majuscula!");
        }

        assertEquals(0, appController.getIntrebari().size());
    }

    @Test
    public void test_invalid2_ECP() {
        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("Enunt test?", "a) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            appController.addNewIntrebare(intrebare, file);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Varianta1 nu incepe cu '1)'!");
        }

        assertEquals(0, appController.getIntrebari().size());
    }


    @Test
    public void test_invalid3_ECP() {
        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("A", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            appController.addNewIntrebare(intrebare, file);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Ultimul caracter din enunt nu e '?'!");
        }

        assertEquals(0, appController.getIntrebari().size());
    }

    @Test
    public void test_valid2_ECP() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("A?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, file);
        assertEquals(1, appController.getIntrebari().size());
    }
}