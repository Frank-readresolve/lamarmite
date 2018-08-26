package fr.formation.lamarmite.components;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import fr.formation.lamarmite.entities.*;
import fr.formation.lamarmite.repositories.ICivilityJpaRepository;

@Component
public class UserMapper implements FieldSetMapper<User> {

    private final ICivilityJpaRepository civilityJpaRepository;

    @Autowired
    protected UserMapper(ICivilityJpaRepository civilityJpaRepository) {
	this.civilityJpaRepository = civilityJpaRepository;
    }

    @Override
    public User mapFieldSet(FieldSet fieldSet) {
	User user = new User();
	user.setLastname(fieldSet.readString("lastname"));
	user.setFirstname(fieldSet.readString("firstname"));
	user.setEmail(fieldSet.readString("email"));
	user.setPassword(encodePassword(fieldSet.readString("password")));
	user.setRole(User.Role.valueOf(fieldSet.readString("role")));
	String civilityCode = fieldSet.readString("civility_code");
	Civility civility = civilityJpaRepository.findByCode(civilityCode);
	user.setCivility(civility);
	return user;
    }

    private static String encodePassword(String rawPassword) {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	return encoder.encode(rawPassword);
    }
}
