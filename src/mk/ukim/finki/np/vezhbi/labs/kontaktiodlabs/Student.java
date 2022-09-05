package mk.ukim.finki.np.vezhbi.labs.kontaktiodlabs;

public class Student {
    private Contact [] contacts;

    private String firstName;
    private String lastName;
    private String city;
    private int age;
    private long index;

    private int totalEmailContacts;
    private int totalPhoneContacts;

    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
        this.totalPhoneContacts = 0;
        this.totalPhoneContacts = 0;
        this.contacts = new Contact[0];
    }

    public void addEmailContact(String date, String email) {
        Contact [] temp = new Contact[contacts.length+1];

        int i;
        for(i=0; i<contacts.length; i++) {
            temp[i] = contacts[i];
        }
        temp[i] = new EmailContact(date,email);
        contacts = temp;
        totalEmailContacts++;
    }

    public void addPhoneContact(String date, String phone) {
        Contact [] temp = new Contact[contacts.length + 1];
        int i;
        for(i=0; i<contacts.length; i++) {
            temp[i] = contacts[i];
        }
        temp[i] = new PhoneContact(date,phone);

        contacts = temp;
        totalPhoneContacts++;
    }

    public Contact [] getEmailContacts() {
        Contact [] temp = new Contact[totalEmailContacts];
        int j=0;
        for(int i=0; i<contacts.length; i++) {
            if(contacts[i].getType().equals("Email")) {
                temp[j++] = contacts[i];
            }
        }
        return temp;
    }

    public Contact [] getPhoneContacts() {
        Contact [] temp = new Contact[totalPhoneContacts];
        int j=0;
        for(int i=0; i<contacts.length; i++) {
            if(contacts[i].getType().equals("Phone")) {
                temp[j++] = contacts[i];
            }
        }
        return temp;
    }

    public String getCity() {
        return city;
    }

    public String getFullName(){
        return String.format("%s %s",firstName,lastName);
    }

    public long getIndex() {
        return index;
    }

    public Contact getLatestContact(){
        Contact min = contacts[0];

        for(int i=1; i<contacts.length; i++) {
            if(min.isNewerThan(contacts[i])) {
                min = contacts[i];
            }
        }

        return min;
    }

    public String toStringPhones(){
        StringBuilder sb = new StringBuilder();
        for(Contact contact : getPhoneContacts()) {
            sb.append(String.format("%s, ",contact.getContact()));
        }
        return sb.toString();
    }

    public String toStringEmails() {
        StringBuilder sb = new StringBuilder();
        for(Contact contact : getEmailContacts()) {
            sb.append(String.format("%s, ",contact.getContact()));
        }
        return sb.toString();
    }

    public int getNumberOfContacts(){
        return contacts.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(String.format("\"ime\":\"%s\", ", this.firstName));
        sb.append(String.format("\"prezime\":\"%s\", ", this.lastName));
        sb.append(String.format("\"vozrast\":%d, ", this.age));
        sb.append(String.format("\"grad\":\"%s\", ", this.city));
        sb.append(String.format("\"indeks\":%d, ", this.index));
        sb.append(String.format("\"telefonskiKontakti\":[%s], ", this.toStringPhones()));
        sb.append(String.format("\"emailKontakti\":[%s]", this.toStringEmails()));
        sb.append("}");
        return sb.toString();
    }
}

