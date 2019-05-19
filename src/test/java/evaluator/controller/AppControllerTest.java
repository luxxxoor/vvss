package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import evaluator.repository.IntrebariRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AppControllerTest {
    private IntrebariRepository intrebariRepository;
    private AppController appController;
    private static final String fisier4 = "intrebari4.txt";
    private static final String fisier4Domenii = "intrebari4domenii.txt";
    private static final String fileName = "intrebari.txt";

    @Before
    public void init() throws IOException, InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository = new IntrebariRepository();
        BufferedWriter br = new BufferedWriter(new FileWriter(fisier4));
        br.write("");
        br.close();

        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fisier4);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fisier4);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fisier4);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fisier4);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        intrebariRepository = new IntrebariRepository();
        br = new BufferedWriter(new FileWriter(fileName));
        br.write("");
        br.close();
        appController = new AppController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void verificaNrIntrebari() throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(4, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

        appController.loadIntrebariFromFile(fisier4);
        try {
            appController.createNewTest();
            Assert.fail("Operation with invalid inputs should not pass");
        } catch (Exception ignored) {
        }

        Assert.assertEquals("Invalid inpud data. Expected 4 \"intrebari\"", 4, appController.getIntrebari().size());
    }

    @Test
    public void verificaNrDomenii() throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 5?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(5, intrebariRepository.getIntrebari().size());
        assertEquals(4, intrebariRepository.getNumberOfDistinctDomains());

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
    public void creeazaTest() throws InputValidationFailedException, DuplicateIntrebareException {
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 5?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Chimie"), fileName);
        assertEquals(5, intrebariRepository.getIntrebari().size());
        assertEquals(5, intrebariRepository.getNumberOfDistinctDomains());


        appController.loadIntrebariFromFile(fileName);
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
}