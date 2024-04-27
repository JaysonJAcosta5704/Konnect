package test.java.myapp;

import onetomany.Users.User;
import onetomany.Users.UserRepository;
import onetomany.hobbies.Hobbies;
import onetomany.hobbies.HobbiesRepository;
import onetomany.hobbies.HobbyType;
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
	@Mock
	private HobbiesRepository hobbiesRepository;

	private User user;
	private User user2;
	private Hobbies hob;
	@BeforeEach
	public void setUp() {
		user = new User("Test User", "testuser@example.com", "password123", "testuser", new Date(), 25, "Male");
		user2 = new User("Test User2", "testuser2@example.com", "password123", "testuser2", new Date(), 25, "Male");
		hob= new Hobbies("Soccer", HobbyType.GROUP);

	}

	@Test
	public void testGetName() {
		when(userRepository.findByUsername("testuser")).thenReturn(user);
		User returnedUser = userRepository.findByUsername("testuser");
		assertEquals("Test User", returnedUser.getName());
	}

	@Test
	public void testGetHobbie() {
		when(userRepository.findByUsername("testuser")).thenReturn(user);
		when(hobbiesRepository.findByName("Soccer")).thenReturn(hob);
		user.addHobbie(hobbiesRepository.findByName("Soccer"));
		hob.addUser(user);
		User returnedUser = userRepository.findByUsername("testuser");
		assertEquals(true, returnedUser.getHobbies().contains(hob));
	}


	@Test
	public void testRemoveHobbie() {
		when(userRepository.findByUsername("testuser")).thenReturn(user);
		when(hobbiesRepository.findByName("Soccer")).thenReturn(hob);
		user.addHobbie(hobbiesRepository.findByName("Soccer"));
		hob.addUser(user);
		User returnedUser = userRepository.findByUsername("testuser");
		assertEquals(true, returnedUser.getHobbies().contains(hob));

		hobbiesRepository.findByName("Soccer").removeUser(user);
		user.removeHobbie(hob);
		assertEquals(false, returnedUser.getHobbies().contains(hob));
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
