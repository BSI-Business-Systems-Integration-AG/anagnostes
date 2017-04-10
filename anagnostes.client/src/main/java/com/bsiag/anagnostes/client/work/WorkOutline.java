package com.bsiag.anagnostes.client.work;

import java.util.List;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

import com.bsiag.anagnostes.client.hcr.HcrPage;
import com.bsiag.anagnostes.shared.Icons;
import org.eclipse.scout.rt.platform.classid.ClassId;

/**
 * <h3>{@link WorkOutline}</h3>
 *
 * @author cbu
 */
@Order(1000)
@ClassId("bac2e340-bfd5-448c-92e7-52974c4defec")
public class WorkOutline extends AbstractOutline {

	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		super.execCreateChildPages(pageList);
		pageList.add(new HcrPage());
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Work");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.Pencil;
	}
}
