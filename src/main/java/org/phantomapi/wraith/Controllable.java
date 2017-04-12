package org.phantomapi.wraith;

public interface Controllable
{
	public void tick();
	
	public void start();
	
	public void stop();
	
	public Controllable getParent();
	
	public GList<Controllable> getChildren();
	
	public String getName();
	
	public boolean isRoot();
	
	public boolean isActive();
	
	public void register(Controllable controllable);
	
	public boolean isTicked();
	
	public double getTickRate();
	
	public TickHandler getTickHandler();
}
