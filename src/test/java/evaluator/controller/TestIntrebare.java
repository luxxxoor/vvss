package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import evaluator.repository.IntrebariRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestIntrebare {
    private IntrebariRepository intrebariRepository;
    private AppController appController;
    private static final String fileName = "intrebari.txt";

    @Before
    public void init() throws IOException {
        intrebariRepository = new IntrebariRepository();
        BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
        br.write("");
        br.close();
        appController = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void test_valid_BVA()  throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        Intrebare intrebare = new Intrebare("Enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, fileName);
    }

    @Test
    public void test_invalid_BVA()  throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            try {
                appController.addNewIntrebare(intrebare, fileName);
            } catch (DuplicateIntrebareException e) {
                assertEquals("Prima litera din enunt nu e majuscula!", e.getMessage());
            }
        } catch (InputValidationFailedException e) {
            assertEquals("Prima litera din enunt nu e majuscula!", e.getMessage());
        }
    }

    @Test
    public void test_invalid2_BVA()  throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("Enunt test?", "a) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            appController.addNewIntrebare(intrebare, fileName);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Varianta1 nu incepe cu '1)'!");
        }
    }

    @Test
    public void test_invalid3_BVA()  throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("A", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            appController.addNewIntrebare(intrebare, fileName);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Ultimul caracter din enunt nu e '?'!");
        }

        assertEquals(0, appController.getIntrebari().size());
    }

    @Test
    public void test_valid2_BVA()  throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        Intrebare intrebare = new Intrebare("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, fileName);
        assertEquals(1, appController.getIntrebari().size());
    }

    @Test
    public void test_valid_ECP()  throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        Intrebare intrebare = new Intrebare("Enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, fileName);
        assertEquals(1, appController.getIntrebari().size());
    }

    @Test
    public void test_invalid_ECP()  throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        Intrebare intrebare = null;

        try {
            intrebare = new Intrebare("enunt test?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            appController.addNewIntrebare(intrebare, fileName);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Prima litera din enunt nu e majuscula!");
        }

        assertEquals(0, appController.getIntrebari().size());
    }

    @Test
    public void test_invalid2_ECP() throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("Enunt test?", "a) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            appController.addNewIntrebare(intrebare, fileName);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Varianta1 nu incepe cu '1)'!");
        }

        assertEquals(0, appController.getIntrebari().size());
    }


    @Test
    public void test_invalid3_ECP() throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("A", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
            appController.addNewIntrebare(intrebare, fileName);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Ultimul caracter din enunt nu e '?'!");
        }

        assertEquals(0, appController.getIntrebari().size());
    }

    @Test
    public void test_valid2_ECP() throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        Intrebare intrebare = new Intrebare("A?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Domeniu");
        appController.addNewIntrebare(intrebare, fileName);
        assertEquals(1, appController.getIntrebari().size());
    }
}