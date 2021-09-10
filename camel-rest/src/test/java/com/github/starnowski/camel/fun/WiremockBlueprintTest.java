package com.github.starnowski.camel.fun;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.Properties;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WiremockBlueprintTest extends CamelBlueprintTestSupport {

    @Produce(uri = "direct:sendHttpGetRequest")
    protected ProducerTemplate inputEndpoint;

    @Rule
//    @ClassRule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/camel-wiremock.xml";
    }

    protected String setConfigAdminInitialConfiguration(final Properties props) {
        props.setProperty("service.host", "localhost:" + wireMockRule.getOptions().portNumber());
        // etc...

        return "com.github.starnowski.camel.fun.wiremock.stuff";
    }

    @Test
    public void exampleTest() {
        // GIVEN
        stubFor(get("/getAllUsers")
//                .withHeader("Content-Type", containing("application/xml"))
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>SUCCESS</response>")));
        Exchange senderExchange = new DefaultExchange(context, ExchangePattern.InOut);

        // WHEN
        Exchange output = inputEndpoint.send(senderExchange);

        // THEN
        verify(getRequestedFor(urlPathEqualTo("/getAllUsers"))
                .withHeader("Content-Type", equalTo("text/xml")));
    }

}
