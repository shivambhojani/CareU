package com.example.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

	NoNameController nNcontroller = new NoNameController();

	@Test
	void contextLoads() {
		String val = nNcontroller.getMessage();
		String expected = "App deployed";
		Assertions.assertEquals(val, expected);
	}

}
