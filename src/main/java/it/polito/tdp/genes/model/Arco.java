package it.polito.tdp.genes.model;

public class Arco {

	Integer i1;
	Integer i2;
	double peso;
	
	
	
	public Arco(Integer i1, Integer i2, double peso) {
		super();
		this.i1 = i1;
		this.i2 = i2;
		this.peso = peso;
	}
	public Integer getI1() {
		return i1;
	}
	public void setI1(Integer i1) {
		this.i1 = i1;
	}
	public Integer getI2() {
		return i2;
	}
	public void setI2(Integer i2) {
		this.i2 = i2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
	
}
