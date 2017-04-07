package com.bsiag.anagnostes.demoapp.client.helloworld;

import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.runner.RunWith;

import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm;

/**
 * <h3>{@link HelloWorldFormTest}</h3> Contains Tests for the
 * {@link HcrForm}.
 *
 * @author cbu
 */
@RunWith(ClientTestRunner.class)
@RunWithSubject("anonymous")
@RunWithClientSession(TestEnvironmentClientSession.class)
public class HelloWorldFormTest {

}
