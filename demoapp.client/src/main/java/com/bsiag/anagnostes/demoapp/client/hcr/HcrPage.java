package com.bsiag.anagnostes.demoapp.client.hcr;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.shared.TEXTS;

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
