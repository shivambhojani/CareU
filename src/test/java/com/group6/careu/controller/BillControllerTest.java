package com.group6.careu.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.UserDocument;
import com.group6.careu.service.BillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BillController.class})
@ExtendWith(SpringExtension.class)
class BillControllerTest {
    @Autowired
    private BillController billController;

    @MockBean
    private BillService billService;

    @Test
    void testShowBill() throws Exception {
        UserDocument userDocument = new UserDocument();
        userDocument.setFileContent("AAAAAAAA".getBytes("UTF-8"));
        userDocument.setFileName("bill.txt");
        userDocument.setFileType("File Type");
        userDocument.setId(1);
        userDocument.setUserId(123);
        when(this.billService.billProcessor((Integer) any(), (Integer) any())).thenReturn(userDocument);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bill/{id}/{bankReferenceCode}", 1, 1);
        MockMvcBuilders.standaloneSetup(this.billController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("bill"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("bill"));
    }

    @Test
    void testShowBill2() throws Exception {
        UserDocument userDocument = new UserDocument();
        userDocument.setFileContent("AAAAAAAA".getBytes("UTF-8"));
        userDocument.setFileName("bill.txt");
        userDocument.setFileType("File Type");
        userDocument.setId(1);
        userDocument.setUserId(123);
        when(this.billService.billProcessor((Integer) any(), (Integer) any())).thenReturn(userDocument);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/bill/{id}/{bankReferenceCode}", 1, 1);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.billController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("bill"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("bill"));
    }
}

