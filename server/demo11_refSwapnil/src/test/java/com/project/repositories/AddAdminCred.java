package com.project.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.project.pojos.Credentials;
import com.project.pojos.Role;
import com.project.repository.CredentialRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class AddAdminCred {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private CredentialRepository credRepo;
	
	@Test
	void test() {
		assertNotNull(credRepo);
	}

	@Test
	void testAddUserCredential() {
		Credentials cred = new Credentials("sgadave",encoder.encode( "swapnil123"), Role.valueOf("ROLE_ADMIN"));
		System.out.println(cred);
		credRepo.save(cred);
		assertEquals(1, 1);
	}

}
