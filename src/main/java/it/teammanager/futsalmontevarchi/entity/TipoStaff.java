package it.teammanager.futsalmontevarchi.entity;

public enum TipoStaff {
	ALLENATORE("Allenatore"),
	ALLENATORE_IN_SECONDA("Allenatore in seconda"),
	DIRIGENTE_ACCOMPAGNATORE("Dirigente accompagnatore"),
	DIRIGENTE_ARBITRO("Dirigente addetto arbitro"),
	MASSAGGIATORE("Massaggiatore"),;

	private final String descrizione;

	TipoStaff(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}
}
