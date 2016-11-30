package cusutils.cusData;

public class Name {
    private String lastName;
    private String firstName;

    public Name(String lName, String fName) {
        lastName = lName;
        firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }


    public String toString() {
        return lastName + ", " + firstName;
    }
    @Override
    public int hashCode() {
    	int count=0;
    	count=count+ lastName.hashCode() *31;
    	count=count+firstName.hashCode()*31;
    	return count;
    }
    
    @Override 
    public boolean equals(Object o) {
    	Name name;
    	if(!(o instanceof Name)) {
    		return false;
    		}
   
    	name =(Name) o;
    	
    	if(this.getFirstName().equals(name.getFirstName()) && this.getLastName().equals(name.getLastName())){
    		return true;
    		}
    	else{
    		return false;
    		}
    }
}