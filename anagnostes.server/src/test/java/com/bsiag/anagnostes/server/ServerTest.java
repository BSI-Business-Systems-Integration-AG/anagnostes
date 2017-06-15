package com.bsiag.anagnostes.server;

import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.runner.RunWith;

/**
 * Base class for Anagnostes server tests.
 */
@RunWith(ServerTestRunner.class)
@RunWithSubject(ServerTest.SUBJECT_NAME)
@RunWithServerSession(ServerSession.class)
public class ServerTest {
	static final String SUBJECT_NAME = "test_subject";
}
