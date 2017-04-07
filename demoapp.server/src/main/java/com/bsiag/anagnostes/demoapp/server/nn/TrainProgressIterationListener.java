package com.bsiag.anagnostes.demoapp.server.nn;

import org.deeplearning4j.nn.api.Model;
import org.deeplearning4j.optimize.api.IterationListener;

import com.bsiag.anagnostes.demoapp.server.nn.util.LoggingUtility;

public class TrainProgressIterationListener  implements IterationListener {
	private static final long serialVersionUID = 1L;
	
	private int m_printIterations = 10;
    private boolean m_invoked = false;
    private long m_iterCount = 0;
    
    private LoggingUtility.InfiniteProgressBar m_progressBar = new LoggingUtility.InfiniteProgressBar();

    public TrainProgressIterationListener(int printIterations) {
        this.m_printIterations = printIterations;
    }

    public TrainProgressIterationListener() {
    }

    @Override
    public boolean invoked(){ return m_invoked; }

    @Override
    public void invoke() { this.m_invoked = true; }

    @Override
    public void iterationDone(Model model, int iteration) {
        if(m_printIterations <= 0)
            m_printIterations = 1;
        if(m_iterCount % m_printIterations == 0) {
            invoke();
            double result = model.score();
            m_progressBar.printProgress("Iteration: " + m_iterCount + ", Score: " + result);
        }
        m_iterCount++;
    }
}
