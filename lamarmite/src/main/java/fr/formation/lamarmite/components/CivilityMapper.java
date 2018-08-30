package fr.formation.lamarmite.components;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import fr.formation.lamarmite.entities.Civility;

@Component
public class CivilityMapper implements FieldSetMapper<Civility> {

    @Override
    public Civility mapFieldSet(FieldSet fieldSet) {
	Civility civility = new Civility();
	civility.setAbbreviationEn(fieldSet.readString("abbreviationEn"));
	civility.setAbbreviationFr(fieldSet.readString("abbreviationFr"));
	civility.setCode(fieldSet.readString("code"));
	return civility;
    }
}
