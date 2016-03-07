package be.kdg.kandoe.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/testcontext.xml"})
public class TestUsers {

    @Test
    public void testTest() {
        Assert.assertEquals(true,true); //TODO: Hier moet nog een echte test komen
    }
}
