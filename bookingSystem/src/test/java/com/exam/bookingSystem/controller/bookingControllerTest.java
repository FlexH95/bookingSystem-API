package com.exam.bookingSystem.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.exam.bookingSystem.model.LectApplyList;
import com.exam.bookingSystem.model.LectList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class bookingControllerTest {

	int iResult = 0;

	private MockMvc mock;

	@Autowired
	WebApplicationContext webApplicationContext;

	public String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	public <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Before
	public void setUp() {
		mock = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.addFilters(new CharacterEncodingFilter("UTF-8", true)).alwaysDo(print()).build();
	}

	String sControl = "/booking/lect";

	@Test
	public void testGetLectAll() {
		try {

			MvcResult mvcResult = mock
					.perform(MockMvcRequestBuilders.get(sControl + "/all").accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			assertEquals(200, mvcResult.getResponse().getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Rollback(true)
	@Test
	public void testInsertLect() throws Exception {
		iResult = 0;
		LectList List = new LectList();
		List.setLecturerName("X");
		List.setPlaceName("X_Place");
		List.setCapCnt(10);
		List.setDateTimeStamp("202208011230");
		List.setLectDesc("X Lect");

		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.post(sControl + "/new")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapToJson(List))).andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
	public void testGetLectEmpList() {
		try {

			MvcResult mvcResult = mock.perform(
					MockMvcRequestBuilders.get(sControl + "/emp/list" + "").accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			assertEquals(200, mvcResult.getResponse().getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLectBefore7days() {
		try {

			MvcResult mvcResult = mock
					.perform(MockMvcRequestBuilders.get(sControl + "/show").accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			assertEquals(200, mvcResult.getResponse().getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Rollback(true)
	@Test
	public void testInsertLectEmp() throws Exception {
		iResult = 0;
		LectApplyList List = new LectApplyList();
		List.setLecturerName("B");
		List.setEmpNo("98765");

		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.post(sControl + "/emp/new")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapToJson(List))).andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	String empNoTest = "";

	@Test
	public void testGetLectListByEmp() {
		try {
			if (!empNoTest.isEmpty()) {

				MvcResult mvcResult = mock.perform(
						MockMvcRequestBuilders.get(sControl + "/" + empNoTest).accept(MediaType.APPLICATION_JSON_VALUE))
						.andReturn();

				assertEquals(200, mvcResult.getResponse().getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Rollback(true)
	@Test
	public void testDeleteLectEmp() {
		try {
			iResult = 0;
			LectApplyList List = new LectApplyList();
			List.setLecturerName("B");
			List.setEmpNo("12345");

			MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.delete(sControl + "/emp/{lect}")
					.contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapToJson(List))).andReturn();

			int status = mvcResult.getResponse().getStatus();

			assertEquals(200, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLectRank() {
		try {

			MvcResult mvcResult = mock
					.perform(MockMvcRequestBuilders.get(sControl + "/rank").accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			assertEquals(200, mvcResult.getResponse().getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
