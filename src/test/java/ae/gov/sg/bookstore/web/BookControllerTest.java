/**
 * 
 */
package ae.gov.sg.bookstore.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.*;

/**
 * The Class BookControllerTest.
 *
 * @author mohamed elzaiady
 */
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

	/** The mock mvc. */
	@Autowired
	MockMvc mockMvc;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test method for {@link ae.gov.sg.bookstore.web.BookController#getAllBooks()}.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetAllBooks() throws Exception {
		mockMvc.perform(get("/api/books"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$[0]").isMap())
		.andExpect(notNull("$[0].name"))
		.andExpect(notNull("$[0].description"))
		.andExpect(notNull("$[0].author"))
		.andExpect(notNull("$[0].classification"))
		.andExpect(notNull("$[0].price"))
		.andExpect(notNull("$[0].isbn"))
		.andExpect(notNull("$[0].id"));

	}
	
	/**
	 * Test method for {@link ae.gov.sg.bookstore.web.BookController#read(java.lang.Integer)}.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testRead() throws Exception {
		mockMvc.perform(get("/api/books/2").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").isMap())
			.andExpect(notNull("$.name"))
			.andExpect(notNull("$.description"))
			.andExpect(notNull("$.author"))
			.andExpect(notNull("$.classification"))
			.andExpect(notNull("$.price"))
			.andExpect(notNull("$.isbn"))
			.andExpect(notNull("$.id"));
		
	}
	
	@Test
	void testReadNotFound() throws Exception {
		mockMvc.perform(get("/api/books/1000").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(notNull("$.errorCode"))
			.andExpect(notNull("$.errorMessage"));

	}

	/**
	 * Not null.
	 *
	 * @param jsonPath the json path
	 * @return the result matcher
	 */
	private static ResultMatcher notNull(String jsonPath) {
		return jsonPath(jsonPath, is(notNullValue()));
	}


	/**
	 * Test method for {@link ae.gov.sg.bookstore.web.BookController#create(ae.gov.sg.bookstore.dto.BookDTO)}.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testCreate() throws Exception {
		mockMvc.perform(post("/api/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Effective Java\","
						+ "\"description\":\"Effective Java\", "
						+ "\"author\":\"Joshua Bloch\", "
						+ "\"isbn\":\"978-0134685991\", "
						+ "\"classification\":\"Technology\", "
						+ "\"price\":\"117.24\"}"))
		.andExpect(status().isCreated());
	}

	
	@Test
	void testCreateInvalid() throws Exception {
		mockMvc.perform(post("/api/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Effective Java 3\","
						+ "\"description\":\"Effective Java\", "
						+ "\"author\":\"Joshua Bloch\", "
						+ "\"isbn\":\"\", "
						+ "\"classification\":\"Technology\", "
						+ "\"price\":\"117.24\"}"))
		.andExpect(status().isBadRequest());
	}

	
	/**
	 * Test method for {@link ae.gov.sg.bookstore.web.BookController#update(java.lang.Integer, ae.gov.sg.bookstore.dto.BookDTO)}.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testUpdate() throws Exception {
		mockMvc.perform(put("/api/books/2")
			.content("{\"name\":\"Effective Java\","
				+ "\"description\":\"Effective Java\", "
				+ "\"author\":\"Joshua Bloch\", "
				+ "\"isbn\":\"978-013400000\", "
				+ "\"classification\":\"Technology\", "
				+ "\"price\":\"117.24\"}")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	/**
	 * Test method for {@link ae.gov.sg.bookstore.web.BookController#delete(java.lang.Integer)}.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDelete() throws Exception {
		mockMvc.perform(delete("/api/books/3")).andExpect(status().isOk());
	}


	
}
