package com.postcode.au.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.postcode.au.api.service.PostCodeServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PostCodeServiceTest {
    private PostCodeServices postCodeServiceMock;

    @BeforeEach
    public void setUp() {
        postCodeServiceMock = Mockito.mock(PostCodeServices.class);

    }

    @Test
    public void createClientSuccessfuly() throws Exception {

        when(postCodeServiceMock.searchByPostCode(contains("3000"))).thenReturn(new ArrayList<>());
        assertNotNull(postCodeServiceMock.searchByPostCode("3000"));
    }
}
