package com.bsiag.anagnostes.shared.hcr;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.rt.platform.classid.ClassId;

@ClassId("da91a174-5553-43fe-9bb7-a27c17aa93e9")
public class ScanLookupCall extends LookupCall<String>{
	private static final long serialVersionUID = 1L;
		
	@Override
	protected Class<? extends ILookupService<String>> getConfiguredService() {
		return IScanLookupService.class;
	}
}
