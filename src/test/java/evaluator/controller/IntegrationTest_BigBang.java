package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.repository.IntrebariRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;

import static org.junit.Assert.assertEquals;

public class IntegrationTest_BigBang {
    private IntrebariRepository intrebariRepository;
    private AppController appController;
    private String fileName = "test.txt";

    @Before
    public void init() throws Exception {
        intrebariRepository = new IntrebariRepository();
        BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
        br.write("");
        br.close();
        appController = new AppController();
    }

    @Test
    public void unitTest_F01() throws DuplicateIntrebareException, InputValidationFailedException {
        // add Intrebare
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        assertEquals(2, intrebariRepository.getIntrebari().size());
    }

    @Test
    public void unitTest_F02() throws DuplicateIntrebareException, InputValidationFailedException {
        // create Test
        // init: 5î + 5d
        intrebariRepository.addIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        intrebariRepository.addIntrebare(new Intrebare("Enunt 5?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Chimie"), fileName);
        assertEquals(5, intrebariRepository.getIntrebari().size());
        assertEquals(5, intrebariRepository.getNumberOfDistinctDomains());

        appController.loadIntrebariFromFile(fileName);
        // test
        try {
            final evaluator.model.Test test = appController.createNewTest();
            Assert.assertEquals(5, test.getIntrebari().size());
        } catch (NotAbleToCreateTestException e) {
            Assert.fail();
        }
    }

    @Test
    public void unitTest_F03() throws DuplicateIntrebareException, InputValidationFailedException {
        // get Statistica
        // populate the file with questions in order to do the statistics
        appController.addNewIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 5?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);

        // test
        try {
            final Statistica statistica = appController.getStatistica();
            Assert.assertNotNull(statistica);
        } catch (NotAbleToCreateStatisticsException e) {
            Assert.fail();
        }
    }

    @Test
    public void integrationTest_BigBang() throws DuplicateIntrebareException, InputValidationFailedException {
        // test Add Intrebare
        appController.addNewIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Romana"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Chimie"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 5?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 6?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Biologie"), fileName);
        assertEquals(6, appController.getIntrebari().size());


        appController.loadIntrebariFromFile(fileName);
        // test Create Test
        try {
            final evaluator.model.Test test = appController.createNewTest();
            Assert.assertEquals(5, test.getIntrebari().size());
        } catch (NotAbleToCreateTestException e) {
            Assert.fail();
        }

        // test Get Statistica
        try {
            final Statistica statistica = appController.getStatistica();
            Assert.assertNotNull(statistica);
            Assert.assertEquals(new Integer(1), statistica.getIntrebariDomenii().get("Romana"));
            Assert.assertEquals(new Integer(1), statistica.getIntrebariDomenii().get("Info"));
            Assert.assertEquals(new Integer(1), statistica.getIntrebariDomenii().get("Chimie"));
            Assert.assertEquals(new Integer(2), statistica.getIntrebariDomenii().get("Biologie"));

        } catch (NotAbleToCreateStatisticsException e) {
            Assert.fail();
        }
    }
}
