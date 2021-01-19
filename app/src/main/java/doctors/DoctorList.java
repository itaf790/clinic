package doctors;

public class DoctorList{

    private String Name;
    private String Specialization;
    private String Experiance;
    private String Education;
    private String Email;
    private String Age;
    private String Contact;
    private String Address;
    private String Shift;
    public String imageURL;

    public DoctorList(){

    }

    public DoctorList(String name,String spcialization, String experiance, String eduction, String email,String age,String contact, String address, String shift,String url) {
        this.Name = name;
        this.Specialization = spcialization;
        this.Experiance = experiance;
        this.Education = eduction;
        this.Email = email;
        this.Age = age;
        this.Contact = contact;
        this.Address = address;
        this.Shift = shift;
        this.imageURL = url;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }

    public String getSpecialization() {
        return Specialization;
    }
    public void setSpecialization(String specialization) {
        this.Specialization = specialization;
    }

    public String getExperiance() {
        return Experiance;
    }
    public void setExperiance(String experiance) {
        this.Experiance = experiance;
    }


    public String getEducation() {
        return Education;
    }
    public void setEducation(String education) {
        this.Education = education;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }



    public String getAge() {
        return Age;
    }
    public void setAge(String age) {
        this.Age = age;
    }




    public String getContact() {
        return Contact;
    }
    public void setContact(String contact) {
        this.Contact = contact;
    }

    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        this.Address = address;
    }

    public String getShift() {
        return Shift;
    }
    public void setShift(String shift) {
        this.Shift = shift;
    }


    public String getImageURL() {
        return imageURL;
    }

    
}
