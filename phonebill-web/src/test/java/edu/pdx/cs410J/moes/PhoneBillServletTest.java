package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.http.HttpRequest;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link PhoneBillServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
class PhoneBillServletTest {

  /**
   * This test will test that we can invoke the get request from the server
   */
  @Test
  void testGet() throws IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    servlet.doGet(request, response);
  }


  /**
   * This test will test that we can invoke the post request from the server
   */
  @Test
  void testPost() throws IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    servlet.doPost(request, response);
  }

  /**
   * This test will test that we can invoke the delete request from the server
   */
  @Test
  void testDelete() throws IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    // servlet.doGet(request, response);
    servlet.doDelete(request, response);
  }

  /**
   * Test that pretty print can be called with bad data and it wil not find it
   */
  @Test
  void testPrettyPrintItButNoValidCustomer() throws IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    servlet.prettyPrintIt("invalid", response);
  }

  /**
   * Tests that we can call doDelete With no data
   */
  @Test
  void testWriteCustomerCallsReturnSC_NOT_FOUND() throws IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    servlet.doDelete(request, response);
  }

  /**
   * Tests that we get bad request when doing a get with no data
   */
  @Test
  void testDoGetNoDataBadRequest() throws IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);
    servlet.doGet(request, response);
    verify(pw, never()).println(anyString());
    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }

  /**
   * TEsting stuff idk man
   */
  @Test
  void testingSomething() throws IOException {
    String hostName = "localHost";
    int port = 8080;
    PhoneBillRestClient client = mock(PhoneBillRestClient.class);
    client.createNewCallInBill("Customer", "123-123-1234", "123-123-1234",
            "10/10/2022 11:30 AM", "10/10/2022 12:30 AM");
    try {
      client.returnAllCallsCustomerTimeRange("Customer", "10/10/2022 11:30 AM", "10/10/2022 12:30 AM");
    }
    catch (HttpRequestHelper.RestException e){
      System.out.println(e.getMessage());
    }
  }

  /**
   * TEst
   */
  @Test
  void testThing() throws IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpRequestHelper http = mock(HttpRequestHelper.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    http.post(Map.of("customer", "Tom", "callerNumber", "123-123-1234", "calleeNumber", "123-123-1234",
            "10/10/2022 10:30 am", "10/10/2022 11:30 am"));
    http.get(Map.of("customer", "Tom", "begin", "10/10/2022", "end", "10/10/2022 11:30 am"));
  }


  /**
   * TEst
   */
  @Test
  void testQuickDateCheck() throws IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpRequestHelper http = mock(HttpRequestHelper.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);

  }

/**
  @Test
  void initiallyServletContainsNoDictionaryEntries() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    // Nothing is written to the response's PrintWriter
    verify(pw, never()).println(anyString());
    verify(response).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  void addOneWordToDictionary() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    String word = "TEST WORD";
    String definition = "TEST DEFINITION";

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("word")).thenReturn(word);
    when(request.getParameter("definition")).thenReturn(definition);

    HttpServletResponse response = mock(HttpServletResponse.class);

    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    assertThat(stringWriter.toString(), containsString(Messages.definedWordAs(word, definition)));

    // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
    ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
    verify(response).setStatus(statusCode.capture());

    assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));

    assertThat(servlet.getDefinition(word), equalTo(definition));
  }
  */


}
