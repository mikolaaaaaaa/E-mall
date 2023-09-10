//package by.mikola.clientservice;
//
//import by.mikola.clientservice.controller.impl.ClientControllerImpl;
//import by.mikola.clientservice.dto.client.ClientResponse;
//import by.mikola.clientservice.entity.Client;
//import by.mikola.clientservice.service.ClientService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(
//		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//		classes = ClientServiceApplication.class)
//@AutoConfigureMockMvc
////@TestPropertySource(
////		locations = "classpath:application.properties")
//class ClientServiceApplicationTests {
//
//	@Autowired
//	private ClientControllerImpl clientController;
//
//	@Autowired
//	private ClientService clientService;
//
//	public ClientServiceApplicationTests() {
//		MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testGetAllClients() {
//		List<Client> clients = Arrays.asList(
//				new Client(1L,"mikola@a1.by"),
//				new Client(2L, "danila@a1.by")
//		);
//		List<ClientResponse> clientsResponse = Arrays.asList(
//				new ClientResponse(1L,"mikola@a1.by"),
//				new ClientResponse(2L, "danila@a1.by")
//		);
//		when(clientService.getClients()).thenReturn(clients);
//
//		List<ClientResponse> result = clientController.getAllClients();
//
//		assertEquals(clientsResponse, result);
//	}
//
//}
