package main.fr.ubordeaux.domain.type;

public enum EnseigneId {
	INTERMARCHE(new Enseigne("Intermarché")),
	LECLERC(new Enseigne("Leclerc")),
	LIDLE(new Enseigne("Lidle")),
	AUCHAN(new Enseigne("Auchan"));


	private EnseigneId(Enseigne label) { this.label = label; }
	private Enseigne label;

	public Enseigne label() { return label; }

}
