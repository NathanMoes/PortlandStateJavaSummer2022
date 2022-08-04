package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration test that tests the REST calls made by {@link PhoneBillRestClient}
 */
@TestMethodOrder(MethodName.class)
class PhoneBillRestClientIT {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  private PhoneBillRestClient newPhoneBillRestClient() {
    int port = Integer.parseInt(PORT);
    return new PhoneBillRestClient(HOSTNAME, port);
  }

  /**
   * Test that we can call return all calls customer with valid input added in before to server
   * probalby add in a call then do return all calls for customer with name valid
   */
  @Test
  void testValidReturnAllCallsCustomer() throws IOException{
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.createNewCallInBill("Nathan", "123-123-1234", "123-123-1234",
            "10/10/2022 10:30 am", "10/10/2022 11:30 am");
    client.returnAllCallsCustomer("Nathan");
  }

  /**
   * Test that we can call get calls in range with valid input and data previous in the server

  @Test
  void testValidPhoneCallRangeReturnWithValidDataAddedAlready() throws IOException, RestException{
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.createNewCallInBill("Nathan", "123-123-1234", "123-123-1234",
            "10/10/2022 10:30 am", "10/10/2022 11:30 am");
    client.returnAllCallsCustomerTimeRange("Nathan", "10/10/2022 10:30 am", "10/10/2022 11:30 am");
  }
  */

  /**
   * Tests the pretty print it function
   */
  @Test
  void testPrettyPrintItFunction() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.createNewCallInBill("Nathan", "123-123-1234", "123-123-1234",
            "10/10/2022 10:30 am", "10/10/2022 11:30 am");
    client.prettyPrintIt("Nathan");
  }


  /**
  @Test
  void test4EmptyWordThrowsException() {
    PhoneBillRestClient client = newPhoneBillRestClient();
    String emptyString = "";

    RestException ex =
      assertThrows(RestException.class, () -> client.addDictionaryEntry(emptyString, emptyString));
    assertThat(ex.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
    assertThat(ex.getMessage(), equalTo(Messages.missingRequiredParameter("word")));
  }
  */

}
