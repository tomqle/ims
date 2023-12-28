package dev.tomle.ims.interfaces;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.tomle.ims.ImsApplication;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@ActiveProfiles("fixedport")
public class RestApiTest {

	/*private final static int EXPECTED_PORT = 8080;
	@Value("${server.port}")
	private int serverPort;

	@Test
	public void givenFixedPortAsServerPort_whenReadServerPort_thenGetThePort() {
	    assertEquals(EXPECTED_PORT, serverPort);
	}*/

	@Test
	public void contextLoads() throws InterruptedException {
		Thread.sleep(360000);
	}
}
