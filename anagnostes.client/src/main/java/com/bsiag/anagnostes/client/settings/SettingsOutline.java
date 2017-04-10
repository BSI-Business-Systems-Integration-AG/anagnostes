package com.bsiag.anagnostes.client.settings;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.shared.TEXTS;

import com.bsiag.anagnostes.shared.Icons;
import org.eclipse.scout.rt.platform.classid.ClassId;

/**
 * <h3>{@link SettingsOutline}</h3>
 *
 * @author cbu
 */
@Order(3000)
@ClassId("a6f6acee-4e41-40ac-a548-e9c328640612")
public class SettingsOutline extends AbstractOutline {

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Settings");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.Gear;
	}
}
