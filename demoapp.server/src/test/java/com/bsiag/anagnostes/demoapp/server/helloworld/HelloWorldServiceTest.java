package com.bsiag.anagnostes.demoapp.server.helloworld;

import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.runner.RunWith;

import com.bsiag.anagnostes.demoapp.server.ServerSession;

/**
 * <h3>{@link HelloWorldServiceTest}</h3>
 *
 * @author cbu
 */
@RunWith(ServerTestRunner.class)
@RunWithSubject(HelloWorldServiceTest.SUBJECT_NAME)
@RunWithServerSession(ServerSession.class)
public class HelloWorldServiceTest {
	public static final String SUBJECT_NAME = "test_subject";

}
