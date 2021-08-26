package design.lld.tic_tac_toe;

public class Player {
	
	private long id;
	
	private String name;
	
	public Player(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
