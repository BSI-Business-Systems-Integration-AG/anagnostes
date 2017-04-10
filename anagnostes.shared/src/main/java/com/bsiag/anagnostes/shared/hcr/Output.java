package com.bsiag.anagnostes.shared.hcr;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Output {
	
	private final double m_confidence;
	private final String m_value;
	private final List<OutputEntry> m_outputVector;
	
	public Output(final List<OutputEntry> outputVector) {
		m_outputVector = outputVector;
		OutputEntry maxConfidenceEntry = getMaxConfidenceEntry(m_outputVector);
		m_confidence = maxConfidenceEntry.getOutput();
		m_value = maxConfidenceEntry.getLabel();
	}

	public OutputEntry getMaxConfidenceEntry(final List<OutputEntry> outputVector) {
		if(outputVector.size() < 1) {
			throw new RuntimeException("need at least one entry in the output vector");
		}
		OutputEntry maxEntry = outputVector.get(0);
		for (OutputEntry outputEntry : outputVector) {
			if(outputEntry.getOutput() > maxEntry.getOutput()) {
				maxEntry = outputEntry;
			}
		}
		return maxEntry;
	}
	
	public double getConfidence() {
		return m_confidence;
	}

	public String getCharacter() {
		return m_value;
	}

	@Override
	public String toString() {
		return "Recognized character: '" + getCharacter() + "', confidence: " + new DecimalFormat("0.000").format(getConfidence()) + " -- " + outputVectorToString();
	}
	
	private String outputVectorToString() {
		return m_outputVector.stream().map(e -> e.toString()).collect(Collectors.joining(", "));
	}
}
