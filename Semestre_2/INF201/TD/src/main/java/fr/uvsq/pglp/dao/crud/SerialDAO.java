package fr.uvsq.pglp.dao.crud;

import java.io.Serializable;

public abstract class SerialDAO<T extends Serializable>	extends Serializer<T> 
													implements DAO<T> {
	
	private Logger log = new Logger();
	
	public abstract String getFilename(String id);	
	public abstract String getFilename(T obj);
	
	@Override
	public T create(T obj) {
		return createFile(obj, getFilename(obj), log);
	}

	@Override
	public T read(String id) {
		return readFile(getFilename(id), log);
	}

	@Override
	public T update(T obj) {
		return updateFile(obj, getFilename(obj), log);
	}

	@Override
	public void delete(T obj) {
		deleteFile(getFilename(obj), log);
	}
}
