import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LibraryManagementTest {

	private Library library;
	private Transaction transaction;
	
	@Before
	public void setUp() {
		library = new Library();
		transaction = Transaction.getTransaction();
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
	
	@Test
	public void testBorrowReturn() {
		try {
			Book book = new Book(101, "Test Book");
			Member member = new Member(1, "Cole Beemer");
		
			library.addBook(book);
			library.addMember(member);
		
			assertTrue(book.isAvailable());
		
			boolean borrowSuccessful = transaction.borrowBook(book, member);
			assertTrue("The book should be borrowed", borrowSuccessful);
		
			assertFalse(book.isAvailable());
		
			boolean borrowAgainSuccessful = transaction.borrowBook(book, member);
			assertFalse("The book should not be available to borrow again", borrowAgainSuccessful);
		
			transaction.returnBook(book, member);
		
			assertTrue(book.isAvailable());
		
			boolean returnAgainSuccessful = transaction.returnBook(book, member);
			assertFalse("The book should not be available to return again", returnAgainSuccessful);
		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}
}
