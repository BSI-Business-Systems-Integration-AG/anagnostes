package com.bsiag.anagnostes.demoapp.shared.hcr;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class TestPersonLookupCall extends LookupCall<String>{
	private static final long serialVersionUID = 1L;
		
	@Override
	protected Class<? extends ILookupService<String>> getConfiguredService() {
		return ITestPersonLookupService.class;
	}

}
