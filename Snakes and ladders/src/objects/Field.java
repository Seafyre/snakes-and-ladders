package objects;

public class Field {
	
	Field(int id, int link)
	{
		this.init(id, link);
	}
	
	//initial methods
	public void init(int id, int link)
	{
		this.id = id;
		this.link = link;
	}
	
	
	
	//getter methods
	public int getid()
	{
		return this.id;
	}
	
	public int getlink()
	{
		return this.link;
	}
	
	
	//attributes
	private int id;
	private int link;

}
