package controller;

public @interface info {
	/*
	 * Login -> LoginServlet esegue il login di user e admin
	 * 		L' user viene indirizzato su utente.jsp
	 * 		L'admin viene indirizzato su admin.jsp
	 * 
	 * Registrazione -> redirect su registrazioneServlet
	 *
	 * USER (utente.jsp)
	 * Nuovo noleggio -> NuovoNoleggioServlet" method="GET"		nuovoNoleggio.jsp
	 * 			visualizza categorie disponibili -> RedirectNoleggioServlet" method="GET"   ->  categoriePerNoleggio.jsp
     s* 					visualizza auto -> RedirectNoleggioServlet" method="POST"  ->  listAutoPerNoleggio.jsp
     * 										noleggia -> "EseguiNoleggioServlet" method="POST"  -> iTuoiNoleggi.jsp
	 * 
	 * I tuoi noleggi -> ITuoiNoleggiServlet" method="GET"	-> iTuoiNoleggi.jsp	 
	 * 				AnnullaPrenotazione -> EseguiNoleggioServlet" method="GET">
	 * 
	 * 
	 * Profile Settings -> "NuovoNoleggioServlet" method="POST"    -> utente.jsp"
	 * 							logout -> LoginServlet" method="GET">
	 * 
	 * 		
	 * 
	 * ADMIN
	 * 
	 * Categorie -> categorieServlet GET ->  categorie.jsp
	 * 			
	 * 				visualizzaAuto -> AutomobileServlet" method="GET  -> visualizzaAuto.jsp
	 * 								
	 * 								ADD -> AutomobileServlet" method="POST"  -> visualizzaAuto.jsp
	 * 								Cambia -> modificaPrenotabileServlet" method="POST"   -> visualizzaAuto.jsp
	 * 								modificaa -> ModificaAutomobileServlet" method="GET" -> modificaAuto.jsp
	 *  										modifica-> "ModificaAutomobileServlet" method="POST ->  visualizzaAuto.jsp
	 *  										indietro -> modificaPrenotabileServlet" method="GET" -> visualizzaAuto.jsp
	 * AggiungiCategorie -> categorieServlet POST -> aggiungiCategoria.jsp  
	 *
	 *					aggiungi -> "AggiungiCategoriaServlet" method="POST"> ->categorie.jsp
	 *
	 *
	 */

}
