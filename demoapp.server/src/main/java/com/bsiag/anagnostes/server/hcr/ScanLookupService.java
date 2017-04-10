package com.bsiag.anagnostes.server.hcr;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.rt.server.services.lookup.AbstractLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

import com.bsiag.anagnostes.server.neuralnetwork.util.NumbersUtility;
import com.bsiag.anagnostes.shared.hcr.IScanLookupService;

public class ScanLookupService extends AbstractLookupService<String> implements IScanLookupService {

	@Override
	public List<? extends ILookupRow<String>> getDataByKey(ILookupCall<String> call) {
		if (NumbersUtility.getAllFolderNames().contains(call.getKey())) {
			return createLookupRowArray(new String[][] { { call.getKey(), call.getKey() } }, call, String.class);
		}
		return null;
	}

	@Override
	public List<? extends ILookupRow<String>> getDataByText(ILookupCall<String> call) {
		List<String> allFolderNames = NumbersUtility.getAllFolderNames();
		List<String[]> list = new ArrayList<String[]>();

		for (String folder : allFolderNames) {
			if (folder.contains(call.getText())) {
				list.add(new String[] { folder, folder });
			}
		}
		return createLookupRowArray(list.toArray(new String[0][0]), call, String.class);
	}

	@Override
	public List<? extends ILookupRow<String>> getDataByAll(ILookupCall<String> call) {
		List<String> allFolderNames = NumbersUtility.getAllFolderNames();
		List<String[]> list = new ArrayList<String[]>();

		for (String folder : allFolderNames) {
			list.add(new String[] { folder, folder });
		}
		return createLookupRowArray(list.toArray(new String[0][0]), call, String.class);
	}

	@Override
	public List<? extends ILookupRow<String>> getDataByRec(ILookupCall<String> call) {
		return null;
	}
}
