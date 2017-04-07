package com.bsiag.anagnostes.demoapp.shared.hcr;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import com.bsiag.anagnostes.demoapp.shared.helloworld.HcrFormData;

/**
 * <h3>{@link IHcrService}</h3>
 *
 * @author cbu
 */
@TunnelToServer
public interface IHcrService extends IService {
	HcrFormData load(HcrFormData input);
}
