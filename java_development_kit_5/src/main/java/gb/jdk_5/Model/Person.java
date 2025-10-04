package gb.jdk_5.Model;

public class Person {
        public String lastName;
        public String firstName;
        public String middleName;
        public String gender; // "male" / "female"
        public String birthdate; // "yyyy-MM-dd"
        public String phonenumber;

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getMiddleName() {
                return middleName;
        }

        public void setMiddleName(String middleName) {
                this.middleName = middleName;
        }

        public String getGender() {
                return gender;
        }

        public void setGender(String gender) {
                this.gender = gender;
        }

        public String getBirthdate() {
                return birthdate;
        }

        public void setBirthdate(String birthdate) {
                this.birthdate = birthdate;
        }

        public String getPhonenumber() {
                return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
                this.phonenumber = phonenumber;
        }

        public Person(String lastName, String firstName, String middleName, String gender, String birthdate,
                        String phonenumber) {
                this.lastName = lastName;
                this.firstName = firstName;
                this.middleName = middleName;
                this.gender = gender;
                this.birthdate = birthdate;
                this.phonenumber = phonenumber;
        }
} 