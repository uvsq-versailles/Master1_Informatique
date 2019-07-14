package fr.uvsq.pglp.dao.crud;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Serializer<T extends Serializable> {
	
	public T writeFile(T obj, String filename, LogInterface log) {
		try (ObjectOutputStream	out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(filename)))) {
			out.writeObject(obj);
			log.write("Objet " + obj + " créé!");
			return obj;
		} catch (IOException e) {
			log.write(e.getMessage());
			return null;
		}
	}
	
	public void deleteFile(String filename, LogInterface log) {
		File file = new File(filename); 
        if(file.delete()) log.write("Objet supprimé!"); 
        else log.write("Suppression impossible");
	}
	
	private boolean exists(String filename) {
		File file = new File(filename); 
        return file.exists();
	}
	
	public T readFile(String filename, LogInterface log) {
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(filename)))) {
			T obj = (T) in.readObject();
			log.write("Objet " + obj + " lu!");
			return obj;
		} catch (ClassNotFoundException | IOException e) {
			log.write(e.getMessage());
			return null;
		}
	}
	
	public T createFile(T obj, String filename, LogInterface log) {
		if (exists(filename)) {
			log.write("Création impossible");
			return null;
		}
		else {
			writeFile(obj, filename, log);
			return obj;
		}
	}
	
	public T updateFile(T obj, String filename, LogInterface log) {
		if (!exists(filename)) {
			log.write("Mise à jour impossible");
			return null;
		}
		else {
			deleteFile(filename, log);
			writeFile(obj, filename, log);
			return obj;
		}
	}

}
