//package com.example.Shop;
//
//import au.com.dius.pact.provider.junit.Consumer;
//import au.com.dius.pact.provider.junit.Provider;
//import au.com.dius.pact.provider.junit.State;
//import au.com.dius.pact.provider.junit.loader.PactBroker;
//import au.com.dius.pact.provider.junit5.HttpTestTarget;
//import au.com.dius.pact.provider.junit5.PactVerificationContext;
//import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.TestTemplate;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//@Provider("shop")
//@Consumer("manager")
//@PactBroker(host = "localhost", port = "8083")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ShopProviderTest {
//
//    @BeforeEach
//    void before(PactVerificationContext context) {
//        context.setTarget(new HttpTestTarget("localhost", port));
//    }
//    @LocalServerPort
//    private int port;
//
//    @BeforeAll
//    static void enablePublishingPact() {
//        System.setProperty("pact.verifier.publishResults", "true");
//    }
//
//    @TestTemplate
//    @ExtendWith(PactVerificationInvocationContextProvider.class)
//    void pactVerificationTestTemplate(PactVerificationContext context) {
//        context.verifyInteraction();
//    }
//
//    @State("valid shopId received from shop")
//    public void validDateProvider() {
//    }
//}
