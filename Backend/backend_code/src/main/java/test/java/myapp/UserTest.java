package test.java.myapp;

import onetomany.Users.User;
import onetomany.Users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserTest {


	@Mock
	private UserRepository userRepository;

	private User user;

	@BeforeEach
	public void setUp() {
		user = new User("Test User", "testuser@example.com", "password123", "testuser", new Date(), 25, "Male");
	}

	@Test
	public void testGetName() {
		when(userRepository.findByUsername("testuser")).thenReturn(user);
		User returnedUser = userRepository.findByUsername("testuser");
		assertEquals("Test User", returnedUser.getName());
	}


	@Test
	public void testGetEmailId() {
		assertEquals("testuser@example.com", user.getEmailId());
	}

	@Test
	public void testGetUserPassword() {
		assertEquals("password123", user.getUserPassword());
	}

	@Test
	public void testGetUsername() {
		assertEquals("testuser", user.getUsername());
	}

	@Test
	public void testGetAge() {
		assertEquals(25, user.getAge());
	}

	@Test
	public void testGetGender() {
		assertEquals("Male", user.getGender());
	}
}
