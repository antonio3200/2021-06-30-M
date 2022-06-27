package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {

	private GenesDao dao;
	private List<Integer> vertici;
	private SimpleDirectedWeightedGraph<Integer,DefaultWeightedEdge> grafo;
	
	public Model() {
		this.dao= new GenesDao();
		this.vertici= new ArrayList<>();
	}
	
	public void creaGrafo() {
		this.grafo= new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		this.vertici=this.dao.getCromosomi();
		//aggiungo vertici
		Graphs.addAllVertices(this.grafo, this.vertici);
		//aggiungo archi
		List<Arco> archi= this.dao.getArchi(vertici);
		for(Arco a : archi) {
			Graphs.addEdgeWithVertices(this.grafo, a.getI1(), a.getI2(), a.getPeso());
		}
	}
	
	public List<Integer> getVertici(){
		return this.vertici;
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public double minimoGrafo() {
		double minimo=Double.MAX_VALUE;
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)<minimo) {
				minimo=this.grafo.getEdgeWeight(e);
			}
		}
		return minimo;
	}
	
	
	public double massimoGrafo() {
		double massimo=Double.MIN_VALUE;
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)>massimo) {
				massimo=this.grafo.getEdgeWeight(e);
			}
		}
		return massimo;
	}
	
	public boolean verificaSoglia(double soglia) {
		if(soglia<=this.massimoGrafo() && soglia>=this.minimoGrafo()) {
			return true;
		}
			return false;
	}
	
	public int contaArchiMaggiori(double soglia) {
		int contatore=0;
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)>soglia) {
				contatore++;
			}
		}
		return contatore;
	}
	
	public int contaArchiMinore(double soglia) {
		int contatore=0;
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			if(this.grafo.getEdgeWeight(e)<soglia) {
				contatore++;
			}
		}
		return contatore;
	}
	
}