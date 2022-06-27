/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model ;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnContaArchi"
    private Button btnContaArchi; // Value injected by FXMLLoader

    @FXML // fx:id="btnRicerca"
    private Button btnRicerca; // Value injected by FXMLLoader

    @FXML // fx:id="txtSoglia"
    private TextField txtSoglia; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doContaArchi(ActionEvent event) {
    	txtResult.appendText("\n\n\n");
    	String sog=txtSoglia.getText();
    	if(sog==null) {
    		txtResult.appendText("inserire un valore numerico nell'area di testo");
    		return;
    	}
    	try {
    		double soglia=Double.parseDouble(sog);
    		Boolean valido=this.model.verificaSoglia(soglia);
    		if(valido) {
    			txtResult.appendText("Il valore "+soglia+" è compreso nell'intervallo "+this.model.minimoGrafo()+"/"+this.model.massimoGrafo()+"\n");
    		}
    		else {
    			txtResult.appendText("Il valore "+soglia+" NON è compreso nell'intervallo "+this.model.minimoGrafo()+"-"+this.model.massimoGrafo()+"\n");
    		}
    		
    		txtResult.appendText("Soglia : "+soglia+" : Archi maggiori= "+this.model.contaArchiMaggiori(soglia)+" ; Archi Minori= "+this.model.contaArchiMinore(soglia));
    	}
    	catch(NumberFormatException e) {
    		throw new NumberFormatException("inserire valore numerico");
    	}
    }

    @FXML
    void doRicerca(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnContaArchi != null : "fx:id=\"btnContaArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSoglia != null : "fx:id=\"txtSoglia\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model ;
		this.model.creaGrafo();
		txtResult.appendText("GRAFO CREATO \n\n");
		txtResult.appendText("Numero vertici : "+this.model.nVertici()+"\n");
		txtResult.appendText("Numero archi : "+this.model.nArchi()+"\n");
		txtResult.appendText("Peso minimo : "+this.model.minimoGrafo()+"\n");
		txtResult.appendText("Peso massimo : "+this.model.massimoGrafo());
		
	}
}
