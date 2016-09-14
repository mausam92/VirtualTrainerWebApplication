
public class Dog {
@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name  "+this.name+"  colour  "+this.colour;
	}
private String name;
private String colour;
	public Dog() {

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}

}
