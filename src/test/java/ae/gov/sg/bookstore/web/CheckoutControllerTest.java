/**
 * 
 */
package ae.gov.sg.bookstore.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;

/**
 * The Class CheckoutControllerTest.
 *
 * @author mohamed elzaiady
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class CheckoutControllerTest {
	
	/** The mock mvc. */
	@Autowired
	MockMvc mockMvc;


	/**
	 * Test method for {@link ae.gov.sg.bookstore.web.CheckoutController#checkout(java.util.List, java.lang.String)}.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetCheckout() throws Exception {
		mockMvc.perform(get("/api/checkout/")
				.param("books", "1,2,3")
				.param("promotionCode", "PRO-420-000"))
		.andExpect(status().isOk());		
	}
	
	/**
	 * Test get checkout using query parameters.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetCheckoutUsingQueryParameters() throws Exception {
		mockMvc.perform(get("/api/checkout/")
				.queryParam("books", "1,2,3")
				.queryParam("promotionCode", "PRO-420-000"))
		.andExpect(status().isOk());
		
	}

	
	/**
	 * Test get checkout no promotion code.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetCheckoutNoPromotionCode() throws Exception {
		mockMvc.perform(get("/api/checkout/")
				.queryParam("books", "1,2,3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalPrice").isNotEmpty())
				.andExpect(jsonPath("$.totalPrice").isNumber())
				.andExpect(jsonPath("$.totalPrice").value(94.17d + 121.0d + 428.91d));

	}

	
	/**
	 * Test method for {@link ae.gov.sg.bookstore.web.CheckoutController#checkout(java.util.List, java.lang.String)}.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testPostCheckout() throws Exception  {
		mockMvc.perform(post("/api/checkout/")
				.content("{\"books\":[1,2,3], \"promotionCode\":\"PRO-420-000\"}")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(jsonPath("$.totalPrice").isNotEmpty())
			    .andExpect(jsonPath("$.totalPrice").isNumber());

	}


}
