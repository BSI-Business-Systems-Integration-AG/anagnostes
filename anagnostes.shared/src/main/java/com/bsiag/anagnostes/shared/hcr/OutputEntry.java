package com.bsiag.anagnostes.shared.hcr;

import java.text.DecimalFormat;

public class OutputEntry {

	private String m_label;
	private double m_output;

	public OutputEntry(String label, double output) {
		this.m_label = label;
		this.m_output = output;
	}

	public final String getLabel() {
		return m_label;
	}

	public final double getOutput() {
		return m_output;
	}

	@Override
	public String toString() {
		return getLabel() + ": " + new DecimalFormat("0.000").format(getOutput());
	}
}
