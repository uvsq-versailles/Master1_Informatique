package fr.uvsq.pglp.kholle;

public abstract class Usine {

	public Option getOption() {
		return createOption();
	}

	protected abstract Option createOption();
}