package fr.uvsq.pglp.dao.crud;

import java.time.LocalDateTime;

public class Logger implements LogInterface {

	public void write(String string) {
		System.out.println(LocalDateTime.now() + ": " + string);
	}

}
