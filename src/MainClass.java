
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
	
	AdditionClass adding = new AdditionClass();
	int ResultAdd = adding.doAddition(4, 5);

	
	System.out.println("Sum is: "+ResultAdd);
	
	SubtractionClass subtracting = new SubtractionClass();
	int ResultMinus = subtracting.doSubtraction(10, 3);
	
	System.out.println("Difference is: "+ResultMinus);
	
	
	AdditionClass addingThree = new AdditionClass(4,5,3);
	//added by Mehnaz	
	}
	
		
}
