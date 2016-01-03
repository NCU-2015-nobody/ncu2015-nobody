package com.nobody.dom;

public class CDTimer implements Runnable{
	private DOM dom;
	private boolean cd;
	private int cdTime;
	
	public CDTimer(DOM dom) {
		this.dom = dom;
		this.cd = dom.info.CD;
		this.cdTime = 10;
	}
	
	@Override
	public void run() {
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("run run run CD: " + cdTime);
			
			if(cdTime > 0)
			{
				cdTime--;
			}
			
			if(cdTime == 0)
			{
				cd = false;
			}
			else // cdTime != 0
			{
				cd = true;
			}
		}
	}
	
	public void setCDTime(int cdTime)
	{
		this.cdTime = cdTime;
	}
}
