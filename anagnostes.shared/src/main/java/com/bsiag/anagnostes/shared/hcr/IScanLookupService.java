package com.bsiag.anagnostes.shared.hcr;

import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

@TunnelToServer
public interface IScanLookupService extends ILookupService<String> {

}
