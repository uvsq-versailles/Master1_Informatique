package fr.uvsq.pglp.solid.dip;

import java.time.LocalDateTime;

public class ClasseMetier {
	public void uneMethodeMetier(LogInterface log) {
		log.write(LocalDateTime.now() + "Debut methode métier");
		// traitement métier
		log.write(LocalDateTime.now() + "Fin methode métier");
	}
}
