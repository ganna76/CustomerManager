package com.steven.demo.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(CustomerController.class)
@SpringJUnitConfig
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerServiceMock;

    private Customer testCustomer1 = Customer.builder()
            .customerRef(4L)
            .customerName("Darth Vader")
            .addressLine1("1 Death Star")
            .addressLine2("")
            .town("Orion")
            .county("Black Hole")
            .country("Outer Space")
            .postcode("EVIL 123")
            .build();

    private Customer testCustomer2 = Customer.builder()
            .customerRef(99L)
            .customerName("Frank Furter")
            .addressLine1("17 Sausage Lane")
            .addressLine2("")
            .town("Frankfurt")
            .county("Outer Sausageshire")
            .country("Sausageland")
            .postcode("SA1 US2")
            .build();

    @Test
    public void testGetCustomers() throws Exception {

        when(customerServiceMock.getCustomers()).thenReturn(List.of(testCustomer1,testCustomer2));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/getcustomers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"customerRef\":4,\"customerName\":\"Darth Vader\",\"addressLine1\":\"1 Death Star\",\"addressLine2\":\"\",\"town\":\"Orion\",\"county\":\"Black Hole\",\"country\":\"Outer Space\",\"postcode\":\"EVIL 123\"},{\"customerRef\":99,\"customerName\":\"Gracie Mae Clark\",\"addressLine1\":\"17 Pelstream Avenue\",\"addressLine2\":\"\",\"town\":\"Stirling\",\"county\":\"Stirlingshire\",\"country\":\"UK\",\"postcode\":\"FK7 0BE\"}]"));
    }

    @Test
    public void testGetCustomerByRef() throws Exception {

        when(customerServiceMock.getCustomerByRef(4L)).thenReturn(List.of(testCustomer1));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/getcustomerbyref/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"customerRef\":4,\"customerName\":\"Darth Vader\",\"addressLine1\":\"1 Death Star\",\"addressLine2\":\"\",\"town\":\"Orion\",\"county\":\"Black Hole\",\"country\":\"Outer Space\",\"postcode\":\"EVIL 123\"}]"));
    }

    @Test
    public void testPutCustomers() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/putcustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCustomer2)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}

