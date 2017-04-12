package org.phantomapi.wraith;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class ControllablePlugin extends JavaPlugin implements Controllable
{
	private Controller base;
	
	public void onEnable()
	{
		base = new Controller(null)
		{
			@Override
			public void onStop()
			{
				ControllablePlugin.this.onStop();
			}
			
			@Override
			public void onStart()
			{
				ControllablePlugin.this.onStop();
			}
			
			@Override
			public void onTick()
			{
				ControllablePlugin.this.onTick();
			}
		};
		
		start();
	}
	
	public abstract void onStart();
	public abstract void onStop();
	
	public void onTick()
	{
		
	}
	
	public void onDisable()
	{
		stop();
	}

	@Override
	public void tick()
	{
		
	}

	@Override
	public void start()
	{
		preStart();
		base.start();
	}

	@Override
	public void stop()
	{
		base.stop();
	}

	@Override
	public Controllable getParent()
	{
		return base.getParent();
	}

	@Override
	public GList<Controllable> getChildren()
	{
		return base.getChildren();
	}

	@Override
	public boolean isRoot()
	{
		return true;
	}

	@Override
	public boolean isActive()
	{
		return base.isActive();
	}

	@Override
	public void register(Controllable controllable)
	{
		base.register(controllable);
	}

	@Override
	public boolean isTicked()
	{
		return base.isTicked();
	}

	@Override
	public double getTickRate()
	{
		return base.getTickRate();
	}

	@Override
	public TickHandler getTickHandler()
	{
		return base.getTickHandler();
	}
	
	private void preStart()
	{
		if(findTicked())
		{
			base.ticked = true;
			base.tickRate = findTickValue();
			base.tickHandle = findTickHandle();
		}
	}
	
	private boolean findTicked()
	{
		return getClass().isAnnotationPresent(Ticked.class);
	}
	
	private double findTickValue()
	{
		if(base.ticked)
		{
			return getClass().getAnnotationsByType(Ticked.class)[0].value();
		}
		
		return 0;
	}
	
	private TickHandler findTickHandle()
	{
		if(getClass().isAnnotationPresent(TickHandle.class))
		{
			return getClass().getAnnotationsByType(TickHandle.class)[0].value();
		}
		
		return TickHandler.SYNCED;
	}
}
