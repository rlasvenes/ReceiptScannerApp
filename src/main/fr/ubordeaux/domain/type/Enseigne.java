package main.fr.ubordeaux.domain.type;

public class Enseigne {
	private final String labelEnseigne;
	
	public Enseigne(String labelEnseigne) {
		this.labelEnseigne = labelEnseigne;
	}
	
	public String label() {
		return labelEnseigne;
	}

	@Override
	public String toString() {
		return labelEnseigne;
	}
}
