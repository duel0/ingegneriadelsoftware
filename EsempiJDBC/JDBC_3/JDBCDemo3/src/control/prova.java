package control;



import entity.EntityStudente;

public class prova {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EntityStudente s = new EntityStudente("M6300001");
				
		System.out.println(s);
		
		System.out.println(s.getBadge().get(0).getStudente().getCognome());
		
		EntityStudente s1 = new EntityStudente();
		s1.setNome("prova");
		s1.setCognome("prova");
		s1.setDataNascita("01/01/99");
		
		s1.scriviSuDB("M630006");
		//System.out.println(s.getCorsi());
		

		
	}

}
