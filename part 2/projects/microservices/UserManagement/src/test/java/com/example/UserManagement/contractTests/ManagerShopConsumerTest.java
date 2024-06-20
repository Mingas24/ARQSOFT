//package com.example.UserManagement.contractTests;
//
//import au.com.dius.pact.consumer.MockServer;
//import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
//import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
//import au.com.dius.pact.consumer.junit5.PactTestFor;
//import au.com.dius.pact.core.model.RequestResponsePact;
//import au.com.dius.pact.core.model.annotations.Pact;
//import io.pactfoundation.consumer.dsl.LambdaDsl;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.fluent.Request;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@ExtendWith(PactConsumerTestExt.class)
//@PactTestFor(providerName = "shop", port = "8084")
//public class ManagerShopConsumerTest {
//
//    @Pact(consumer = "manager")
//    public RequestResponsePact validShopIdFromShop(PactDslWithProvider builder) {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("content-type", "application/json");
//        return builder
//                .given("valid shopId received from shop")
//                .uponReceiving("valid shopId from shop")
//                .method("Post")
//                .path("/graphql")
//                .query(
//                        "query { " +
//                        "  getShopById(shopId:9) { " +
//                        "    shop_id " +
//                        "  } " +
//                        "}"
//                )
//                .willRespondWith()
//                .headers(headers)
//                .status(200)
//                .body(LambdaDsl.newJsonBody((object) -> {
//                    object.object("data", (object1) -> {
//                                object1.object(
//                                        "getShopById", (object2) -> {
//                                            object2.numberType("shop_id", 1);
//                                        }
//                                );
//                            }
//                    );
////              Exemplo do como vai ficar o retorno
////                {
////                    "data": {
////                        "getShopById": {
////                            "shop_Id": 1
////                        }
////                    }
////                }
//                }).build())
//                .toPact();
//    }
//
//    @Test
//    @PactTestFor(pactMethod = "validShopIdFromShop")
//    public void testValidDateFromProvider(MockServer mockServer) throws IOException {
//        HttpResponse httpResponse = Request.Post(mockServer.getUrl() + "/graphql")
//                .execute().returnResponse();
//        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
//    }
//}
