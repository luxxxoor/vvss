package evaluator.repository;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import evaluator.model.Intrebare;
import evaluator.exception.DuplicateIntrebareException;

public class IntrebariRepository {

	private List<Intrebare> intrebari;

	public IntrebariRepository() {
		setIntrebari(new LinkedList<Intrebare>());
	}

	public void addIntrebare(Intrebare i, String file) throws DuplicateIntrebareException {
		if(exists(i))
			throw new DuplicateIntrebareException("Intrebarea deja exista!");
		intrebari.add(i);
		writeIntrebariInFile(i,file);
	}

	public boolean exists(Intrebare i){
		for(Intrebare intrebare : intrebari)
			if (intrebare.equals(i))
				return true;
		return false;
	}

	public Intrebare pickRandomIntrebare(){
		Random random = new Random();
		return intrebari.get(random.nextInt(intrebari.size()));
	}

	public int getNumberOfDistinctDomains(){
		return getDistinctDomains().size();

	}

	public Set<String> getDistinctDomains(){
		Set<String> domains = new TreeSet<String>();
		for(Intrebare intrebre : intrebari)
			domains.add(intrebre.getDomeniu());
		return domains;
	}

	public List<Intrebare> getIntrebariByDomain(String domain){
		List<Intrebare> intrebariByDomain = new LinkedList<Intrebare>();
		for(Intrebare intrebare : intrebari){
			if(intrebare.getDomeniu().equals(domain)){
				intrebariByDomain.add(intrebare);
			}
		}

		return intrebariByDomain;
	}

	public int getNumberOfIntrebariByDomain(String domain){
		return getIntrebariByDomain(domain).size();
	}

	public List<Intrebare> loadIntrebariFromFile(String f){

		List<Intrebare> intrebari = new LinkedList<Intrebare>();
		BufferedReader br = null;
		String line = null;
		List<String> intrebareAux;
		Intrebare intrebare;

		try{
			br = new BufferedReader(new FileReader(f));
			line = br.readLine();
			while(line != null){
				intrebareAux = new LinkedList<String>();
				while(!line.equals("##")){
					intrebareAux.add(line);
					line = br.readLine();
				}
				intrebare = new Intrebare();
				intrebare.setEnunt(intrebareAux.get(0));
				intrebare.setVarianta1(intrebareAux.get(1));
				intrebare.setVarianta2(intrebareAux.get(2));
				intrebare.setVarianta3(intrebareAux.get(3));
				intrebare.setVariantaCorecta(intrebareAux.get(4));
				intrebare.setDomeniu(intrebareAux.get(5));
				intrebari.add(intrebare);
				line = br.readLine();
			}

		}
		catch (IOException e) {
			System.out.println("Nu se poate face crearea!");
		}
//		finally{
//			try {
//				br.close();
//			} catch (IOException e) {
//				System.out.println("Nu merge");
//			}
//		}

		return intrebari;
	}

	public List<Intrebare> getIntrebari() {
		return intrebari;
	}

	public void setIntrebari(List<Intrebare> intrebari) {
		this.intrebari = intrebari;
	}

	public void writeIntrebariInFile(Intrebare intrebare, String f){

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f,true));
			bw.write("\n" + intrebare.getEnunt());
			bw.newLine();
			bw.write(intrebare.getVarianta1());
			bw.newLine();
			bw.write(intrebare.getVarianta2());
			bw.newLine();
			bw.write(intrebare.getVarianta3());
			bw.newLine();
			bw.write(intrebare.getVariantaCorecta());
			bw.newLine();
			bw.write(intrebare.getDomeniu());
			bw.newLine();
			bw.write("##");

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
