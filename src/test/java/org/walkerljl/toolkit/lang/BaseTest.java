package org.walkerljl.toolkit.lang;

import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * BaseTest
 *
 * @author lijunlin
 */
public abstract class BaseTest {

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void after() {
    }
}
