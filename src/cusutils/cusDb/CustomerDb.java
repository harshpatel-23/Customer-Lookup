package cusutils.cusDb;
import cusutils.cusData.*;
import java.util.*;


public class CustomerDb {
    static ArrayList<Customer> cusList;
    static Map<PhoneNum,Customer> phone_search;
    static Map<Name,List<Customer>> name_search;
    public static int customerCnt() {
         return cusList.size();
    }

    public static Map<PhoneNum, Customer> readPhoneDir() {
    	return  phone_search;
	}
    public static Map<Name, List<Customer>> readNameDir() {
		return name_search;
	}
    
    public static void initialize() {
         Customer cus;
         PhoneNum phone;
         Name name;
         
         phone_search = new HashMap<PhoneNum,Customer>();
         name_search = new HashMap<Name,List<Customer>>();
         cusList = new ArrayList<Customer>();
         
         phone = new PhoneNum(510, "505-4472");
         name = new Name("Patel", "Harsh");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);
        
         /* duplicate of Harsh Patel name */
         phone = new PhoneNum(510, "505-4472");
         name = new Name("Patel", "Harsh");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);

         /* duplicate of Harsh Patel name */
         phone = new PhoneNum(510, "505-4472");
         name = new Name("Patel", "Harsh");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);

         phone = new PhoneNum(437, "225-6590");
         name = new Name("Jackson", "Sean");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);

         phone = new PhoneNum(408, "123-4567");
         name = new Name("Watson", "John");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);

         /* duplicate of John Watson name */
         phone = new PhoneNum(215, "678-4567");
         name = new Name("Watson", "John");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);

         /* duplicate of John Watson name */
         phone = new PhoneNum(621, "299-4353");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);

         /* duplicate of John Watson name */
         phone = new PhoneNum(436, "875-1892");
         name = new Name("Watson", "John");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);

         phone = new PhoneNum(615, "234-7961");
         name = new Name("Walters", "Susan");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);

         phone = new PhoneNum(423, "873-2479");
         name = new Name("Wu", "Willam");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);
         
         
         phone = new PhoneNum(423, "873-2479");
         name = new Name("Wu", "Willam");
         cus = new Customer(name, phone); 
         phone_search.put(cus.getPhone(), cus);

         phone = new PhoneNum(158, "809-1682");
         name = new Name("Alders", "Julia");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);

         /* Duplicate of Julia Alders  */
         phone = new PhoneNum(569, "109-9925");
         name = new Name("Alders", "Julia");
         cus = new Customer(name, phone);
         phone_search.put(cus.getPhone(), cus);
         
         for(Map.Entry<PhoneNum, Customer> data : phone_search.entrySet()){
        	Customer key_customer=data.getValue();
        	Name val_customer=data.getValue().getName();
        	
        	if(name_search.containsKey(val_customer)){
        		 ArrayList<Customer> custlist=(ArrayList<Customer>)name_search.get(val_customer);   		 
        		 custlist.add(key_customer);
        		 name_search.put(val_customer, custlist);
        	}
        	else
        	{
        		cusList = new ArrayList<Customer>();
        		cusList.add(key_customer);
        		name_search.put(val_customer, cusList);
        	}        	
         }
    }
}