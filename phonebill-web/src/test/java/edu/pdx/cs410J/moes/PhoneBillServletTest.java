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
   * Test to make sure doGet can be properly called with valid args
   * with no customer added in before hand
   */
  @Test
  void testDoGetWorksWithValidArgsPassedInNoPreviousCustomer() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "Nathan";
    String begin = "10/10/2022 10:30 am";
    String end = "10/10/2022 11:30 am";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);

    servlet.doGet(request, response);

  }


  /**
   * Test to make sure doGet can be properly called with valid args
   * with no customer added in before hand but with -pretty in customer name
   */
  @Test
  void testDoGetWorksWithValidArgsPassedInNoPreviousCustomerPretty() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "Nathan-pretty";
    String begin = "10/10/2022 10:30 am";
    String end = "10/10/2022 11:30 am";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);

    servlet.doGet(request, response);

  }

  /**
   * Test to make sure doGet can be properly called with malfored date
   * with no customer added in before hand but with -pretty in customer name
   */
  @Test
  void testDoGetWorksBadDatePassed() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "Nathan-pretty";
    String begin = "dasdqw d q dw q dq qw ";
    String end = "10/10/2022 11:30 am";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);

    servlet.doGet(request, response);
  }



  /**
   * Test to make sure doPost can be properly called with valid args
   *
   */
  @Test
  void testDoPostWorksWithValidArgsPassedIn() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "Nathan";
    String begin = "10/10/2022 10:30 am";
    String end = "10/10/2022 11:30 am";
    String callerNumber = "123-123-1234";
    String calleeNumber = "123-123-1234";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);
    when(request.getParameter("calleeNumber")).thenReturn(calleeNumber);
    when(request.getParameter("callerNumber")).thenReturn(callerNumber);

    servlet.doPost(request, response);

  }

  /**
   * Test to make sure doPost can be properly called with non valid args, customer name
   *
   */
  @Test
  void testDoPostWorksWithNonValidArgsPassedInCustomer() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "";
    String begin = "10/10/2022 10:30 am";
    String end = "10/10/2022 11:30 am";
    String callerNumber = "123-123-1234";
    String calleeNumber = "123-123-1234";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);
    when(request.getParameter("calleeNumber")).thenReturn(calleeNumber);
    when(request.getParameter("callerNumber")).thenReturn(callerNumber);

    servlet.doPost(request, response);

  }

  /**
   * Test to make sure doPost can be properly called with non valid args, begin time
   *
   */
  @Test
  void testDoPostWorksWithNonValidArgsPassedInBegin() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "Nathan";
    String begin = "";
    String end = "10/10/2022 11:30 am";
    String callerNumber = "123-123-1234";
    String calleeNumber = "123-123-1234";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);
    when(request.getParameter("calleeNumber")).thenReturn(calleeNumber);
    when(request.getParameter("callerNumber")).thenReturn(callerNumber);

    servlet.doPost(request, response);

  }

  /**
   * Test to make sure doPost can be properly called with non valid args, end time
   *
   */
  @Test
  void testDoPostWorksWithNonValidArgsPassedInEnd() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "Nathan";
    String begin = "10/10/2022 10:30 am";
    String end = "";
    String callerNumber = "123-123-1234";
    String calleeNumber = "123-123-1234";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);
    when(request.getParameter("calleeNumber")).thenReturn(calleeNumber);
    when(request.getParameter("callerNumber")).thenReturn(callerNumber);

    servlet.doPost(request, response);

  }

  /**
   * Test to make sure doPost can be properly called with non valid args, caller
   *
   */
  @Test
  void testDoPostWorksWithNonValidArgsPassedIncallerNumber() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "Nathan";
    String begin = "10/10/2022 10:30 am";
    String end = "10/10/2022 11:30 am";
    String callerNumber = "";
    String calleeNumber = "123-123-1234";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);
    when(request.getParameter("calleeNumber")).thenReturn(calleeNumber);
    when(request.getParameter("callerNumber")).thenReturn(callerNumber);

    servlet.doPost(request, response);

  }

  /**
   * Test to make sure doPost can be properly called with non valid args, callee
   *
   */
  @Test
  void testDoPostWorksWithNonValidArgsPassedIncalleeNumber() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "Nathan";
    String begin = "10/10/2022 10:30 am";
    String end = "10/10/2022 11:30 am";
    String callerNumber = "123-123-1234";
    String calleeNumber = "";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);
    when(request.getParameter("calleeNumber")).thenReturn(calleeNumber);
    when(request.getParameter("callerNumber")).thenReturn(callerNumber);

    servlet.doPost(request, response);

  }

  /**
   * Test to make sure doPost can be properly called with non valid args, callee malformed
   *
   */
  @Test
  void testDoPostWorksWithNonValidArgsPassedIncalleeNumberMalformed() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "Nathan";
    String begin = "10/10/2022 10:30 am";
    String end = "10/10/2022 11:30 am";
    String callerNumber = "123-123-1234";
    String calleeNumber = "123-213-123-12-312-3-13-123-213-12-3";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);
    when(request.getParameter("calleeNumber")).thenReturn(calleeNumber);
    when(request.getParameter("callerNumber")).thenReturn(callerNumber);

    servlet.doPost(request, response);

  }

  /**
   * Test to make sure doPost can be properly called with non valid args, caller malformed
   *
   */
  @Test
  void testDoPostWorksWithNonValidArgsPassedIncallerNumberMalformed() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "Nathan";
    String begin = "10/10/2022 10:30 am";
    String end = "10/10/2022 11:30 am";
    String callerNumber = "123-123-1234-12312-312-3-123-12-312-3-123-12-3";
    String calleeNumber = "123-123-1234";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);
    when(request.getParameter("calleeNumber")).thenReturn(calleeNumber);
    when(request.getParameter("callerNumber")).thenReturn(callerNumber);

    servlet.doPost(request, response);

  }

  /**
   * Test to make sure that we can add a call when there is already a name under the system for the customer bill
   *
   */
  @Test
  void testDoPostWorkdsValidCustomerExistsWhenAddingAnother() throws ServletException, IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    String customer = "Nathan";
    String begin = "10/10/2022 10:30 am";
    String end = "10/10/2022 11:30 am";
    String callerNumber = "123-123-1234-12312-312-3-123-12-312-3-123-12-3";
    String calleeNumber = "123-123-1234";

    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("begin")).thenReturn(begin);
    when(request.getParameter("end")).thenReturn(end);
    when(request.getParameter("calleeNumber")).thenReturn(calleeNumber);
    when(request.getParameter("callerNumber")).thenReturn(callerNumber);

    servlet.doPost(request, response);
    servlet.doPost(request, response);

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

    HttpServletRequest request = mock(HttpServletRequest.class); ///////////////////////////////////////////////////
  /////////////////////////////// NOTE TO SELF USE THIS TO TEST GET AND POST STUFF
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
