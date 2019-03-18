package evaluator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;

import evaluator.controller.AppController;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.model.Test;

//functionalitati
//F01.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//F02.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//F03.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

	private static final String file = "C:\\Work\\5-ProiectEvaluatorExamen\\5-ProiectEvaluatorExamen\\ProiectEvaluatorExamen\\src\\main\\java\\evaluator\\intrebari.txt";

	public static void main(String[] args) throws IOException {

		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

		AppController appController = new AppController();

		boolean activ = true;
		String optiune = null;

		Scanner scanner = new Scanner(System.in);

		while(activ){

			System.out.print("\n");
			System.out.print("1.Adauga intrebare\n");
			System.out.print("2.Creeaza test\n");
			System.out.print("3.Statistica\n");
			System.out.print("4.Exit\n");
			System.out.print("\n");

			optiune = console.readLine();

			switch(optiune){
				case "1" : {
				    appController.loadIntrebariFromFile(file);

					System.out.print("Enunt: ");
					String enunt = scanner.nextLine();
					System.out.print("Varianta 1: ");
					String varianta1 = scanner.nextLine();
					System.out.print("Varianta 2: ");
					String varianta2 = scanner.nextLine();
					System.out.print("Varianta 3: ");
					String varianta3 = scanner.nextLine();
					System.out.print("Varianta corecta: ");
					String variantaCorecta = scanner.nextLine();
					System.out.print("Domeniu: ");
					String domeniu = scanner.nextLine();

					try {
						Intrebare intrebare = new Intrebare(enunt, varianta1, varianta2, varianta3, variantaCorecta, domeniu);
						appController.addNewIntrebare(intrebare, file);
					} catch (InputValidationFailedException | DuplicateIntrebareException e) {
						System.out.println(e.getMessage());
					}

					break;
                }
				case "2" : {
                    appController.loadIntrebariFromFile(file);

					try {
						Test test = appController.createNewTest();
						System.out.println("TEST");
						for (Intrebare intrebare : test.getIntrebari()) {
	                        System.out.println(intrebare.getEnunt() + " " + intrebare.getVarianta1() + " " + intrebare.getVarianta2() + " " + intrebare.getVarianta3());
						}

					} catch (NotAbleToCreateTestException e) {
						System.out.println(e.getMessage());
					}

					break;
				}
				case "3" : {
                    appController.loadIntrebariFromFile(file);
                    Statistica statistica;
                    try {
                        statistica = appController.getStatistica();
                        System.out.println(statistica);
                    } catch (NotAbleToCreateStatisticsException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }
				case "4" :
					activ = false;
					break;
				default:
					break;
			}
		}

	}

}
