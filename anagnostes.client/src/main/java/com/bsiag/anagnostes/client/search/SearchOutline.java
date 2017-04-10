package com.bsiag.anagnostes.client.search;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractSearchOutline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bsiag.anagnostes.shared.Icons;
import org.eclipse.scout.rt.platform.classid.ClassId;

/**
 * <h3>{@link SearchOutline}</h3>
 *
 * @author cbu
 */
@Order(2000)
@ClassId("aa8f41e3-1b1e-41eb-b633-b3b32b3382bc")
public class SearchOutline extends AbstractSearchOutline {

	private static final Logger LOG = LoggerFactory.getLogger(SearchOutline.class);

	@Override
	protected void execSearch(final String query) {
		LOG.info("Search started");
		// TODO [cbu]: Implement search
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.Search;
	}
}
