import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LibraryManagementTest {

	private Library library;
	
	@Before
	public void setUp() {
		library = new Library();
	}
	
	@Test
	public void testValidBookIdBoundary() {
		try {
			Book book1 = new Book(100, "Valid Book 1");
			Book book2 = new Book(999, "Valid Book 2");
			assertNotNull(book1);
			assertNotNull(book2);
		} catch (Exception e) {
			fail("Exception should not be thrown for valid book IDs");
		}
	}

	@Test
	public void testInvalidBookIdGreaterThanMax() {
		try {
			Book book = new Book(1000, "Invalid Book 1");
			fail("Exception should have been thrown for invalid book ID.");
		} catch (Exception e) {
			assertEquals("Invalid book ID.", e.getMessage());
		}
	}
	
	@Test
	public void testInvalidBookIdLessThanMin() {
		try {
			Book book = new Book(99, "Invalid Book 2");
			fail("Exception should have been thrown for invalid book ID.");
		} catch (Exception e) {
			assertEquals("Invalid book ID.", e.getMessage());
		}
	}
	
	@Test
	public void testInvalidBookIdZero() {
		try {
			Book book = new Book(0, "Invalid Book 3");
			fail("Exception should have been thrown for invalid book ID.");
		} catch (Exception e) {
			assertEquals("Invalid book ID.", e.getMessage());
		}
	}
}
