package com.bsiag.anagnostes.demoapp.shared.hcr;

import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

@TunnelToServer
public interface ITestPersonLookupService extends ILookupService<String> {

}
