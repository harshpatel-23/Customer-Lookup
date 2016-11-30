package cusutils.cusData;

public class Customer {
    private Name name;
    private PhoneNum phone;

    public Customer(Name cusName, PhoneNum cusPhone) {
        name = cusName;
        phone = cusPhone;
    }

	public Name getName() {
        return name;
    }

    public PhoneNum getPhone() {
        return phone;
    }

    public String toString() {
        return "Customer: " + name + "  " + phone;
    }

    public void print() {
        System.out.println(this);
    }
    
    @Override
	public int hashCode() {
		return name.hashCode()+phone.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		Customer customer;
    	
    	if(!(o instanceof Customer)){
    		return false;
    	}
    	
    	customer = (Customer) o;
    	
    	if(this.name.equals(customer.name) && this.phone.equals(customer.phone)){
    		return true;
    	}
    	else{
    		return false;
    		}
	}    
}