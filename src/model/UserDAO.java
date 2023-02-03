package model;

import database.DatabaseUtils;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class UserDAO {
//    List<User> ls = new ArrayList<>();
//    
//    public UserDAO(){
//        ls.add(new User("admin", "12345", true));
//        ls.add(new User("bao", "12345", true));
//        ls.add(new User("thu", "12345", true));
//        ls.add(new User("ha", "12345", true));
//        ls.add(new User("nhan", "12345", true));
//        ls.add(new User("quan", "12345", true));
//    }
    
    public User getUserByID(String username){
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        User ee = new User();
        
        try{
            String sSQL = "select * from Users where username = ?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, username);
            rs = sttm.executeQuery();
            while(rs.next()){
                ee.setUsername(rs.getString(1));
                ee.setPassword(rs.getString(2));
                ee.setRole(rs.getBoolean(3));
                
                return ee;
            }
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
        }finally{
            try{
                conn.close();
                rs.close();
                sttm.close();
            }catch (Exception e){
                
            }
        }
        return null;
    }
    
    
//    public boolean checkLogin(String username, String pass){
//        for (User u : ls) {
//            if(u.getUsername().equals(username) && u.getPassword().equals(pass)){
//                return true;
//            }
//        }
//        return false;
//    }
    
    public boolean checkLogin(String username, String pass){
        User user = getUserByID(username);
        if(user != null){
            // ton tai co trong databse
            if(user.getPassword().equals(pass)){
                return true;
            }
        }
        return false;
    }
}
