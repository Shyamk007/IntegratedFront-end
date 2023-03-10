package com.project.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.project.pojos.Admin;
import com.project.pojos.Credentials;
import com.project.repository.AdminRepository;
import com.project.repository.CredentialRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class AddAdminDetails {

	
	@Autowired
	private AdminRepository adRepo;
	
	@Autowired
	private CredentialRepository credRepo;
	
	@Test
	void test() {
		assertNotNull(credRepo);
	}

	@Test
	void testAddAdminDetails() {

		Credentials cred = credRepo.findByUserName("admin").get();
		
		Admin admin =new Admin("Rushikesh", "Pise", "rpise4938@gmail.com");
		cred.setAdminId(admin);
		adRepo.save(admin);
	}

}
