package com.github.starnowski.camel.fun;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Assert;
import org.junit.Test;

public class RestBlueprintTest extends CamelBlueprintTestSupport {

    @Produce(uri = "direct:getAllUsers")
    protected ProducerTemplate inputEndpoint;

    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/camel.xml";
    }

    @Test
    public void shouldReturnAllUsers()
    {
        // GIVEN
        Exchange senderExchange = new DefaultExchange(context, ExchangePattern.InOut);

        // WHEN
        Exchange output = inputEndpoint.send(senderExchange);

        // THEN
        Assert.assertNotNull(output);
    }
}
