package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PhoneBillRestClientTest {

  private HttpRequestHelper.Response dictionaryAsText(Map<String, String> dictionary) {
    StringWriter writer = new StringWriter();
    new TextDumper(writer).dump(dictionary);

    return new HttpRequestHelper.Response(writer.toString());
  }

  /**
   * Tests the ting for the thing so it do it good and not bad
   */
  @Test
  void testDoGetValidInputSC_OK() throws IOException{
    /**
    Map<String, String> args = Map.of("customer", "Thomas", "begin", "10/10/2022 10:30 am", "end",
            "10/10/2022 11:30 am");
    HttpRequestHelper http = mock(HttpRequestHelper.class);
    when(http.get(eq(args)));

    PhoneBillRestClient client = new PhoneBillRestClient(http);
     */
  }

  /**
   * Test that  we can call return all callss customer
   */
  @Test
  void testReturnAllCustomer() throws IOException{
    HttpRequestHelper http = mock(HttpRequestHelper.class);
    PhoneBillRestClient client = new PhoneBillRestClient(http);
  }

}
