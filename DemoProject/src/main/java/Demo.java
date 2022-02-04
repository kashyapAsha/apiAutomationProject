
public class Demo {

	public static void main(String[] args) {
		B b=new B();
		b.m1();
		b.m2();

	}
	
}
	
	abstract class A{
		abstract void m1();
		 void m2() {
			System.out.println("a method");		}
	}
	
	class B extends A{

		@Override
		void m1() {
			System.out.println("b method");	
			
		}
		
	}


