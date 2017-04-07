package com.bsiag.anagnostes.demoapp.server.nn.util;

public class LoggingUtility {

	public static class InfiniteProgressBar {
		private int m_state = 0;
		
		private final String[] PROGRESS_STATES = new String[]{
				"[                    ]", 
				"[>                   ]", 
				"[=>                  ]", 
				"[==>                 ]", 
				"[===>                ]", 
				"[<===>               ]", 
				"[ <===>              ]", 
				"[  <===>             ]", 
				"[   <===>            ]", 
				"[    <===>           ]", 
				"[     <===>          ]", 
				"[      <===>         ]", 
				"[       <===>        ]", 
				"[        <===>       ]", 
				"[         <===>      ]", 
				"[          <===>     ]", 
				"[           <===>    ]", 
				"[            <===>   ]", 
				"[             <===>  ]", 
				"[              <===> ]", 
				"[               <===>]", 
				"[                <===]", 
				"[                 <==]", 
				"[                  <=]", 
				"[                   <]", 
				"[                    ]" 
		};
		
		public void printProgress(String label) {
			int state = ((m_state / PROGRESS_STATES.length) % 2) == 0 ? m_state % PROGRESS_STATES.length : PROGRESS_STATES.length-1 - m_state % PROGRESS_STATES.length;
			System.out.print(String.format("%s: %s\r", PROGRESS_STATES[state], label));
			m_state++;
		}
	}
}
