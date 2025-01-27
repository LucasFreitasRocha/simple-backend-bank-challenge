package io.github.lucasfreitasrocha.simplebank.entrryPoint.controller;

import io.github.lucasfreitasrocha.simplebank.SimpleBankApplication;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.entity.UserEntity;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.entity.UserTypeEntity;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.repository.AccountRepository;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = SimpleBankApplication.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private static final String CPF = "55731535019";
    private static final String CNPJ = "14059178000112";
    private static final String EMAIL = "ts@ts.com";
    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void setDown() {
        userRepository.deleteAll();
        accountRepository.deleteAll();
    }


    @Test
    void emailAndDocumentAlreadyExist() throws Exception {
        UserEntity user = new UserEntity();
        user.setDocument(CPF);
        user.setEmail(EMAIL);
        user.setType(UserTypeEntity.PF);
        user = userRepository.save(user);

        mockMvc.perform(post("/user")
                        .content("""
                                {
                                    "name": "teste",
                                    "document": "%s",
                                    "email": "%s",
                                    "password": "12345",
                                    "type": "PF"
                                }
                                """.formatted(CPF, EMAIL))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].field").value("email"))
                .andExpect(jsonPath("$.errors[0].message").value("esse email já existe"))
                .andExpect(jsonPath("$.errors[1].field").value("document"))
                .andExpect(jsonPath("$.errors[1].message").value("esse documento já existe"))
        ;
    }


    @Test
    void documentInvalid() throws Exception {
        mockMvc.perform(post("/user")
                        .content("""
                                {
                                    "name": "teste",
                                    "document": "1111",
                                    "email": "%s",
                                    "password": "12345",
                                    "type": "PF"
                                }
                                """.formatted(EMAIL))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].field").value("document"))
                .andExpect(jsonPath("$.errors[0].message").value("informe um documento valido, cpf ou cnpj"));
    }

    @Test
    void sendCNPJandPF() throws Exception {
        mockMvc.perform(post("/user")
                        .content("""
                                {
                                    "name": "teste",
                                    "document": "%s",
                                    "email": "%s",
                                    "password": "12345",
                                    "type": "PF"
                                }
                                """.formatted(CNPJ, EMAIL))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].field").value("type/document"))
                .andExpect(jsonPath("$.errors[0].message").value("informe o documento cnpj de acordo com o tipo PJ"));
    }


    @Test
    void sendCPFandPJ() throws Exception {
        mockMvc.perform(post("/user")
                        .content("""
                                {
                                    "name": "teste",
                                    "document": "%s",
                                    "email": "%s",
                                    "password": "12345",
                                    "type": "PJ"
                                }
                                """.formatted(CPF, EMAIL))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].field").value("type/document"))
                .andExpect(jsonPath("$.errors[0].message").value("informe o documento cpf de acordo com o tipo PF"));
    }

    @Test
    void sendInvalidEmail() throws Exception {
        mockMvc.perform(post("/user")
                        .content("""
                                {
                                    "name": "teste",
                                    "document": "%s",
                                    "email": "teste",
                                    "password": "12345",
                                    "type": "PF"
                                }
                                """.formatted(CPF))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].field").value("email"))
                .andExpect(jsonPath("$.errors[0].message").value("informe um email valido"));
    }

    @Test
    void createUserSuccess() throws Exception {
        mockMvc.perform(post("/user")
                        .content("""
                                {
                                     "name": "teste",
                                     "document": "%s",
                                     "email": "%s",
                                     "password": "12345",
                                     "type": "PF"
                                 }
                                """.formatted(CPF, EMAIL))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}