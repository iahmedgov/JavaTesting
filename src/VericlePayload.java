
import java.util.ArrayList;
import java.util.List;


public class VericlePayload implements java.io.Serializable{
	
	String CP_ID;
	String CP_MKey;
	String Clerk_Key;
	String TPSID;
	String PATIENT_ID;
	String LastName;
	String FirstName;
	String Address; 
	String City; 
	String State;
	String Zip;
	String Phone;
	String Phone2; 
	String Email; 
	String DateTime; 
	String Claim_ID; 
	private List<String> tags = new ArrayList<String>();	
	// array	
	private List<VericleTransaction> transactions = new ArrayList<VericleTransaction>();

	String Transaction_amount;

	public String getCP_ID() {
		return CP_ID;
	}

	public void setCP_ID(String cP_ID) {
		CP_ID = cP_ID;
	}

	public String getCP_MKey() {
		return CP_MKey;
	}

	public void setCP_MKey(String cP_MKey) {
		CP_MKey = cP_MKey;
	}

	public String getClerk_Key() {
		return Clerk_Key;
	}

	public void setClerk_Key(String clerk_Key) {
		Clerk_Key = clerk_Key;
	}

	public String getTPSID() {
		return TPSID;
	}

	public void setTPSID(String tPSID) {
		TPSID = tPSID;
	}

	public String getPATIENT_ID() {
		return PATIENT_ID;
	}

	public void setPATIENT_ID(String pATIENT_ID) {
		PATIENT_ID = pATIENT_ID;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getZip() {
		return Zip;
	}

	public void setZip(String zip) {
		Zip = zip;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getPhone2() {
		return Phone2;
	}

	public void setPhone2(String phone2) {
		Phone2 = phone2;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

	public String getClaim_ID() {
		return Claim_ID;
	}

	public void setClaim_ID(String claim_ID) {
		Claim_ID = claim_ID;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<VericleTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<VericleTransaction> transactions) {
		this.transactions = transactions;
	}

	public String getTransaction_amount() {
		return Transaction_amount;
	}

	public void setTransaction_amount(String transaction_amount) {
		Transaction_amount = transaction_amount;
	}

}
