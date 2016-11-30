package cusutils.cusData;

public class PhoneNum {
    private int areaCode;
    private String localNum;

    public PhoneNum(int newAreaCode, String newLocalNum) {
        areaCode = newAreaCode;
        localNum = newLocalNum;
    }

    public String toString() {
        return "(" + areaCode + ")" + localNum;
    }
    
    @Override
    public boolean equals(Object o) {
    	PhoneNum num;
    	
    	if(!(o instanceof PhoneNum))
    		return false;
    	num =(PhoneNum) o;
    	if(this.areaCode == num.areaCode && this.localNum.equals(num.localNum)){
    		return true;
    	}
    	else{
    	return false;
    	}
    }

    @Override
    public int hashCode() {
    	int count = areaCode*31;
    	
    	count=localNum.hashCode()*31;
    	return count;
    }

}