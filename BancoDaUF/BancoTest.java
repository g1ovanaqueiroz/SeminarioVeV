import java.util.ArrayList;
import java.util.List;

import easyaccept.src.easyaccept.EasyAcceptFacade;
import easyaccept.src.easyaccept.EasyAccept;
import main.Fachada;

public class BancoTest {

	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();

		files.add("TestScript1.txt");
		Fachada fachada = new Fachada();

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(fachada, files);

		// Execute the tests

		eaFacade.executeTests();

		// Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());

	}

}