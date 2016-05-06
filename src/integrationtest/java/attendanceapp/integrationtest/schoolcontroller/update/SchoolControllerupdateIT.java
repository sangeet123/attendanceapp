package attendanceapp.integrationtest.schoolcontroller.update;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import attendanceapp.controller.SchoolController;
import attendanceapp.controllerimpl.SchoolControllerImpl;
import attendanceapp.integrationtest.common.util.AttendanceAppUtilIT;
import attendanceapp.integrationtest.common.util.TestConfigurerIT;
import attendanceapp.integrationtest.utils.SchoolControllerUtilIT;
import attendanceapp.model.requestobject.SchoolUpdateRequestObject;

public class SchoolControllerupdateIT extends TestConfigurerIT {

	private static final String insertSchoolQuerySQLScriptFilePath = "it-insert-school-queries.sql";
	private static boolean isSettedUp = false;

	@Before()
	public void setUp() {
		super.setUp();
		if (!isSettedUp) {
			try {
				AttendanceAppUtilIT.mysqlScriptRunner(insertSchoolQuerySQLScriptFilePath);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			isSettedUp = true;
		}
	}

	@Configuration()
	@EnableWebMvc()
	public static class TestConfiguration {
		@Bean()
		public SchoolController schoolController() {
			return new SchoolControllerImpl();
		}
	}

	@Test()
	public void update_valid_school_that_exist_in_database() throws Exception {
		/*
		 * There is no way to verify created and updated date therefore jsonPath
		 * is used for the field that are known
		 */
		final String updateSchoolName = "newtest1schoolname";
		SchoolUpdateRequestObject schoolUpdateRequestObject = SchoolControllerUtilIT.getSchoolUpdateRequestObject(1L,
				updateSchoolName, "testemail@email.com", "2453469123");
		getMockMvc()
				.perform(put(SchoolControllerUtilIT.UPDATESCHOOL).contentType(AttendanceAppUtilIT.APPLICATION_JSON_UTF8)
						.content(AttendanceAppUtilIT.convertObjectToJsonBytes(schoolUpdateRequestObject)))
				.andExpect(status().isOk()).andExpect(content().contentType(AttendanceAppUtilIT.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.name", is(updateSchoolName)))
				.andExpect(jsonPath("$.email", is("testemail@email.com")))
				.andExpect(jsonPath("$.telephone", is("2453469123")));
	}

	@Test()
	public void updating_to_school_name_that_already_exist() throws Exception {
		String schoolThatExistInDatabase = "test10";
		SchoolUpdateRequestObject schoolUpdateRequestObject = SchoolControllerUtilIT.getSchoolUpdateRequestObject(1L,
				schoolThatExistInDatabase, "testemail@email.com", "2453469123");
		final String responseJsonString = "{\"statusCode\":3,\"message\":[\"School with given name already exists. Please enter different name.\"]}";
		getMockMvc()
				.perform(put(SchoolControllerUtilIT.UPDATESCHOOL).contentType(AttendanceAppUtilIT.APPLICATION_JSON_UTF8)
						.content(AttendanceAppUtilIT.convertObjectToJsonBytes(schoolUpdateRequestObject)))
				.andExpect(status().isConflict())
				.andExpect(content().contentType(AttendanceAppUtilIT.APPLICATION_JSON_UTF8))
				.andExpect(content().string(responseJsonString));
	}

	@Test()
	public void updating_school_that_does_not_exist() throws Exception {
		SchoolUpdateRequestObject schoolUpdateRequestObject = SchoolControllerUtilIT.getSchoolUpdateRequestObject(100L,
				"test100", "testemail@email.com", "2453469123");
		final String responseJsonString = "{\"statusCode\":2,\"message\":[\"School does not exist.\"]}";
		getMockMvc()
				.perform(put(SchoolControllerUtilIT.UPDATESCHOOL).contentType(AttendanceAppUtilIT.APPLICATION_JSON_UTF8)
						.content(AttendanceAppUtilIT.convertObjectToJsonBytes(schoolUpdateRequestObject)))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(AttendanceAppUtilIT.APPLICATION_JSON_UTF8))
				.andExpect(content().string(responseJsonString));
	}

}