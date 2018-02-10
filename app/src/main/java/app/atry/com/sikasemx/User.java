package app.atry.com.sikasemx;

/**
 * Created by jessicaannor on 10/02/2018.
 */

public class User {
    public String name, email, tel, org, uid;
    public User(){
        // Default constructor
    }

    public User(String uid, String username, String email){
        this.uid = uid;
        this.name = username;
        this.email = email;

    }

}
