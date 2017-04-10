package com.bsiag.anagnostes.client.hcr;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.platform.classid.ClassId;

@ClassId("7550a8ee-943b-4192-89d3-b21d27b2e63c")
public class HcrPage extends AbstractPageWithNodes {

	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected boolean getConfiguredTableVisible() {
		return false;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("PaymentSlip");
	}

	@Override
	protected Class<? extends IForm> getConfiguredDetailForm() {
		return HcrForm.class;
	}
}
