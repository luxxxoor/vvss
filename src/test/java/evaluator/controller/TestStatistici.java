package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestStatistici {
    final AppController appController = new AppController();
    private String fileName = "test.txt";

    @Before
    public void init() throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
        br.write("");
        br.close();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void unitTestValid() throws DuplicateIntrebareException, InputValidationFailedException {
        // populate the file with questions in order to do the statistics
        appController.addNewIntrebare(new Intrebare("Enunt 1?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 2?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Mate"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 3?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 4?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Info"), fileName);
        appController.addNewIntrebare(new Intrebare("Enunt 5?", "1) Varianta 1", "2) Varianta 2", "3) Varianta 3", "1", "Chimie"), fileName);

        // test
        try {
            final Statistica statistica = appController.getStatistica();
            Assert.assertNotNull(statistica);
            Assert.assertEquals(new Integer(2), statistica.getIntrebariDomenii().get("Mate"));
            Assert.assertEquals(new Integer(2), statistica.getIntrebariDomenii().get("Info"));
            Assert.assertEquals(new Integer(1), statistica.getIntrebariDomenii().get("Chimie"));

        } catch (NotAbleToCreateStatisticsException e) {
            Assert.fail();
        }
    }

    @Test
    public void unitTestInvalid() throws NotAbleToCreateStatisticsException {
        expectedException.expect(NotAbleToCreateStatisticsException.class);
        appController.getStatistica(); // throw exception
        Assert.fail();
    }
}
