package edu.pdx.cs410J.moes;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static edu.pdx.cs410J.web.HttpRequestHelper.Response;
import static edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class PhoneBillRestClient {

    private static final String WEB_APP = "phonebill";
    private static final String SERVLET = "calls";

  private final HttpRequestHelper http;


    /**
     * Creates a client to the Phone Bil REST service running on the given host and port
     * @param hostName The name of the host
     * @param port The port
     */
    public PhoneBillRestClient( String hostName, int port )
    {
      this(new HttpRequestHelper(String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET)));
    }

  @VisibleForTesting
  PhoneBillRestClient(HttpRequestHelper http) {
    this.http = http;
  }


  /**
   * check if the reponse from a request is ok or not, and gives info on that
   * @param response is the http response
   */
    private void throwExceptionIfNotOkayHttpStatus(Response response) {
      int code = response.getHttpStatusCode();
      if (code != HTTP_OK) {
        String message = response.getContent();
        throw new RestException(code, message);
      }
    }

  /**
   * This function is a call to having the arguments to return all calls in a phone bill
   * @param customer is the customer name as a parameter
   */
  public String returnAllCallsCustomer(String customer) throws IOException {
    Response response = http.get(Map.of("customer", customer));
    throwExceptionIfNotOkayHttpStatus(response);
    return response.getContent();
  }

  /**
   * This function is a call to having the return of all calls in a time range
   * @param customer is the customer name
   * @param begin is the start time
   * @param end is the end time
   */
  public String returnAllCallsCustomerTimeRange(String customer, String begin, String end) throws IOException{
    Response response = http.get(Map.of("customer", customer, "begin", begin, "end", end));
    throwExceptionIfNotOkayHttpStatus(response);
    return response.getContent();
  }

  /**
   * This function is a call to having a call posted to the website
   * @param customer is the customer name
   * @param callerNumber is the caller's number
   * @param calleeNumber is the callee number
   * @param begin is the start time of the call
   * @param end is the end time of the call
   */
  public String createNewCallInBill(String customer, String callerNumber, String calleeNumber, String begin, String end)
  throws IOException {
    Response response = http.post(Map.of("customer", customer, "callerNumber", callerNumber,
            "calleeNumber", calleeNumber, "begin", begin, "end", end));
    throwExceptionIfNotOkayHttpStatus(response);
    return response.getContent();
  }

  /**
   * This function is mostly just a wrapper class to the pretty print to standard out for the client
   * @param customer is the customer name as a stringggggggggggggg
   */
  public String prettyPrintIt(String customer) throws IOException{
    customer = (customer + "-pretty");
    Response response = http.get(Map.of("customer", customer));
    throwExceptionIfNotOkayHttpStatus(response);
    return response.getContent();
  }

}
