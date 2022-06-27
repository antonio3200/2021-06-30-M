package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Arco;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	

	public List<Integer> getCromosomi(){
		String sql="SELECT DISTINCT g.Chromosome AS c "
				+ "FROM genes g "
				+ "WHERE g.Chromosome!=0";
		List<Integer> result= new ArrayList<>();
		Connection conn=DBConnect.getConnection();
		try {
			PreparedStatement st= conn.prepareStatement(sql);
			ResultSet rs= st.executeQuery();
			while(rs.next()) {
				Integer i=rs.getInt("c");
				result.add(i);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("SQL ERROR");
		}
		return result;
	}

	
	public List<Arco> getArchi(List<Integer> vertici){
		String sql="SELECT g1.Chromosome AS c1, g2.Chromosome AS c2, SUM(Distinct i1.Expression_Corr) AS peso "
				+ "FROM genes g1, genes g2, interactions i1 "
				+ "WHERE g1.Chromosome<>g2.Chromosome "
				+ "AND g1.GeneID=i1.GeneID1 "
				+ "AND g2.GeneID=i1.GeneID2 "
				+ "AND g1.Chromosome!='0' "
				+ "AND g2.Chromosome!='0' "
				+ "GROUP BY c1,c2 "
				+ "ORDER BY c1,c2";
		List<Arco> result= new ArrayList<>();
		Connection conn=DBConnect.getConnection();
		try {
			PreparedStatement st= conn.prepareStatement(sql);
			ResultSet rs= st.executeQuery();
			while(rs.next()) {
				Integer i1= rs.getInt("c1");
				Integer i2=rs.getInt("c2");
				if(vertici.contains(i1) && vertici.contains(i2)) {
					double peso=rs.getDouble("peso");
					Arco a = new Arco(i1,i2,peso);
					result.add(a);
				}
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("SQL ERROR");
		}
		return result;
	}
	
}
