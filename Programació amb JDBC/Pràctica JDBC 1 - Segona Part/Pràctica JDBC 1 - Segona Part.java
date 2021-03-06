/*
PASSOS PREVIS: LLegir el contingut del fitxer PassosASeguir.txt (dins del zip del fitxer adjunt) 

EXERCICI:
Heu d'implementar el mètode Consulta de la classe CtrlDdadesPublic. Aquest mètode ha de: 
- Retornar una fila per cada dni de professor obtingut a partir dels paràmetres d'entrada. En cada fila hi ha d'haver 
el dni d'un professor i la quantitat d'assignacions del professor.

Cal tenir en compte que: 
- Els dni de professors en els paràmetres d'entrada s'hauran acabat quan es trobi el dni -999.
- Cal que tingueu en compte que en Java per comparar strings s'ha d'usar els mètodes equals o compareTo de la classe String. 

En cas que ocorri algun error, el mètode ha de llançar una excepció identificada amb el codi d'error que s'indica.
11: Error intern

Pel joc de proves públic el resultat que s'obtindrà és el següent:
111	1

En el fitxer adjunt trobareu: 
- Els passos a seguir: (PassosASeguir.txt)
- Les classes i mètodes per obtenir els paràmetres d'entrada: (MetodesAuxiliars.txt) 
- Les classes i mètodes per retornar el resultat i llençar excepcions: (MetodesAuxiliars.txt) 
- El projecte Eclipse que cal estendre. 
*/

//Solución:

/* Imports de la classe */
import java.sql.*; 

/* Capa de Control de Dades */
class CtrlDadesPublic extends CtrlDadesPrivat {
	
	public ConjuntTuples consulta(Connection c, Tuple params) throws BDException {
        try {
        ConjuntTuples ct = new ConjuntTuples();
        PreparedStatement ps = c.prepareStatement("select count(*) as nombreA from assignacions " + 
        " where dni = ?");
        int i = 1;
        String s = params.consulta(i);
        while(!s.equals("-999")) {
            Tuple fila = new Tuple();
            ps.setString(1, s);
            ResultSet num = ps.executeQuery();
            num.next();
            int numAssig = num.getInt("nombreA");
            String num_a = String.valueOf(numAssig);
            fila.afegir(s);
            fila.afegir(num_a);
            ct.afegir(fila);
            ++i;
            s = params.consulta(i);
            }
        return ct;
        }
        catch(SQLException se) {
            throw new BDException(11);
        }
    }
}
